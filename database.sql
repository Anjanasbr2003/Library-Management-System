drop database lmsys;

CREATE DATABASE IF NOT EXISTS lmsys;

USE lmsys;

CREATE TABLE IF NOT EXISTS book (
B_id char(5) primary key,
B_name varchar(100),
Author varchar(50),
isbn char(13),
shelf_no int,
count int
);

CREATE TABLE IF NOT EXISTS Users (
U_id char(5) PRIMARY KEY,
U_name varchar(100) NOT NULL,
U_type ENUM('admin','librarian','member'),
U_password varchar(128),
U_phone char(10),
U_email varchar(100),
U_address varchar(200)
);

CREATE TABLE IF NOT EXISTS reservation(
R_id int AUTO_INCREMENT PRIMARY KEY,
B_id char(5) ,
U_id char(5) ,
FOREIGN KEY (B_id) REFERENCES book(B_id),
FOREIGN KEY (U_id) REFERENCES users(U_id)
);

CREATE TABLE IF NOT EXISTS BorrowedBooks (
Borrow_id char(5) PRIMARY KEY,
Borrow_date date,
Borrow_time time,
B_id char(5) ,
U_id char(5) ,
FOREIGN KEY (B_id) REFERENCES book(B_id),
FOREIGN KEY (U_id) REFERENCES users(U_id)
);

CREATE TABLE IF NOT EXISTS Fine(
F_id char(4) PRIMARY KEY,
amount double(6,2) NOT NULL,
Payment_status ENUM('P','NP'),
B_id char(5) ,
U_id char(5) ,
FOREIGN KEY (B_id) REFERENCES book(B_id),
FOREIGN KEY (U_id) REFERENCES users(U_id)
);

INSERT INTO book
(B_id,B_name,Author,isbn,shelf_no,count)
VALUES
("B001","Computer Programming","Athula Adhikari","0123456789cp",1,5),
("B002","Wireless Computing","sun Perera","0123456789wc",1,3),
("B003","Mathematics","Nimal Perera","0123456789mt",4,5),
("B004","Networking","Jane Cathorine","0123456789nt",3,1),
("B005","Bussiness Management","Anil Ranasinghe","0123456789bm",2,4);

INSERT INTO users
(U_id,U_name,U_type,U_password,U_phone,U_email,U_address)
VALUES
("AD001","Chathura","admin","admin1234","0714589365","admin@gmail.com","Kuruwita,Ratnapura"),
("LB001","Nimal","Librarian","lib1234","0757864346","librarian@gmail.com","Nugegoda,Colombo"),
("ME001","Nishi","member","nishi1234","0745687124","nishi@gmail.com","Homagama,Colombo");

INSERT INTO fine
(F_id,amount,Payment_status,B_id,U_id)
VALUES
("F001",200.00,"NP","B001","ME001");

INSERT INTO reservation
(R_id,B_id,U_id)
VALUES
(1,"B002","ME001"),
(2,"B003","ME001");

INSERT INTO BorrowedBooks
(Borrow_id,Borrow_date,Borrow_time,B_id,U_id)
VALUES
("BI01",CURDATE(),CURTIME(),"B002","ME001"),
("BI02",CURDATE(),CURTIME(),"B003","ME001");