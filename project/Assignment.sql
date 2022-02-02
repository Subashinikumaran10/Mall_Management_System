create database Assignment;
use assignment;
create table Non_veg_food
(
id int Not null primary key auto_increment,
Name_of_variety  varchar(50),
price float
);

insert into Non_veg_food values(1,'Chicken_Briyani',150);
insert into Non_veg_food(Name_of_variety,price)  values('Mutton_Briyani',250);
insert into Non_veg_food(Name_of_variety,price)  values('Fried_Chicken',200);
insert into Non_veg_food(Name_of_variety,price)  values('Chicken_Lollipop',250);
insert into Non_veg_food(Name_of_variety,price)  values('Tandoori_chicken',300);
insert into Non_veg_food(Name_of_variety,price)  values('Mutton_curry',100);
insert into Non_veg_food(Name_of_variety,price)  values('Chicken_curry',250);
insert into Non_veg_food(Name_of_variety,price)  values('Chicken_noodles',250);
select * from Non_veg_food;

select * from non_veg_food price where name_of_variety='chicken_curry';
use assignment;
create table veg_food
(
id int not null primary key auto_increment,
Name_of_variety varchar(50),
price float
);

insert into veg_food values(1,'Veg_fried_rice',120);
insert into veg_food(Name_of_variety,price) values('Gopi_manchurian',200);
insert into veg_food(Name_of_variety,price) values('Butter_Naan',200);
insert into veg_food(Name_of_variety,price) values('Bisi_Bele_bath',80);
insert into veg_food(Name_of_variety,price) values('Veg_noodles',200);
insert into veg_food(Name_of_variety,price) values('Aloo_mushroom_curry',60);
insert into veg_food(Name_of_variety,price) values('masala_dosa',50);
 
update veg_food
set Name_of_variety ="masala_dosa"
where id = 7;
select* from veg_food;

use assignment;
create table Ice_Baker
(
id int not null primary key auto_increment,
Name_of_variety varchar(50),
price float
);

insert into Ice_baker values(1,'Blueberry_cone',50);
insert into Ice_Baker(Name_of_variety,price) values('Butterscotch',50);
insert into Ice_Baker(Name_of_variety,price) values('Vanilla',50);
insert into Ice_Baker(Name_of_variety,price) values('slice_favour',100);
insert into Ice_Baker(Name_of_variety,price) values('Falooda',200);
insert into Ice_Baker(Name_of_variety,price) values('Kulfi',500);

select* from Ice_baker;

use assignment;
create table Sweets_Baker
(
id int not null primary key auto_increment,
Name_of_variety varchar(50),
price float
);

insert into Sweets_Baker values(1,'Gulab_jamun',30);
insert into Sweets_Baker(Name_of_variety,price) values('Kaju_Katli',50);
insert into Sweets_Baker(Name_of_variety,price) values('Ladoo',50);
insert into Sweets_Baker(Name_of_variety,price) values('Kaju_Katli',50);
insert into Sweets_Baker(Name_of_variety,price) values('Poli',50);
insert into Sweets_Baker(Name_of_variety,price) values('Rasgulla',50);
insert into Sweets_Baker(Name_of_variety,price) values('Unni_Appam',50);
insert into Sweets_Baker(Name_of_variety,price) values('Ras_Malai',50);

select* from Sweets_Baker;

use assignment;
create table users
(
name varchar(50),
Name_of_variety varchar(50),
No_of_table int,
price float,
Total float
);
select* from users;
truncate users;



use assignment;
create table MAX
(
code_no int not null ,
Name_of_Dresses varchar(50),
price float,
discount float,
Total float
);

