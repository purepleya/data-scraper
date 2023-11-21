DROP TABLE IF EXISTS `population_scrap_log`;

CREATE TABLE IF NOT EXISTS `population_scrap_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `yyyy_mm` varchar(6) NOT NULL,
  `stdg_cd` varchar(12) NOT NULL,
  `lv` int NOT NULL,
  `reg_se_cd` int NOT NULL,
  `scrap_start_dtm` datetime NOT NULL,
  `scrap_end_dtm` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
