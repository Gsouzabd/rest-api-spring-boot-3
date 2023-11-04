CREATE TABLE IF NOT EXISTS `rest_api_spring_boot_gsdb`.`person` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`first_name` varchar(250) NOT NULL,
`last_name` varchar(250) NOT NULL,
`address` varchar(250) NOT NULL,
`gender` varchar(8) NOT NULL,
PRIMARY KEY (`id`)
);
