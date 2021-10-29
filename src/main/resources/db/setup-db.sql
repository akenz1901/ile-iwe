create database ileiwedb;
create user if not exists 'ileiwe_user'@'localhost' identified by 'Ileiwe111';
grant all privileges on ileiwedb.* to 'ileiwe_user'@'localhost';
flush privileges;