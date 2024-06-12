package com.namng.iot_process_v1.controller;

import com.namng.iot_process_v1.model.Device;
import com.namng.iot_process_v1.service.DeviceService;
import com.namng.iot_process_v1.service.impl.DeviceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/iot/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService = new DeviceServiceImp();

    @GetMapping("/getAllDevice")
    public ResponseEntity<?> GetAllDeviceInfor(){
        try {
            List<Device> response = new ArrayList<>();
            response = deviceService.loadAllDevice();
            return ResponseEntity.ok(response);
        }catch (Exception e){
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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