insert into max values(346784,'shirt',700,20,569);
insert into max values(346785,'Hawaiian shirt',800,12,704);
insert into max (code_no,Name_of_dresses,price,Total) values(346786,'Tshirt',450,450);
insert into max (code_no,Name_of_dresses,price,Total) values(346787,'Jumper',450,450);
insert into max values(346788,'Jeans',800,12,704);
insert into max (code_no,Name_of_dresses,price,Total) values(346766,'singlet',250,250);
insert into max values(346767,'kurthi',650,10,585);
insert into max values(346768,'Salwar',700,12,616);
insert into max (code_no,Name_of_dresses,price,Total) values(346789,'palzo',450,450);
insert into max (code_no,Name_of_dresses,price,Total) values(346790,'Anarkali',650,650);
insert into max values(346791,'Kurta_set',1000,12,880);
insert into max (code_no,Name_of_dresses,price,Total) values(346792,'genes',800,800);
insert into max (code_no,Name_of_dresses,price,Total) values(346793,'top',450,450);
insert into max (code_no,Name_of_dresses,price,Total) values(346794,'zunato',450,450);
insert into max (code_no,Name_of_dresses,price,Total) values(346795,'splendid',450,450);
insert into max (code_no,Name_of_dresses,price,Total) values(346796,'frock',550,550);
insert into max (code_no,Name_of_dresses,price,Total) values(346797,'Noe&zoe',450,450);
insert into max values(346798,'grandy_frock',1000,12,880);

select* from max;

create table custtable_max
(
coined_no int,
code_no int,
name_of_dresses varchar(50),
price float,
total float,
payment float
);
select * from custtable_max;

use assignment;
create table Trends
(
code_no int not null ,
Name_of_Dresses varchar(50),
price float,
discount float,
Total float
);

insert into Trends values(346784,'shirt',700,20,569);
insert into Trends values(346785,'Hawaiian shirt',800,12,704);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346786,'Tshirt',450,450);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346787,'Jumper',450,450);
insert into Trends values(346788,'Jeans',800,12,704);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346766,'singlet',250,250);
insert into Trends values(346767,'kurthi',650,10,585);
insert into Trends values(346768,'Salwar',700,12,616);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346789,'palzo',450,450);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346790,'Anarkali',650,650);
insert into Trends values(346791,'Kurta_set',1000,12,880);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346792,'genes',800,800);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346793,'top',450,450);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346794,'zunato',450,450);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346795,'splendid',450,450);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346796,'frock',550,550);
insert into Trends (code_no,Name_of_dresses,price,Total) values(346797,'Noe&zoe',450,450);
insert into Trends values(346798,'grandy_frock',1000,12,880);

select * from Trends;
create table custtable_Trends
(
coined_no int,
code_no int,
name_of_dresses varchar(50),
price float,
total float,
payment float
);

select * from custtable_trends;
use assignment;
create table LifeStyle
(
code_no int not null ,
Name_of_Dresses varchar(50),
price float,
discount float,
Total float
);

insert into Lifestyle values(346784,'shirt',700,20,569);
insert into Lifestyle values(346785,'Hawaiian shirt',800,12,704);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346786,'Tshirt',450,450);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346787,'Jumper',450,450);
insert into Lifestyle values(346788,'Jeans',800,12,704);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346766,'singlet',250,250);
insert into Lifestyle values(346767,'kurthi',650,10,585);
insert into Lifestyle values(346768,'Salwar',700,12,616);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346789,'palzo',450,450);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346790,'Anarkali',650,650);
insert into Lifestyle values(346791,'Kurta_set',1000,12,880);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346792,'genes',800,800);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346793,'top',450,450);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346794,'zunato',450,450);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346795,'splendid',450,450);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346796,'frock',550,550);
insert into Lifestyle (code_no,Name_of_dresses,price,Total) values(346797,'Noe&zoe',450,450);
insert into Lifestyle values(346798,'grandy_frock',1000,12,880);

select * from Lifestyle;
create table custtable_Lifestyle  
(
coined_no int,
code_no int,
name_of_dresses varchar(50),
price float,
total float,
payment float
);
 select * from custtable_lifestyle;


use assignment;
create table Metro
(
code_no int not null ,
Name_of_footwear varchar(50),
price float,
discount float,
Total float
);

