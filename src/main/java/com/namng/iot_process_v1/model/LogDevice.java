package com.namng.iot_process_v1.model;

import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;

@Data
@Entity
@Table(name = "log_device")
public class LogDevice {

    public static final String KEY_ID = "DeviceId";
    public static final String KEY_INFOR = "infor";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String infor;

    @Column(nullable = false)
    private Date state_time;

    @Column(nullable = false)
    private int status_app;

    @Column(nullable = false)
    private int status_web;

    @Column(nullable = false)
    private Long id_device;
}
