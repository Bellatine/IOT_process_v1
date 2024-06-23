//package com.namng.iot_process_v1.config;
//
//import com.namng.iot_process_v1.service.MqttService;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MqttConfig {
//
//    @Autowired
//    private MqttService mqttService;
//
//    private String subTopic = "/test/namng1";
//
//    @Bean
//    public MqttClient mqttClient() throws MqttException {
//        String broker = "tcp://broker.hivemq.com:1883";
//        String clientId = MqttClient.generateClientId();
//        MqttClient client = new MqttClient(broker, clientId);
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setCleanSession(true);
//
//        // Nếu broker yêu cầu xác thực, hãy thêm tên người dùng và mật khẩu vào đây
//        // options.setUserName("yourUsername");
//        // options.setPassword("yourPassword".toCharArray());
//
//        client.connect(options);
//
//        // Subscribe to topic
//        client.subscribe(subTopic, (topic, message) -> {
//            String payload = new String(message.getPayload());
//            mqttService.saveLogDevice( payload);
//        });
//
//        return client;
//    }
//}