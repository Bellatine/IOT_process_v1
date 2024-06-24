package com.namng.iot_process_v1.controller;

import com.namng.iot_process_v1.service.LogDeviceService;
import com.namng.iot_process_v1.service.MqttService;
import com.namng.iot_process_v1.service.impl.LogDeviceServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/iot/log_device")
public class LogDeviceController {

    private static final Logger logger = LogManager.getLogger(LogDeviceController.class);

    @Autowired
    private LogDeviceService logDeviceService = new LogDeviceServiceImpl();

    @GetMapping("/getLogDevice")
    public ResponseEntity<?> getLogDevice(@RequestParam int type, @RequestParam Long deviceId){ //type 2: app, type 1: web
        try{
            return ResponseEntity.ok(logDeviceService.getLogDeviceByStatus(type, deviceId));
        }catch (Exception e){
            logger.error("Error get logdevice ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
