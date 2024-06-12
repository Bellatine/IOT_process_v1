package com.namng.iot_process_v1.service.impl;

import com.namng.iot_process_v1.model.Device;
import com.namng.iot_process_v1.model.User;
import com.namng.iot_process_v1.repository.DeviceRepository;
import com.namng.iot_process_v1.repository.UserRepository;
import com.namng.iot_process_v1.service.DeviceService;
import com.namng.iot_process_v1.util.CacheManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService {

    private static final Logger logger = LogManager.getLogger(DeviceServiceImp.class);

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

    @Override
    public String removeDeviceById(String userName, Long deviceId) {
        try{
            User user = CacheManager.Users.ALLUSERS.get(userName);
            if(user == null || user.getRole() != CacheManager.Role.ADMIN
                    ||user.getStatus() != CacheManager.Status.ACTIVE){
                logger.warn("user is not admin or inactive!");
                return "user is not admin or inactive!";
            }
            deviceRepository.deleteById(deviceId);
            return "remove success!";
        }catch (Exception e){
            logger.error("Error when remove device!", e);
            return e.getMessage();
        }
    }
}
