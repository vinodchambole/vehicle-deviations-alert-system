CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vehicle_name` varchar(45) NOT NULL,
  `vehicle_number` varchar(45) NOT NULL,
  `reg_date` datetime(3) NOT NULL,
  `color` varchar(45) NOT NULL,
  `fuel` varchar(45) NOT NULL,
  `make_date` datetime(3) NOT NULL,
  `manufacturer` varchar(45) NOT NULL,
  `owner_adhar_id` varchar(45) NOT NULL,
  `owner_name` varchar(45) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `vehicle_number_UNIQUE` (`vehicle_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS violations (
  id bigint(10) NOT NULL AUTO_INCREMENT,
  type varchar(255) DEFAULT NULL,
  fine_amount DOUBLE NOT NULL,
  violation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
  vehicle_number varchar(45) NOT NULL,
  PRIMARY KEY (id),
 CONSTRAINT violations_fk FOREIGN KEY (vehicle_number) REFERENCES vehicle (vehicle_number)
) DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE IF NOT EXISTS vehicle_owner (
  owner_adhar_id varchar(45) NOT NULL,
  owner_name varchar(45) DEFAULT NULL,
  phone_number varchar(45) DEFAULT NULL,
  address varchar(45) DEFAULT NULL,
  fasttag_id varchar(45) DEFAULT NULL,
  fasttag_balance DOUBLE NOT NULL,
  PRIMARY KEY (owner_adhar_id)
) DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811110000', 'Ram', '1234567890', 'Bidar', '1111111111', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811111111', 'Ram', '1234567890', 'Bidar', '2222222222', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811112222', 'Ram', '1234567890', 'Bidar', '3333333333', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811113333', 'Ram', '1234567890', 'Bidar', '4444444444', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811114444', 'Ram', '1234567890', 'Bidar', '5555555555', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811115555', 'Ram', '1234567890', 'Bidar', '6666666666', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811116666', 'Ram', '1234567890', 'Bidar', '7777777777', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811117777', 'Ram', '1234567890', 'Bidar', '8888888888', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811118888', 'Ram', '1234567890', 'Bidar', '9999999999', '10000.00');
INSERT INTO `vehicle`.`vehicle_owner` (`owner_adhar_id`, `owner_name`, `phone_number`, `address`, `fasttag_id`, `fasttag_balance`) VALUES ('1234456811119999', 'Ram', '1234567890', 'Bidar', '0000000000', '10000.00');


