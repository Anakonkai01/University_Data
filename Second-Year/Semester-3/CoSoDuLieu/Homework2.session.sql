

CREATE TABLE CarType(
    TypeID INT PRIMARY KEY,
    TypeName VARCHAR(50) UNIQUE
)

CREATE TABLE Car(
    CarID INT PRIMARY KEY,
    Model VARCHAR(50) UNIQUE,
    Brand VARCHAR(100),
    PricePerDay DECIMAL(18,2) CHECK (PricePerDay > 0),
    TypeID INT,
    FOREIGN KEY (TypeID) REFERENCES CarType(TypeID)
)


CREATE TABLE Customer(
    CustomerID INT PRIMARY KEY,
    FullName VARCHAR(100),
    Address VARCHAR(100),
    Phone VARCHAR(50) DEFAULT 'not available'
)

CREATE TABLE Rental(
    RentalID INT PRIMARY KEY,
    CustomerID INT,
    CarID INT,
    StartDate DATE DEFAULT CURRENT_DATE,
    EndDate DATE CHECK(EndDate > StartDate),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (CarID) REFERENCES Car(CarID)
)

CREATE TABLE RentalDetails(
    RentalID INT,
    CarID INT,
    TotalDays INT CHECK(TotalDays > 0),
    TotalPrice DECIMAL(18,2) CHECK (TotalPrice > 0),
    PRIMARY KEY(RentalID,CarID),
    FOREIGN KEY (RentalID) REFERENCES Rental(RentalID)
)


INSERT INTO CarType (TypeID, TypeName)
VALUES
(1, 'SUV'),
(2, 'Sedan'),
(3, 'Luxury');

INSERT INTO Car (CarID, Model, Brand, PricePerDay, TypeID)
VALUES
(101, 'X5', 'BMW', 150, 1),  -- TypeID 1 references SUV
(102, 'Civic', 'Honda', 70, 2),  -- TypeID 2 references Sedan
(103, 'S-Class', 'Mercedes', 300, 3);  -- TypeID 3 references Luxury


INSERT INTO Customer (CustomerID, FullName, Address, Phone)
VALUES
(1, 'John Doe', '123 Elm St', '123-456-7890'),
(2, 'Jane Smith', '456 Oak St', '234-567-8901'),
(3, 'Mike Johnson', '789 Pine St', '345-678-9012');


INSERT INTO Rental (RentalID, CustomerID, CarID, StartDate, EndDate)
VALUES
(1001, 1, 101, '2023-10-01', '2023-10-07'),  -- John Doe rents BMW X5 from 1st to 7th Oct
(1002, 2, 102, '2023-10-05', '2023-10-08'),  -- Jane Smith rents Honda Civic from 5th to 8th Oct
(1003, 3, 103, '2023-10-10', '2023-10-15');  -- Mike Johnson rents Mercedes S-Class from 10th to 15th Oct


INSERT INTO RentalDetails (RentalID, CarID, TotalDays, TotalPrice)
VALUES
(1001, 101, 7, 7 * 150),  -- 7 days for BMW X5, TotalPrice = 7 * 150
(1002, 102, 3, 3 * 70),  -- 3 days for Honda Civic, TotalPrice = 3 * 70
(1003, 103, 5, 5 * 300);  -- 5 days for Mercedes S-Class, TotalPrice = 5 * 300




-- Remove any customers who have not rented a car in the past 3 years.

-- List all rentals made in 2023, showing:

-- RentalID
-- CustomerID
-- Total number of days each rental lasted.
-- Update the price per day for all cars in the "Luxury" category by 50%, but ensure no price exceeds 1,000 per day. If the new price exceeds 1,000, cap it at 1,000.


-- 2
UPDATE car 
SET priceperday = priceperday*1.20
WHERE model='SUV'

--3
DELETE FROM customer
WHERE CustomerID NOT IN(
    SELECT CustomerID FROM rental
    WHERE StartDate > (CURRENT_DATE - INTERVAL '3 years')
)

--4
SELECT 
    r.RentalID,
    r.CustomerID,
    SUM(TotalDays) AS TotalDayRent
FROM rental as r 
JOIN RentalDetails as rd ON rd.RentalID=r.RentalID
WHERE EXTRACT(YEAR FROM r.StartDate) = 2023
GROUP BY r.RentalID,r.CustomerID 

--5
UPDATE car 
set priceperday = CASE
        when priceperday*1.5 > 1000 THEN 1000
        else priceperday*1.5
    END
WHERE TypeID IN (
    select TypeId from cartype 
    WHERE TypeName = 'Luxury'
)