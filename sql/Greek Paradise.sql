
CREATE TABLE Sizes
(
value varchar(255) NOT NULL,
PRIMARY KEY (value)
);

CREATE TABLE Prices
(
value float NOT NULL,
size_value varchar(255),
PRIMARY KEY (value),
FOREIGN KEY (size_value) REFERENCES Sizes(value)
);

CREATE TABLE Items
(
name varchar(255) NOT NULL,
Description varchar(255),
PRIMARY KEY (name),
price_value float NOT NULL,
FOREIGN KEY (price_value) REFERENCES Prices(value)
);

CREATE TABLE Customers
(
firstName varchar(255) NOT NULL,
lastName varchar(255) NOT NULL,
Street varchar(255),
ZIP varchar(255),
City varchar(255),
Phone varchar(255),
CONSTRAINT pk_Customer PRIMARY KEY (firstName, lastName)
);

CREATE TABLE Employees
(
userName varchar(255) NOT NULL,
Salary float,
Phone varchar(255) NOT NULL,
Position varchar(255) NOT NULL,
PRIMARY KEY (userName)
);

CREATE TABLE OrderTypes
(
name varchar(255) NOT NULL,
price_value int NOT NULL,
PRIMARY KEY (name),
FOREIGN KEY (price_value) REFERENCES Prices(value)
);

CREATE TABLE Orders
(
id int NOT NULL,
order_type_name varchar(255),
employee_userName varchar(255),
Customer_id int,
Table_id int,
PRIMARY KEY (id),
FOREIGN KEY (Customer_id) REFERENCES Customers(id),
FOREIGN KEY (Table_id) REFERENCES Tables(id),
FOREIGN KEY (order_type_name) REFERENCES OrderTypes(name),
FOREIGN KEY (employee_userName) REFERENCES Employees(userName)
);

CREATE TABLE OrderedItems
(
id int NOT NULL,
order_id int NOT NULL,
item_name varchar(255) NOT NULL,
item_size varchar(255),
PRIMARY KEY (id),
FOREIGN KEY (order_id) REFERENCES Orders(id),
FOREIGN KEY (item_name) REFERENCES Items(name),
FOREIGN KEY (item_size) REFERENCES Sizes(value)
);

CREATE TABLE Tables
(
id int NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO OrderTypes VALUES ('Delivery',0);
INSERT INTO OrderTypes VALUES ('Eat In',0);
INSERT INTO OrderTypes VALUES ('Takeaway',0);

INSERT INTO Sizes VALUES ('Small');
INSERT INTO Sizes VALUES ('Medium');
INSERT INTO Sizes VALUES ('Large');

COMMIT;

