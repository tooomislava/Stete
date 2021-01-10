create database p3db default character set utf8mb4;
use tzilic_20_20;
create table prijava_stete(
    sifra int not null primary key auto_increment,
    vlasnik varchar(255) not null,
    geografskaSirina float(10,8) not null,
    geografskaDuzina float(10,8) not null,
    iznosStete float(10,2) not null

);
insert into prijava_stete(vlasnik,geografskaSirina,geografskaDuzina,iznosStete)
values ('Luka',2.23598023,3.2569,5.45),
 ('Petar',2.24578931,0.5896,7.23),
('Jure',8.00000000,5.4444,1.66);
