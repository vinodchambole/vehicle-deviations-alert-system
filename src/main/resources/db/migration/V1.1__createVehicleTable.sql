CREATE TABLE IF NOT EXISTS `vehicle` (
  `vehicle_name` varchar(45) NOT NULL,
  `vehicle_number` varchar(45) NOT NULL DEFAULT "XXX",
  `reg_date` datetime(3) NOT NULL,
  `color` varchar(45) NOT NULL,
  `fuel` varchar(45) NOT NULL,
  `make_date` datetime(3) NOT NULL,
  `owner_id` varchar(45) NOT NULL,
  `owner_name` varchar(45) NOT NULL,
  `fasttag_id` varchar(45) NOT NULL,
  PRIMARY KEY (`vehicle_number`),
  UNIQUE KEY `vehicle_number_UNIQUE` (`vehicle_number`),
  UNIQUE KEY `owner_id_UNIQUE` (`owner_id`),
  UNIQUE KEY `fasttag_id_UNIQUE` (`fasttag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;