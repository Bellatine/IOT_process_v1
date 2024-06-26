package com.namng.iot_process_v1.repository;

import com.namng.iot_process_v1.model.LogDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LogDeviceRepository extends JpaRepository<LogDevice, Long> {

    @Query("select l from LogDevice l where l.status_app = :status_app and l.id_device = :id_device")
    List<LogDevice> findWithStatusApp(@Param("status_app") Long status_app, @Param("id_device") Long id_device);

    @Query("select l from LogDevice l where l.status_web = :status_web and l.id_device = :id_device")
    List<LogDevice> findWithStatusWeb(@Param("status_web") Long status_web, @Param("id_device") Long id_device);

    @Modifying
    @Transactional
    @Query("update LogDevice l set l.status_web = :status_web where l.id = :id")
    void updateStatusWeb(@Param("status_web") Long status_web, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update LogDevice l set l.status_app = :status_app where l.id = :id")
    void updateStatusApp(@Param("status_app") Long status_app, @Param("id") Long id);
}
