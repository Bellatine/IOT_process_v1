package com.namng.iot_process_v1.controller;


import com.namng.iot_process_v1.model.Pool;
import com.namng.iot_process_v1.service.PoolService;
import com.namng.iot_process_v1.service.impl.PoolServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iot/device")
public class PoolController {

    private static final Logger logger = LogManager.getLogger(PoolController.class);

    @Autowired
    private PoolService poolService = new PoolServiceImpl();

    @GetMapping("/getPoolByUsername")
    public ResponseEntity<?> getPoolByUsername(@RequestParam String username){
        try{
            return ResponseEntity.ok(poolService.getListPoolFromUser(username));
        }catch (Exception e){
            logger.error("Error get pool by username ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addNewPool")
    public ResponseEntity<?> addNewPool(@RequestBody Pool newPool){
        try{
            return ResponseEntity.ok(poolService.saveNewPool(newPool));
        }catch (Exception e){
            logger.error("add new pool fail ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userName}/{poolId}")
    public ResponseEntity<?> removePool(@PathVariable String userName, @PathVariable Long poolId){
        try{
            return ResponseEntity.ok(poolService.removePoolById(userName, poolId));
        }catch (Exception e){
            logger.error("remove pool fail ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
