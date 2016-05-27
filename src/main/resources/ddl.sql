DROP TABLE `DataBasePoissons`;

CREATE TABLE `DataBasePoissons` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `Poisson` varchar(255) default NULL,
  `Temperature` mediumint default NULL,
  `Acidite` mediumint default NULL,
  'Capacite' mediumint default NULL,
  'Eau' varchar(255) default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

INSERT INTO `DataBasePoissons` (`Poisson`,`Temperature`,`Acidite`,'Capacite','Eau') VALUES ("Saumon",5,2,25,"froide");
INSERT INTO `DataBasePoissons` (`Poisson`,`Temperature`,`Acidite`,'Capacite','Eau') VALUES ("Raie",44,4,41,"chaude");
INSERT INTO `DataBasePoissons` (`Poisson`,`Temperature`,`Acidite`,'Capacite','Eau') VALUES ("Crevette",32,13,33,"froide");
INSERT INTO `DataBasePoissons` (`Poisson`,`Temperature`,`Acidite`,'Capacite','Eau') VALUES ("Étoile",16,11,26,"chaude");
INSERT INTO `DataBasePoissons` (`Poisson`,`Temperature`,`Acidite`,'Capacite','Eau') VALUES ("Crabe",39,4,38,"froide");
INSERT INTO `DataBasePoissons` (`Poisson`,`Temperature`,`Acidite`,'Capacite','Eau') VALUES ("Truite",39,6,19,"chaude");
