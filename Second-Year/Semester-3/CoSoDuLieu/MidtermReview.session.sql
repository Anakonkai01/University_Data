
CREATE TABLE LoaiNGK (
    Maloai INT PRIMARY KEY,
    Tenloai VARCHAR(100) UNIQUE
);

CREATE TABLE NGK (
    MaNGK INT PRIMARY KEY,
    TenNGK VARCHAR(100) UNIQUE,
    DVT VARCHAR(20) CHECK (DVT IN ('chai', 'lon', 'thùng', 'kết')),
    Soluong INT CHECK (Soluong > 0),
    Dongia DECIMAL(10, 2) CHECK (Dongia > 0),
    MaloaiNGK INT,
    FOREIGN KEY (MaloaiNGK) REFERENCES LoaiNGK(Maloai)
);


CREATE TABLE Khachhang (
    MsKH INT PRIMARY KEY,
    Hoten VARCHAR(100),
    Diachi VARCHAR(200),
    Dienthoai VARCHAR(20) DEFAULT 'chưa có'
);


CREATE TABLE Hoadon (
    Sohd INT PRIMARY KEY,
    MsKH INT,
    Nhanvien VARCHAR(100),
    Ngaylap DATE DEFAULT CURRENT_DATE,  -- Use CURRENT_DATE in PostgreSQL
    FOREIGN KEY (MsKH) REFERENCES Khachhang(MsKH)
);


CREATE TABLE CTHD (
    Sohd INT,
    MaNGK INT,
    Soluong INT CHECK (Soluong > 0),
    Dongia DECIMAL(10, 2),
    PRIMARY KEY (Sohd, MaNGK),
    FOREIGN KEY (Sohd) REFERENCES Hoadon(Sohd),
    FOREIGN KEY (MaNGK) REFERENCES NGK(MaNGK)
);


ALTER TABLE CTHD
ADD Thanhtien INT;

ALTER TABLE CTHD
ADD CONSTRAINT chk_thanhtien CHECK (Thanhtien > 0);


ALTER TABLE CTHD
DROP CONSTRAINT fk_cthd_ngk;  -- assuming the constraint name is fk_cthd_ngk

ALTER TABLE CTHD
DROP CONSTRAINT fk_cthd_hoadon;  -- assuming the constraint name is fk_cthd_hoadon


ALTER TABLE CTHD
ADD CONSTRAINT chk_thanhtien CHECK (Thanhtien > 0);


INSERT INTO LoaiNGK (Maloai, Tenloai)
VALUES
(1, 'Nước ngọt'),
(2, 'Nước có ga'),
(3, 'Nước trái cây'),
(4, 'Trà'),
(5, 'Cà phê'),
(6, 'Sữa'),
(7, 'Nước khoáng'),
(8, 'Nước tăng lực'),
(9, 'Bia'),
(10, 'Rượu');

INSERT INTO NGK (MaNGK, TenNGK, DVT, Soluong, Dongia, MaloaiNGK)
VALUES
(1, 'Coca Cola', 'lon', 100, 10000, 2),  -- Nước có ga
(2, 'Pepsi', 'lon', 150, 12000, 2),      -- Nước có ga
(3, 'Nước Cam', 'chai', 50, 15000, 3),   -- Nước trái cây
(4, 'Trà Xanh 0 Độ', 'chai', 200, 9000, 4), -- Trà
(5, 'Cà phê đen', 'chai', 80, 18000, 5), -- Cà phê
(6, 'Sữa tươi Vinamilk', 'chai', 100, 14000, 6), -- Sữa
(7, 'Aquafina', 'chai', 300, 8000, 7), -- Nước khoáng
(8, 'Number 1', 'lon', 90, 11000, 8),  -- Nước tăng lực
(9, 'Tiger Beer', 'lon', 70, 22000, 9), -- Bia
(10, 'Rượu vang Đà Lạt', 'chai', 30, 50000, 10); -- Rượu

INSERT INTO Khachhang (MsKH, Hoten, Diachi, Dienthoai)
VALUES
(1, 'Nguyen Van A', 'Hanoi', '0123456789'),
(2, 'Tran Thi B', 'HCM City', '0987654321'),
(3, 'Le Van C', 'Da Nang', '0912345678'),
(4, 'Pham Thi D', 'Hue', '0908765432'),
(5, 'Hoang Van E', 'Haiphong', '0981234567'),
(6, 'Nguyen Thi F', 'Can Tho', '0934567890'),
(7, 'Phan Van G', 'Quang Nam', '0918765432'),
(8, 'Tran Van H', 'Nha Trang', '0938765432'),
(9, 'Le Thi I', 'Vung Tau', '0901234567'),
(10, 'Doan Van J', 'Binh Duong', '0986543210');