insert into metro values(346784,'boat shoes',700,20,569);
insert into metro values(346785,'sandals',800,12,704);
insert into metro (code_no,Name_of_footwear,price,Total) values(346786,'sneakers',450,450);
insert into metro (code_no,Name_of_footwear,price,Total) values(346787,'clogs',450,450);
insert into metro values(346788,'Jellies',800,12,704);
insert into metro (code_no,Name_of_footwear,price,Total) values(346766,'soft shoes',250,250);
insert into metro values(346767,'flip flop',650,10,585);
insert into metro values(346768,'gladitors',700,12,616);
insert into metro (code_no,Name_of_footwear,price,Total) values(346789,'crocs',450,450);
insert into metro (code_no,Name_of_footwear,price,Total) values(346790,'Mary janes',650,650);
insert into metro values(346791,'crocs',1000,12,880);
insert into metro (code_no,Name_of_footwear,price,Total) values(346792,'kitten heel',800,800);
insert into metro (code_no,Name_of_footwear,price,Total) values(346793,'wedge',450,450);
insert into metro (code_no,Name_of_footwear,price,Total) values(346794,'slingbacks',450,450);
insert into metro (code_no,Name_of_footwear,price,Total) values(346795,'dockside',450,450);
insert into metro (code_no,Name_of_footwear,price,Total) values(346796,'mocassion',550,550);
insert into metro (code_no,Name_of_footwear,price,Total) values(346797,'ring sandals',450,450);
insert into metro values(346798,'jump boots',1000,12,880);

select * from Metro;
create table custtable_Metro  
(
coined_no int,
code_no int,
name_of_dresses varchar(50),
price float,
total float,
payment float
);
select * from custtable_metro;

use assignment;
create table BATA
(
code_no int not null ,
Name_of_footwear varchar(50),
price float,
discount float,
Total float
);

insert into bata values(346784,'boat shoes',700,20,569);
insert into bata values(346785,'sandals',800,12,704);
insert into bata (code_no,Name_of_footwear,price,Total) values(346786,'sneakers',450,450);
insert into bata (code_no,Name_of_footwear,price,Total) values(346787,'clogs',450,450);
insert into bata values(346788,'Jellies',800,12,704);
insert into bata (code_no,Name_of_footwear,price,Total) values(346766,'soft shoes',250,250);
insert into bata values(346767,'flip flop',650,10,585);
insert into bata values(346768,'gladitors',700,12,616);
insert into bata (code_no,Name_of_footwear,price,Total) values(346789,'crocs',450,450);
insert into bata (code_no,Name_of_footwear,price,Total) values(346790,'Mary janes',650,650);
insert into bata values(346791,'crocs',1000,12,880);
insert into bata (code_no,Name_of_footwear,price,Total) values(346792,'kitten heel',800,800);
insert into bata (code_no,Name_of_footwear,price,Total) values(346793,'wedge',450,450);
insert into bata (code_no,Name_of_footwear,price,Total) values(346794,'slingbacks',450,450);
insert into bata (code_no,Name_of_footwear,price,Total) values(346795,'dockside',450,450);
insert into bata (code_no,Name_of_footwear,price,Total) values(346796,'mocassion',550,550);
insert into bata (code_no,Name_of_footwear,price,Total) values(346797,'ring sandals',450,450);
insert into bata values(346798,'jump boots',1000,12,880);

select * from BATA;
create table custtable_BATA  
(
coined_no int,
code_no int,
name_of_dresses varchar(50),
price float,
total float,
payment float
);

use assignment;
create table Rocia
(
code_no int not null ,
Name_of_footwear varchar(50),
price float,
discount float,
Total float
);

insert into rocia values(346784,'boat shoes',700,20,569);
insert into rocia values(346785,'sandals',800,12,704);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346786,'sneakers',450,450);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346787,'clogs',450,450);
insert into rocia values(346788,'Jellies',800,12,704);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346766,'soft shoes',250,250);
insert into rocia values(346767,'flip flop',650,10,585);
insert into rocia values(346768,'gladitors',700,12,616);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346789,'crocs',450,450);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346790,'Mary janes',650,650);
insert into rocia values(346791,'crocs',1000,12,880);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346792,'kitten heel',800,800);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346793,'wedge',450,450);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346794,'slingbacks',450,450);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346795,'dockside',450,450);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346796,'mocassion',550,550);
insert into rocia (code_no,Name_of_footwear,price,Total) values(346797,'ring sandals',450,450);
insert into rocia values(346798,'jump boots',1000,12,880);

select * from Rocia;
create table custtable_rocia  
(
coined_no int,
code_no int,
name_of_dresses varchar(50),
price float,
total float,
payment float
);

create table Route
(
floor_route varchar(1000)
);



create table offer
(
id int primary key auto_increment,
offer_details varchar(1000)
);
drop table offer;
insert into offer(offer_details) values("LIFESTYLE -- SHIRT HAVE 20% OFFER FOR EVERY SHIRT");
select * from offer;