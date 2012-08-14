drop table if exists MOVES;
drop table if exists GAME;

create table MOVES (
 id INT NOT NULL AUTO_INCREMENT,
 move INT,
 white CHAR(7),
 black CHAR(7),
 game INT, INDEX (game),
 PRIMARY KEY (id)
);
 
create table GAME (
  id INT NOT NULL AUTO_INCREMENT,
  white VARCHAR(256),
  black VARCHAR(256),
  description VARCHAR(1024),  
  result VARCHAR(8),  
  next CHAR(1),
  move INT,
  PRIMARY KEY (id)
);
