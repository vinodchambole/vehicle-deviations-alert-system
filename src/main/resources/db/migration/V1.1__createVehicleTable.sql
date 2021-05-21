CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vehicle_name` varchar(45) NOT NULL,
  `vehicle_number` varchar(45) NOT NULL,
  `reg_date` datetime(3) NOT NULL,
  `color` varchar(45) NOT NULL,
  `fuel` varchar(45) NOT NULL,
  `make_date` datetime(3) NOT NULL,
  `owner_adhar_id` varchar(45) NOT NULL,
  `owner_name` varchar(45) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `vehicle_number_UNIQUE` (`vehicle_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;