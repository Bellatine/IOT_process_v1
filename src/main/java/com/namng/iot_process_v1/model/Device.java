package com.namng.iot_process_v1.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long status;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long id_pool;
}
