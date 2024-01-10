Drop  database Museum;
Create database Museum;
use Museum;
# create Event_s table 5
Create table Event_s(
	E_id int not null,
	Location VarChar(30),
	Dates Date,
	Times Time,
	Primary Key(E_id));

insert into Event_s values(1,'Birzeit','2022-05-28','01:00:00');
insert into Event_s values(2,'Birzeit','2019-05-25','01:00:00');
insert into Event_s values(3,'Birzeit','2021-06-21','01:00:00');
insert into Event_s values(4,'Birzeit','2022-05-28','01:00:00');
insert into Event_s values(5,'Birzeit','2019-05-25','01:00:00');
insert into Event_s values(6,'Birzeit','2021-06-21','01:00:00');
insert into Event_s values(7,'Birzeit','2022-05-28','01:00:00');
insert into Event_s values(8,'Birzeit','2019-05-25','01:00:00');
insert into Event_s values(9,'Birzeit','2021-06-21','01:00:00');

Create table Customer(
Cid int not null,
Cname varchar(25),
age int,
address varchar(30),
gender char,
E_id int,
primary key (Cid),
Foreign key(E_id) References Event_s(E_id)
);

insert into Customer values(1,'Kevin Hart',34,'Palestine','M',2);
insert into Customer values(2,'julia wiston',26,'Palestine','F',2);
insert into Customer values(3,'Lisa Cadi',22,'Palestine','F',1);
insert into Customer values(4,'Lisa Cadi',22,'Palestine','F',1);
insert into Customer values(5,'Kevin Hart',34,'Palestine','M',2);
insert into Customer values(6,'julia wiston',26,'Palestine','F',2);
insert into Customer values(7,'Lisa Cadi',22,'Palestine','F',1);



 # create Items table   
CREATE TABLE Items(
	Item_id int primary key,
    Cid int not null,
	Item_Description varchar(60),
	Item_useage varchar(30),
	Item_name varchar(30),
    E_id int not null,
    Artistes_Name varchar(25),
    Item_Price real,
    Foreign key(E_id) References Event_s(E_id),
    Foreign key(Cid) References Customer(Cid)
    );
insert into Items values(1,1,'Dress','USAGE 1','NAME 1',1,'Mohammad',200);
insert into Items values(2,2,'Dress','USAGE 2','NAME 2',2,'Ahmad',300);
insert into Items values(3,3,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(4,4,'Dress','USAGE 1','NAME 1',1,'Mohammad',200);
insert into Items values(5,5,'Dress','USAGE 2','NAME 2',2,'Ahmad',300);
insert into Items values(6,6,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(10,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(11,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(12,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(13,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(14,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(15,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(16,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(17,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(18,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(19,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(20,1,'Dress','USAGE 3','NAME 3',3,'John',400);
insert into Items values(21,1,'Dress','USAGE 3','NAME 3',3,'John',400);


Select * from items;


insert into Items values(12,1,'Dress','USAGE 3','NAME 3',3,'John',400); 

    
CREATE TABLE Clothes (
	Item_id int not null, -- from parent table 
	Clothes_id int not null,
    Clothes_Size varchar(60),
    Clothes_Color varchar(60),
    Gender varchar(60), 
	Clothes_Description varchar(60),
    PRIMARY KEY (Clothes_id,Item_id),
    FOREIGN KEY (Item_id) REFERENCES Items (Item_id)); 
  
insert into Clothes values(1,1,'F','RED','M','Dress');
insert into Clothes values(2,2,'M','BLACK','M','Scarf');
insert into Clothes values(3,3,'M','WHITE','M','Hijab');
insert into Clothes values(10,10,'M','WHITE','M','Hijab');
insert into Clothes values(13,10,'M','WHITE','M','Hijab');
insert into Clothes values(14,10,'M','WHITE','M','Hijab');
insert into Clothes values(15,10,'M','WHITE','M','Hijab');

Select * from clothes;
SELECT * FROM Clothes;

    
    # create book table
CREATE TABLE Books (
	Item_id int not null, -- from parent table 
	Books_id int not null,
    PRIMARY KEY (Books_id ,Item_id),
    FOREIGN KEY (Item_id) REFERENCES Items (Item_id),
	Page_num int,
	Edition varchar(30),
	location varchar(60));

# create masterpieces & painting table
CREATE TABLE Masterpieces(
	Item_id int not null, -- from parent table 
	Mp_id int not null,
    PRIMARY KEY (Mp_id,Item_id),
    FOREIGN KEY (Item_id) REFERENCES Items (Item_id),
    Wight int,
    Meterial varchar(30),
	Masterpieces_Name  varchar(30),
    Artistes_Name varchar(25)
);
insert into Masterpieces values(1,1,23,'FABRICA','NAME 1','Shown Mendes');


insert into Masterpieces values(13,2,23,'FABRICA','NAME 2','Shown Mendes');
insert into Masterpieces values(13,3,23,'FABRICA','NAME 3','Shown Mendes');
insert into Masterpieces values(13,4,23,'FABRICA','NAME 4','Shown Mendes');
insert into Masterpieces values(13,7,23,'FABRICA','NAME 5','Shown Mendes');

Select * FROM Masterpieces;






Drop table Masterpieces;
# create clothing table


    

    

Create table Employees(
Ename varchar(25),
Email varchar(30),
address varchar(30),
gender char,
Eid int not null,
EPassword varchar(30),
primary key (Eid)
);