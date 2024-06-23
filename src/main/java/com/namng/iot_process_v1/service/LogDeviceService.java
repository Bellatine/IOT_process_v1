package com.namng.iot_process_v1.service;

import com.namng.iot_process_v1.model.LogDevice;

import java.util.List;

public interface LogDeviceService {
    List<LogDevice> getLogDeviceByStatus (int type);
}
