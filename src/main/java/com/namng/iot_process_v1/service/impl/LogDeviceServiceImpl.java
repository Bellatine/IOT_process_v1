package com.namng.iot_process_v1.service.impl;

import com.namng.iot_process_v1.model.LogDevice;
import com.namng.iot_process_v1.repository.LogDeviceRepository;
import com.namng.iot_process_v1.service.LogDeviceService;
import com.namng.iot_process_v1.util.CacheManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogDeviceServiceImpl implements LogDeviceService {

    private static final Logger logger = LogManager.getLogger(LogDeviceService.class);

    @Autowired
    private LogDeviceRepository logDeviceRepository;

    @Override
    public List<LogDevice> getLogDeviceByStatus(int type) {
        List<LogDevice> logDevices = null;
        if(type == CacheManager.LogDevice.TYPE_APP){
            logDevices = logDeviceRepository.findWithStatusApp(CacheManager.LogDevice.NOT_SCAN);
            logger.info("Get log for app: " + logDevices.size());
            for(LogDevice logDevice : logDevices){
                logDeviceRepository.updateStatusApp(CacheManager.LogDevice.SCANNED, logDevice.getId());
            }
        } else if(type == CacheManager.LogDevice.TYPE_WEB){
            logDevices = logDeviceRepository.findWithStatusWeb(0L);
            logger.info("Get log for web: " + logDevices.size());
            for(LogDevice logDevice : logDevices) {
                logDeviceRepository.updateStatusWeb(CacheManager.LogDevice.SCANNED, logDevice.getId());
            }
        }

        return logDevices;
    }
}
