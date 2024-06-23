package com.namng.iot_process_v1.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.namng.iot_process_v1.model.LogDevice;
import com.namng.iot_process_v1.repository.LogDeviceRepository;
import com.namng.iot_process_v1.service.MqttService;
import com.namng.iot_process_v1.util.CacheManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MqttServiceImpl implements MqttService {

    private static final Logger logger = LogManager.getLogger(MqttService.class);
    private String pubTopicPath = "/test/namng1/";

    private String broker = "tcp://test.mosquitto.org:1883";
    private String clientId = "namng2";
    private String topic = "/test/namng1";
    private MemoryPersistence persistence = new MemoryPersistence();
    private MqttClient client = null;

    @Autowired
    private final LogDeviceRepository logDeviceRepository;

//    @Autowired
//    private MqttClient mqttClient;

    @Autowired
    public MqttServiceImpl(LogDeviceRepository logDeviceRepository) {
        this.logDeviceRepository = logDeviceRepository;
        connectAndSubscribe();
    }

    @Override
    public void saveLogDevice(String payload){
        LogDevice logDevice = new LogDevice();
        JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        logDevice.setId_device((long) Integer.parseInt(String.valueOf(jsonObject.get(LogDevice.KEY_ID))));
        logDevice.setInfor(String.valueOf(jsonObject.get(LogDevice.KEY_INFOR)));
        logDevice.setStatus_app(CacheManager.LogDevice.NOT_SCAN);
        logDevice.setStatus_web(CacheManager.LogDevice.NOT_SCAN);
        logDevice.setState_time(new Date(System.currentTimeMillis()));
        for (String key : jsonObject.keySet()) {
            JsonElement value = jsonObject.get(key);
            logger.info("Field " + key + ": " + value);

        }
        logDeviceRepository.save(logDevice);
    }

    @Override
    public void controlDevice(Long deviceId, Long command) {
        try{
            String pubTopic = pubTopicPath + String.valueOf(deviceId);
            MqttMessage message = new MqttMessage(String.valueOf(command).getBytes());
            message.setQos(1);
            client.publish(pubTopic, message);
        }catch (Exception e){
            logger.error("pub fail deviceId: " + deviceId + " ---- command: " + command, e);
        }

    }

    private void connectAndSubscribe() {
        try {
            client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            client.connect(options);
            client.subscribe(topic, (topic1, message) -> {
                String payload = new String(message.getPayload());
                saveLogDevice(payload);
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
