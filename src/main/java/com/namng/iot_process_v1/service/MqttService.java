package com.namng.iot_process_v1.service;

public interface MqttService {
    public void saveLogDevice(String payload);

    void controlDevice(Long deviceId, Long command);
}
