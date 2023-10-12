CREATE TABLE `population_scrap_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `yyyy` int NOT NULL,
  `mm` int NOT NULL,
  `scrap_start_dtm` datetime NOT NULL,
  `scrap_end_dtm` datetime NOT NULL,
  PRIMARY KEY (`id`)
);
