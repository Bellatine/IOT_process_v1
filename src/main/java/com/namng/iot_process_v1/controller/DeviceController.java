package com.namng.iot_process_v1.controller;

import com.namng.iot_process_v1.model.Device;
import com.namng.iot_process_v1.service.DeviceService;
import com.namng.iot_process_v1.service.MqttService;
import com.namng.iot_process_v1.service.impl.DeviceServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/iot/device")
public class DeviceController {

    private static final Logger logger = LogManager.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService = new DeviceServiceImp();


    @Autowired
    private MqttService mqttService;

    @GetMapping("/getAllDevice")
    public ResponseEntity<?> GetAllDeviceInfor(){
        try {
            List<Device> response = new ArrayList<>();
            response = deviceService.loadAllDevice();
            return ResponseEntity.ok(response);
        }catch (Exception e){
            logger.error("get device fail", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/changeDeviceState")
    public ResponseEntity<?> ChangeDeviceState(@RequestBody Device deviceInforRequest){

        return ResponseEntity.ok("change oke");
    }

    @PostMapping("/addDevice")
    public ResponseEntity<?> AddDevice(@RequestBody Device deviceInforRequest){
        try{
            return ResponseEntity.ok( deviceService.saveNewDevice(deviceInforRequest));
        }catch (Exception e){
            logger.error("add device fail", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{userName}/{deviceId}")
    public ResponseEntity<?> revoveUserDevice(@PathVariable String userName, @PathVariable Long deviceId) {
        try {
            String message = deviceService.removeDeviceById(userName, deviceId);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            logger.error("remove device fail", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getListDeviceByPoolId")
    public ResponseEntity<?> getListDeviceByPoolId(@RequestParam String username, @RequestParam Long poolId){
        try{
            return ResponseEntity.ok(deviceService.getListDevicebyPoolId(username,poolId));
        }catch (Exception e){
            logger.error("getListDeviceByPoolId fail!" + username + " --- " + poolId);
            return ResponseEntity.badRequest().body("getListDeviceByPoolId fail");
        }
    }

    @PostMapping("/sendCommand")
    public ResponseEntity<?> sendCommandToDevice(@RequestParam Long deviceId, @RequestParam Long command){
        try {
            mqttService.controlDevice(deviceId, command);
            return ResponseEntity.ok("ok");
        }catch (Exception e){
            logger.error("error send command");
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
