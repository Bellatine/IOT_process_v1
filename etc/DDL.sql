CREATE TABLE `device`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) NOT NULL,
    `status`      int(11) DEFAULT NULL,
    `description` varchar(500) DEFAULT NULL,
    `id_pool`     int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `fk_id_pool` (`id_pool`),
    CONSTRAINT `fk_id_pool` FOREIGN KEY (`id_pool`) REFERENCES `pool` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
-- iot.device definition


CREATE TABLE `pool`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) NOT NULL,
    `id_user`     int(11) DEFAULT NULL,
    `description` varchar(500) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `id_user` (`id_user`),
    CONSTRAINT `pool_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `users`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `fullname` varchar(100) NOT NULL,
    `status`   int(11) DEFAULT NULL,
    `role`     int(11) DEFAULT NULL,
    `email`    varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `log_device` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `id_device` int(11) DEFAULT NULL,
                              `infor` varchar(1000) DEFAULT NULL,
                              `state_time` timestamp NULL DEFAULT current_timestamp(),
                              `status_app` int(11) DEFAULT NULL,
                              `status_web` int(11) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;