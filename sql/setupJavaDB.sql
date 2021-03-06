
CREATE TABLE Sizes
(
value varchar(255) NOT NULL,
PRIMARY KEY (value)
);

CREATE TABLE Customers
(
id int NOT NULL,
firstName varchar(255) NOT NULL,
lastName varchar(255) NOT NULL,
street varchar(255),
zip varchar(255),
city varchar(255),
phone varchar(255),
CONSTRAINT customer_pk PRIMARY KEY (id)
);

CREATE TABLE Employees
(
userName varchar(255) NOT NULL,
salary float,
phone varchar(255) NOT NULL,
position varchar(255) NOT NULL,
PRIMARY KEY (userName)
);

CREATE TABLE Tables
(
id int NOT NULL,
number varchar(255),
PRIMARY KEY (id)
);

CREATE TABLE Items
(
name varchar(255) NOT NULL,
description varchar(255),
PRIMARY KEY (name)
);

CREATE TABLE Prices
(
id int NOT NULL,
value float NOT NULL,
PRIMARY KEY (id),
size_value varchar(255),
FOREIGN KEY (size_value) REFERENCES Sizes(value)
	ON DELETE NO ACTION,
item_name varchar(255),
FOREIGN KEY (item_name) REFERENCES Items(name)
	ON DELETE CASCADE
);

CREATE TABLE OrderTypes
(
name varchar(255) NOT NULL,
target varchar(255),
PRIMARY KEY (name),
price_id int,
FOREIGN KEY (price_id) REFERENCES Prices(id)
	ON DELETE CASCADE
);

CREATE TABLE Orders
(
id int NOT NULL,
PRIMARY KEY (id),
Customer_id int,
FOREIGN KEY (customer_id) REFERENCES Customers(id),
Table_id int,
FOREIGN KEY (table_id) REFERENCES Tables(id),
order_type_name varchar(255),
FOREIGN KEY (order_type_name) REFERENCES OrderTypes(name)
	ON DELETE NO ACTION,
employee_userName varchar(255),
FOREIGN KEY (employee_userName) REFERENCES Employees(userName)
	ON DELETE NO ACTION
);

CREATE TABLE OrderedItems
(
id int NOT NULL,
order_id int NOT NULL,
item_name varchar(255) NOT NULL,
price_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (order_id) REFERENCES Orders(id)
	ON DELETE CASCADE,
FOREIGN KEY (item_name) REFERENCES Items(name)
	ON DELETE RESTRICT,
FOREIGN KEY (price_id) REFERENCES Prices(id)
	ON DELETE RESTRICT
);

INSERT INTO OrderTypes VALUES ('Delivery','Customer',null);
INSERT INTO OrderTypes VALUES ('Eat In','Table',null);
INSERT INTO OrderTypes VALUES ('Takeaway',null,null);

INSERT INTO Sizes VALUES ('Small');
INSERT INTO Sizes VALUES ('Medium');
INSERT INTO Sizes VALUES ('Large');

