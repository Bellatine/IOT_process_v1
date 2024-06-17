package com.namng.iot_process_v1.repository;

import com.namng.iot_process_v1.model.Device;
import com.namng.iot_process_v1.model.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoolRepository extends JpaRepository<Pool, Long> {

    @Query("select p from Pool p where p.id_user = :id_user")
    List<Pool> findbyIdUser(@Param("id_user") Long id_user);

    @Query("select p from Pool p where p.name = :name")
    List<Pool> findbyName(@Param("name") String name);

}
