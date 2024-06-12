package com.namng.iot_process_v1.repository;

import com.namng.iot_process_v1.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAll();
    Optional<Device> findById(Long id);
    @Query("SELECT d FROM Device d WHERE d.name = :name")
    Device findByName(@Param("name") String name);
    void deleteByName(String name);
    void deleteById(Long id);
}
