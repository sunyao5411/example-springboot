DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `mail` varchar(128) NOT NULL DEFAULT '',
  `password` varchar(128) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `gender` char(1) NOT NULL DEFAULT '',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `employee` (`id`, `no`, `name`, `mail`, `password`, `phone`, `gender`)
VALUES
	(1,'0001','admin','admin@gmail.com','000000','15618332942','F'),
	(2,'0002','tiger','tiger@gmail.com','000000','15618332942','F'),
	(3,'0003','fish','fish@gmail.com','000000','15618332942','F'),
	(4,'0004','rabbit','rabbit@gmail.com','000000','15618332942','F'),
	(5,'0005','docker','docker@gmail.com','000000','15618332942','F')
	;