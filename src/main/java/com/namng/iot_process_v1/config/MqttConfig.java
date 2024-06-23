//package com.namng.iot_process_v1.config;
//
//import com.namng.iot_process_v1.service.MqttService;
//import com.namng.iot_process_v1.service.impl.MqttServiceImpl;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.MqttCallback;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MqttConfig {
//
//    private static final Logger logger = LogManager.getLogger(MqttConfig.class);
//
//    @Autowired
//    private MqttService MqttService;
//
//    @Bean
//    public MqttClient mqttClient() throws MqttException {
//        String broker = "tcp://broker.hivemq.com:1883";
//        String clientId = MqttClient.generateClientId();
//        MqttClient client = new MqttClient(broker, clientId);
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setCleanSession(true);
//        client.connect(options);
//
//        client.subscribe("/test/namng1", (topic, message) -> {
//            String payload = new String(message.getPayload());
//            logger.info(payload);
//            //MqttService.saveLogDevice(payload);
//        });
//
//        return client;
//    }
//}