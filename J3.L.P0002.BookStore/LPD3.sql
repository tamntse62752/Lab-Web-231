USE MASTER
GO

IF EXISTS (SELECT * FROM SYS.DATABASES WHERE NAME = 'LPD3') DROP DATABASE LPD3
GO

CREATE DATABASE LPD3
GO

USE LPD3
GO
CREATE TABLE tblRoles
(
	roleID nvarchar(10) NOT NULL PRIMARY KEY,
	roleName nvarchar(100) NOT NULL
)
GO

CREATE TABLE tblStatusUser
(
	statusUserID nvarchar(10) NOT NULL PRIMARY KEY,
	statusUsername nvarchar(100) NOT NULL
)
GO

CREATE TABLE tblUsers
(
	userID nvarchar(50) NOT NULL PRIMARY KEY,
	fullName nvarchar(100) NOT NULL,
	password nvarchar(50) NULL,
	roleID nvarchar(10) NOT NULL FOREIGN KEY
						REFERENCES tblRoles(roleID),
	statusUserID nvarchar(10) NOT NULL FOREIGN KEY
						REFERENCES tblStatusUser(statusUserID)
)
GO

CREATE TABLE tblCategories
(
	categoryID nvarchar(10) NOT NULL PRIMARY KEY,
	categoryName nvarchar(100) NOT NULL
)
GO

CREATE TABLE tblStatusBook
(
	statusBookID nvarchar(10) NOT NULL PRIMARY KEY,
	statusBookName nvarchar(100) NOT NULL
)
GO

CREATE TABLE tblBooks
(
	bookID nvarchar(10) NOT NULL PRIMARY KEY,
	title nvarchar(100) NOT NULL,
	image nvarchar(300) NOT NULL,
	description nvarchar(100) NOT NULL,
	price float NOT NULL,
	author nvarchar(100) NOT NULL,
	quantity int NOT NULL,
	createBookDate date NULL,
	statusBookID nvarchar(10) NOT NULL FOREIGN KEY
						REFERENCES tblStatusBook(statusBookID),
	categoryID nvarchar(10) NOT NULL FOREIGN KEY
						REFERENCES tblCategories(categoryID)
)
GO

CREATE TABLE tblDiscounts
(
	discountCode nvarchar(100) NOT NULL PRIMARY KEY,
	discountPercent float(2) NOT NULL,
	creatediscountDate date NOT NULL,
	statusDiscount bit NOT NULL
)
GO


CREATE TABLE tblOrders
(
	orderID int NOT NULL PRIMARY KEY,
	total float NOT NULL,
	orderDate date NOT NULL,
	discountCode nvarchar(100) NULL FOREIGN KEY
						REFERENCES tblDiscounts(discountCode),
	userID nvarchar(50) NOT NULL FOREIGN KEY
						REFERENCES tblUsers(userID)
)
GO

CREATE TABLE tblOrderDetails
(
	detailID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	quantity int NOT NULL,
	price float NOT NULL,
	orderID int NOT NULL FOREIGN KEY 
					REFERENCES tblOrders(orderID),
	bookID nvarchar(10) NOT NULL FOREIGN KEY
						REFERENCES tblBooks(bookID)
)
GO

USE LPD3
GO

INSERT INTO tblRoles VALUES(N'AD', N'Admin')
INSERT INTO tblRoles VALUES(N'US', N'User')
GO

INSERT INTO tblStatusUser VALUES(N'Active', N'Active')
INSERT INTO tblStatusUser VALUES(N'Inactive', N'Inactive')
GO

INSERT INTO tblUsers VALUES(N'admin',N'Admin',N'1',N'AD',  N'Active')
INSERT INTO tblUsers VALUES(N'user',N'User',N'1',N'US',  N'Active')
INSERT INTO tblUsers VALUES(N'user2',N'User2',N'1',N'US',  N'Active')
GO

