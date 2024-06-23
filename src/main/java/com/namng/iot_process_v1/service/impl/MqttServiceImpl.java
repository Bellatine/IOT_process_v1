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
import org.springframework.beans.factory.annotation.Autowired;

public class MqttServiceImpl implements MqttService {

    private static final Logger logger = LogManager.getLogger(MqttService.class);
    private final LogDeviceRepository logDeviceRepository;

    @Autowired
    public MqttServiceImpl(LogDeviceRepository logDeviceRepository) {
        this.logDeviceRepository = logDeviceRepository;
        connectAndSubscribe();
    }

    private void connectAndSubscribe() {
        String broker = "tcp://broker.hivemq.com:1883"; // Thay đổi URL broker theo nhu cầu
        String clientId = "namng";
        String topic = "/test/namng1"; // Thay đổi chủ đề theo nhu cầu

        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            client.connect(options);
            client.subscribe(topic, (topic1, message) -> {
                String payload = new String(message.getPayload());
                LogDevice logDevice = new LogDevice();
                JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
                logDevice.setId_device((long) Integer.parseInt(String.valueOf(jsonObject.get(LogDevice.KEY_ID))));
                logDevice.setInfor(String.valueOf(jsonObject.get(LogDevice.KEY_INFOR)));
                logDevice.setStatus_app(CacheManager.LogDevice.NOT_SCAN);
                logDevice.setStatus_web(CacheManager.LogDevice.NOT_SCAN);
                for (String key : jsonObject.keySet()) {
                    JsonElement value = jsonObject.get(key);
                    logger.info("Field " + key + ": " + value);

                }
//                logDevice.setIdDevice(1L); // Thay đổi logic để xác định id_device hợp lệ
//                logDevice.setInfor(payload);
                logDeviceRepository.save(logDevice);
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