INSERT INTO Hoadon (Sohd, MsKH, Nhanvien, Ngaylap)
VALUES
(1, 1, 'Employee 1', '2023-01-01'),
(2, 2, 'Employee 2', '2023-02-05'),
(3, 3, 'Employee 3', '2023-03-12'),
(4, 4, 'Employee 4', '2023-04-25'),
(5, 5, 'Employee 5', '2023-05-07'),
(6, 6, 'Employee 6', '2023-06-15'),
(7, 7, 'Employee 7', '2023-07-20'),
(8, 8, 'Employee 8', '2023-08-30'),
(9, 9, 'Employee 9', '2023-09-10'),
(10, 10, 'Employee 10', '2023-10-05');

INSERT INTO CTHD (Sohd, MaNGK, Soluong, Dongia, Thanhtien)
VALUES
(1, 1, 5, 10000, 5 * 10000),  -- Coca Cola
(1, 2, 3, 12000, 3 * 12000),  -- Pepsi
(2, 3, 2, 15000, 2 * 15000),  -- Nước Cam
(3, 4, 6, 9000, 6 * 9000),    -- Trà Xanh 0 Độ
(4, 5, 4, 18000, 4 * 18000),  -- Cà phê đen
(5, 6, 7, 14000, 7 * 14000),  -- Sữa tươi Vinamilk
(6, 7, 8, 8000, 8 * 8000),    -- Aquafina
(7, 8, 5, 11000, 5 * 11000),  -- Number 1
(8, 9, 4, 22000, 4 * 22000),  -- Tiger Beer
(9, 10, 2, 50000, 2 * 50000); -- Rượu vang Đà Lạt

INSERT INTO CTHD (Sohd, MaNGK, Soluong, Dongia, Thanhtien)
VALUES
(10, 7, 10, 8000, 10 * 8000);    -- Aquafina



-- CAU 2

-- b
UPDATE ngk 
SET dongia = dongia + 10000
WHERE dvt = 'lon'

-- c
DELETE FROM Khachhang
WHERE mskh NOT IN(
    SELECT DISTINCT mskh from hoadon
    WHERE EXTRACT(YEAR FROM ngaylap) > 2010
)

--d
DELETE FROM ngk 
WHERE Soluong = 0

--e
UPDATE NGK
SET Dongia = CASE
    WHEN Dongia + 500000 > 500000 then 500000
    ELSE Dongia + 500000
END
WHERE DVT = 'thung'

--f
SELECT 
    kh.mskh,
    kh.Hoten,
    COUNT(ct.soluong) as soluot
FROM Khachhang AS kh
JOIN Hoadon as hd on hd.mskh = kh.mskh
JOIN CTHD as ct on ct.sohd = hd.sohd
WHERE EXTRACT(YEAR FROM hd.ngaylap) = 2023
GROUP BY kh.mskh,kh.Hoten
ORDER BY soluot DESC

-- g
SELECT 
    n.mangk,
    n.TenNGK,
    SUM(ct.soluong) as soluongban
FROM ngk as n 
JOIN CTHD as ct on ct.mangk = n.mangk 
JOIN hoadon as hd on ct.sohd = hd.sohd
WHERE EXTRACT(YEAR FROM hd.ngaylap) = 2023
GROUP BY n.mangk,n.TenNGK
ORDER BY soluongban DESC

--h (cau nay thua)
SELECT 
    -- TOP 1 
    n.mangk,
    n.TenNGK,
    SUM(ct.soluong) as soluongban
FROM ngk as n 
JOIN CTHD as ct on ct.mangk = n.mangk 
JOIN hoadon as hd on ct.sohd = hd.sohd
WHERE EXTRACT(YEAR FROM hd.ngaylap) = 2023
GROUP BY n.mangk,n.TenNGK
ORDER BY soluongban DESC 

-- h dung cte de giai quyet
WITH SalesSummary AS (
    SELECT N.MaNGK, N.TenNGK, SUM(C.Soluong) AS TotalSold
    FROM CTHD C
    JOIN Hoadon H ON C.Sohd = H.Sohd
    JOIN NGK N ON C.MaNGK = N.MaNGK
    WHERE EXTRACT(YEAR FROM hd.ngaylap) = 2023
    GROUP BY N.MaNGK, N.TenNGK
)
SELECT MaNGK, TenNGK, TotalSold
FROM SalesSummary
WHERE TotalSold = (SELECT MAX(TotalSold) FROM SalesSummary);

-- i
SELECT 
    hd.sohd,
    hd.ngaylap,
    SUM(ct.dongia * ct.soluong) as tongtien
FROM hoadon as hd 
JOIN CTHD as ct on ct.sohd = hd.sohd 
GROUP BY hd.sohd, hd.ngaylap
HAVING SUM(ct.dongia * ct.soluong) > 10000000

