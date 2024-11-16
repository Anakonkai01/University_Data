


CREATE TABLE BookCategory(
    CategoryID INT PRIMARY KEY,
    CategoryName VARCHAR(100) UNIQUE
)

CREATE TABLE Book(
    BookID INT PRIMARY KEY,
    Title VARCHAR(100) UNIQUE,
    Price DECIMAL(10,2) CHECK(Price > 0),
    Stock INT CHECK(Stock > 0),
    CategoryID INT,
    FOREIGN KEY (CategoryID) REFERENCES BookCategory(CategoryID)
)

ALTER TABLE Book ADD Author varchar(100);
CREATE TABLE Customer(
    CustomerID INT PRIMARY KEY,
    Phone VARCHAR(11) DEFAULT 'unknown'
)

ALTER TABLE Customer ADD Fullname VARCHAR(100);
ALTER TABLE Customer ADD Address VARCHAR(100);


CREATE TABLE "Order"(
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
)

CREATE TABLE OrderDetail(
    OrderID INT,
    BookID INT,
    PRIMARY KEY(OrderID,BookID),
    Quantity INT CHECK(Quantity > 0),
    FOREIGN KEY (OrderID) REFERENCES "Order"(OrderID),
    FOREIGN KEY (BookID) REFERENCES Book(BookID)
)

ALTER TABLE OrderDetail ADD Price DECIMAL(10,2)
ALTER TABLE OrderDetail ADD CONSTRAINT ck_price CHECK(Price  > 0)


INSERT INTO Customer (CustomerID, FullName, Address, Phone)
VALUES 
(1, 'John Doe', '123 Main St', '14567890'),
(2, 'Jane Smith', '456 Oak St', '25678901'),
(3, 'Alice Brown', '789 Pine St', '3459012');


INSERT INTO "Order" (OrderID, CustomerID)
VALUES 
(1001, 1),
(1002, 2),
(1003, 3);


INSERT INTO OrderDetail (OrderID, BookID, Quantity, Price)
VALUES 
(1001, 201, 2, 150),
(1001, 202, 1, 200),
(1002, 203, 3, 100),
(1003, 201, 1, 150);


INSERT INTO Book (BookID, Title, Author, Price, Stock, CategoryID)
VALUES 
(201, 'The Great Gatsby', 'F. Scott Fitzgerald', 150, 50, 1),
(202, '1984', 'George Orwell', 200, 40, 2),
(203, 'To Kill a Mockingbird', 'Harper Lee', 100, 60, 1);

INSERT INTO BookCategory (CategoryID, CategoryName)
VALUES 
(1, 'Fiction'),
(2, 'Science Fiction'),
(3, 'Biography');




-- 2
UPDATE book
SET price = price + price*0.1
WHERE author='F. Scott Fitzgerald'

-- 3
DELETE FROM Customer
WHERE CustomerID NOT IN(
    SELECT CustomerID FROM "Order"
    WHERE EXTRACT(YEAR FROM OrderDate) > 2018
)

-- 4
SELECT 
    o.OrderID,
    o.CustomerID,
    SUM(od.Quantity) as TotalBook
FROM "Order" as o 
JOIN OrderDetail as od ON o.OrderID = od.OrderID
WHERE EXTRACT(YEAR FROM o.OrderDate) = 2024 
GROUP BY o.OrderID, o.CustomerID

-- 5
UPDATE book
SET price = CASE
    WHEN price*0.2 > 500 THEN 500
    ELSE price*0.2
    END
WHERE CategoryID IN(
    SELECT CategoryID FROM BookCategory
    WHERE CategoryName = 'Fiction'
)

