drop database if EXISTS db_main;
create database db_main CHARACTER SET utf8 COLLATE utf8_general_ci;
use db_main;
	create table restaurant(
	id int(10) primary key auto_increment,
	images varchar(50),
    name varchar(50),
    location varchar(50),
    rating float(10),
    contactinfo varchar(50),
    
    style varchar(50),
    average_price float(10),
    location_x float(10),
    location_y float(10),
    business_hours varchar(50)
);

insert into restaurant(id,images,name,location,rating,contactinfo,style,average_price,location_x,location_y,business_hours) 
values(1,"https://6657;;https://8081","肯德基","路北边",3.5,12138,"甜;;辣",123.1,15.0,73.1,"9:00:24:00");

	create table dish(
	id int(10) primary key auto_increment,
	images varchar(50),
    store varchar(50),
    rating float(10),
    style varchar(50),
    restaurant_id int(10),
    name varchar(50),
    average_price float(10)
);
insert into dish(id,images,store,rating,style,restaurant_id,name,average_price) 
values(101,"https://111","肯德基",5.0,"甜",1,"小鸡块",17.33);
insert into dish(id,images,store,rating,style,restaurant_id,name,average_price) 
values(102,"https://112","肯德基",1.0,"甜",1,"中鸡块",18.9);
insert into dish(id,images,store,rating,style,restaurant_id,name,average_price) 
values(103,"https://113","肯德基",3.0,"甜",1,"大鸡块",21.33);

	create table review(
	id int(10) primary key auto_increment,
    content varchar(50),
	images varchar(50),
    rating float(10),
    style varchar(50),
    userID int(10),
    restaurant_id int(10),
    time int(10),
    average_price float(10)
);
insert into review(id,content,images,rating,style,userID,restaurant_id,time,average_price) 
values(11,"好难吃","https://111",1.0,"甜",123,1,20230103,17.33);
insert into review(id,content,images,rating,style,userID,restaurant_id,time,average_price) 
values(12,"难吃","https://111",0.0,"甜",123,1,20240103,17.33);
insert into review(id,content,images,rating,style,userID,restaurant_id,time,average_price) 
values(13,"一般般","https://111",3.0,"甜",123,1,20200103,17.33);
insert into review(id,content,images,rating,style,userID,restaurant_id,time,average_price) 
values(14,"好吃","https://111",5.0,"甜",123,1,20210103,17.33);


	create table review2(
	id int(10) primary key auto_increment,
    content varchar(50),
	images varchar(50),
    rating float(10),
    style varchar(50),
    userID int(10),
    dish_id int(10),
    time int(10),
    average_price float(10)
);
insert into review2(id,content,images,rating,style,userID,dish_id,time,average_price) 
values(110,"好难吃","https://111",1.0,"甜",123,101,20230103,100);
insert into review2(id,content,images,rating,style,userID,dish_id,time,average_price) 
values(120,"难吃","https://111",0.0,"-甜",123,101,20240103,100);
insert into review2(id,content,images,rating,style,userID,dish_id,time,average_price) 
values(130,"一般般","https://111",3.0,"--甜",123,101,20200103,100);
insert into review2(id,content,images,rating,style,userID,dish_id,time,average_price) 
values(140,"好吃","https://111",5.0,"---甜",123,101,20210103,100);




