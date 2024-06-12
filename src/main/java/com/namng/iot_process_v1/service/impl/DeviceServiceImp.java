package com.namng.iot_process_v1.service.impl;

import com.namng.iot_process_v1.model.Device;
import com.namng.iot_process_v1.repository.DeviceRepository;
import com.namng.iot_process_v1.repository.UserRepository;
import com.namng.iot_process_v1.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Override
    public Device saveNewDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> loadAllDevice() {
        return deviceRepository.findAll();
    }

    @Override
    public Device searchDeviceByName(String name) {
        return  deviceRepository.findByName(name);
    }
}
