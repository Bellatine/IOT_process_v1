package com.namng.iot_process_v1.util;

import com.namng.iot_process_v1.model.User;

import java.util.Map;

public class CacheManager {

    public static class Status{
        public static int ACTIVE = 1;
        public static int INACTIVE = 0;
    }
    public static  class Role{
        public static int ADMIN = 1;
        public static int VIEWER = 2;
    }

    public static class Users{
        public static Map<String, User> ALLUSERS;
        public static User AUTH_USER;
    }



}