package com.namng.iot_process_v1.repository;

import com.namng.iot_process_v1.model.LogDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDeviceRepository extends JpaRepository<LogDevice, Long> {
    @Query("select l from LogDevice l where l.status_app = :status_app")
    List<LogDevice> findWithStatusApp(@Param("status_app") Long status_app);
}
