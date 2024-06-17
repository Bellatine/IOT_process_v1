package com.namng.iot_process_v1.service.impl;

import com.namng.iot_process_v1.model.Pool;
import com.namng.iot_process_v1.model.User;
import com.namng.iot_process_v1.repository.PoolRepository;
import com.namng.iot_process_v1.service.PoolService;
import com.namng.iot_process_v1.util.CacheManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PoolServiceImpl implements PoolService {

    private static final Logger logger = LogManager.getLogger(PoolService.class);

    @Autowired
    PoolRepository poolRepository;
    @Override
    public List<Pool> getListPoolFromUser(String userName) {
        User user = CacheManager.Users.ALLUSERS.get(userName);
        if(user == null){
            logger.warn("User not found");
            return null;
        }
        return poolRepository.findbyIdUser(user.getId());
    }

    @Override
    public Pool saveNewPool(Pool pool) {
        return poolRepository.save(pool);
    }

    @Override
    public Pool searchPoolByName(String name) {
        return poolRepository.findbyName(name).get(0);
    }

    @Override
    public int removePoolById(String userName, Long poolId) {
        User user = CacheManager.Users.ALLUSERS.get(userName);
        Optional<Pool> pool = poolRepository.findById(poolId);
        if(user == null || !Objects.equals(pool.get().getId_user(), user.getId())){
            logger.warn("user not admin or pool not found");
            return 0;
        }
        poolRepository.deleteById(poolId);
        return 1;
    }
}
