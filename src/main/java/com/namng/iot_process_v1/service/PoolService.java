package com.namng.iot_process_v1.service;

import com.namng.iot_process_v1.model.Device;
import com.namng.iot_process_v1.model.Pool;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoolService {
    List<Pool> getListPoolFromUser(String userName);
    Pool saveNewPool(Pool pool);
    Pool searchPoolByName(String name);
    int removePoolById(String userName, Long poolId);


}
