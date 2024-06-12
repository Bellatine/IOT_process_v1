package com.namng.iot_process_v1;

import com.namng.iot_process_v1.service.UserService;
import com.namng.iot_process_v1.service.impl.UserServiceImpl;
import com.namng.iot_process_v1.util.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IotProcessV1Application {


    public static void main(String[] args) {
        SpringApplication.run(IotProcessV1Application.class, args);
//        UserService userServiceImpl = new UserServiceImpl();
//        CacheManager.Users.ALLUSERS = userServiceImpl.loadAllUser();
    }

}
