package com.namng.iot_process_v1.service;

import com.namng.iot_process_v1.model.Device;

import java.util.List;

public interface DeviceService {
    Device saveNewDevice(Device device);
    List<Device> loadAllDevice();
    Device searchDeviceByName(String name);
    String removeDeviceById(String userName, Long deviceId);
    List<Device> getListDevicebyPoolId(String userName, Long poolId);
}
