CREATE DATABASE portal1 DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
create user 'portal1'@'%' identified by 'portal1';
use portal1;
grant all privileges on portal1.* to portal1@'%';