INSERT INTO tblCategories VALUES(N'C',N'C')
INSERT INTO tblCategories VALUES(N'J',N'Java')
INSERT INTO tblCategories VALUES(N'P',N'Python')
GO

INSERT INTO tblStatusBook VALUES(N'Active', N'Active')
INSERT INTO tblStatusBook VALUES(N'Inactive', N'Inactive')
GO

INSERT INTO tblDiscounts VALUES(N'CODE1',0.5,CAST(N'2021-06-25' AS Date), 1)
INSERT INTO tblDiscounts VALUES(N'CODE2',0.3,CAST(N'2021-06-27' AS Date), 1)
INSERT INTO tblDiscounts VALUES(N'CODE3',0.2,CAST(N'2021-06-28' AS Date), 1)
INSERT INTO tblDiscounts VALUES(N'CODE4',0.5,CAST(N'2021-06-25' AS Date), 1)
INSERT INTO tblDiscounts VALUES(N'CODE5',0.3,CAST(N'2021-06-27' AS Date), 1)
INSERT INTO tblDiscounts VALUES(N'CODE6',0.2,CAST(N'2021-06-28' AS Date), 1)
GO

INSERT INTO tblBooks VALUES(N'C-001',N'Basic C programming',N'c1.jpg',N'Good1C',55,N'Duy1C',20,CAST(N'2021-03-20' AS Date),N'Active',N'C')
INSERT INTO tblBooks VALUES(N'C-002',N'Advanced C programming',N'c2.jpg',N'Good2C',75,N'Duy2C',20,CAST(N'2021-04-21' AS Date),N'Active',N'C')
INSERT INTO tblBooks VALUES(N'C-003',N'300 exercises C',N'c3.jpg',N'Good3C',100,N'Duy3C',20,CAST(N'2021-03-30' AS Date),N'Active',N'C')
INSERT INTO tblBooks VALUES(N'C-004',N'Basic to Advanced C',N'c4.jpg',N'Good4C',65,N'Duy4C',20,CAST(N'2021-06-10' AS Date),N'Active',N'C')

INSERT INTO tblBooks VALUES(N'J-001',N'Basic Java programming',N'j1.jpg',N'Good1Java',90,N'Duy1Java',20,CAST(N'2021-02-20' AS Date),N'Active',N'J')
INSERT INTO tblBooks VALUES(N'J-002',N'Advanced Java programming',N'j2.jpg',N'Good2Java',50,N'Duy2Java',20,CAST(N'2021-02-22' AS Date),N'Active',N'J')
INSERT INTO tblBooks VALUES(N'J-003',N'300 exercises Java',N'c3.jpg',N'Good3Java',110,N'Duy3Java',20,CAST(N'2021-04-12' AS Date),N'Active',N'J')
INSERT INTO tblBooks VALUES(N'J-004',N'Basic to Advanced Java',N'j4.jpg',N'Good4Java',45,N'Duy4Java',20,CAST(N'2021-01-15' AS Date),N'Active',N'J')

INSERT INTO tblBooks VALUES(N'P-001',N'Basic Python programming',N'p1.jpg',N'Good1Python',35,N'Duy1Python',20,CAST(N'2021-02-22' AS Date),N'Active',N'P')
INSERT INTO tblBooks VALUES(N'P-002',N'Advanced Python programming',N'p2.jpg',N'Good2Python',55,N'Duy2Python',20,CAST(N'2021-03-26' AS Date),N'Active',N'P')
INSERT INTO tblBooks VALUES(N'P-003',N'300 exercises Python',N'c3.jpg',N'Good3Python',125,N'Duy3Python',20,CAST(N'2021-05-21' AS Date),N'Active',N'P')
INSERT INTO tblBooks VALUES(N'P-004',N'Basic to Advanced Python',N'p4.jpg',N'Good4Python',80,N'Duy4Python',20,CAST(N'2021-04-20' AS Date),N'Active',N'P')
GO
