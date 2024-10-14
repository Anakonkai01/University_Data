CREATE TABLE Sinhvien (
    Masv VARCHAR(50) PRIMARY KEY,
    Ten VARCHAR(100),
    QueQuan VARCHAR(100),
    NgaySinh DATE,
    HocLuc DECIMAL(10,2) CHECK(HocLuc >= 0 AND HocLuc <= 10)
);


CREATE TABLE Detai(
    Madt VARCHAR(50) PRIMARY KEY,
    TenDeTai VARCHAR(200),
    ChuNhiemDetai VARCHAR(100),
    KinhPhi DECIMAL(10,2) DEFAULT 0 CHECK(KinhPhi < 100000000)
);

CREATE TABLE Sinhvien_Detai(
    Masv VARCHAR(50),
    Madt VARCHAR(50),
    PRIMARY KEY(Masv,Madt),
    FOREIGN KEY (Masv) REFERENCES Sinhvien(Masv),
    FOREIGN KEY (Madt) REFERENCES Detai(Madt),
    NoiThucTap VARCHAR(100),
    QuangDuong DECIMAL(10,2),
    KetQua DECIMAL(10,2) CHECK (KetQua >= 0 AND KetQua <= 10)
);


INSERT INTO sinhvien VALUES
('SV001','Nguyen Van A','Ha Noi','2005-08-01',9.1),
('SV002','Trinh Thanh B','Hue','2004-12-05',8.5),
('SV003','Pham Thi Ngo','Da Nang','2006-07-15',8.8),
('SV004','Le Thanh C','Can Tho','2005-09-09',9.2),
('SV005','Nguyen Thi D','Da Nang','2006-03-12',8.7);


INSERT INTO detai VALUES
('DT001','Thiet kế web','Nguyen Van A',5000000),
('DT002','Phát triển ứng dụng','Trinh Thanh B',3000000),
('DT003','Thiết kế ảo hóa','Pham Thi Ngo',2500000),
('DT004','Quản lý dữ liệu','Le Thanh C',4000000),
('DT005','Phát triển ứng dụng hệ thống','Nguyen Thi D',3500000);

INSERT INTO sinhvien_detai VALUES
('SV001','DT001','Nha Trang',5,8.5),
('SV001','DT002','Da Nang',3,9.2),
('SV002','DT003','Da Nang',2,8.8),
('SV003','DT004','Da Nang',4,9.1),
('SV004','DT002','Da Nang',5,8.7),
('SV005','DT003','Da Nang',3,9.0);



-- cau 2
-- a
-- Cau lenh trong postgres
CREATE VIEW cau2_a AS
SELECT * FROM sinhvien
WHERE EXTRACT(YEAR FROM AGE(NgaySinh)) < 20 AND hocluc > 8.5;
-- Cau lenh trong SQL server
-- CREATE VIEW cau2_a AS
-- SELECT * FROM Sinhvien
-- WHERE DATEDIFF(YEAR, NgaySinh, GETDATE()) < 20 AND HocLuc > 8.5;

-- b
CREATE VIEW cau2_b AS
SELECT * FROM detai 
WHERE KinhPhi > 1000000;


-- c
-- postgres
CREATE VIEW cau2_c AS
SELECT sinhvien.*, sinhvien_detai.KetQua FROM Sinhvien
JOIN sinhvien_detai ON sinhvien.Masv = sinhvien_detai.Masv
WHERE sinhvien_detai.KetQua >= 8 AND EXTRACT(YEAR FROM AGE(Sinhvien.NgaySinh)) < 20 AND sinhvien_detai.KetQua > 8;
-- sql server 
-- CREATE VIEW cau2_c AS
-- SELECT sv.*, sv_dt.KetQua
-- FROM Sinhvien sv 
-- JOIN Sinhvien_Detai sv_dt ON sv.Masv = sv_dt.Masv
-- WHERE sv_dt.KetQua >= 8 AND DATEDIFF(YEAR, sv.NgaySinh, GETDATE()) < 20 AND sv_dt.Ket



-- d
CREATE VIEW cau2_d AS
SELECT dt.ChuNhiemDetai 
FROM Detai dt
JOIN Sinhvien_Detai sv_dt ON dt.Madt=sv_dt.Madt
JOIN Sinhvien sv ON sv.Masv = sv_dt.Masv
WHERE sv.QueQuan='TPHCM';

-- e
CREATE VIEW cau2_e AS
SELECT * 
FROM Sinhvien
WHERE QueQuan='Hai Phong' AND EXTRACT(YEAR FROM NgaySinh) < 1980; -- year ko exist trong postgres

-- f
CREATE VIEW cau2_f AS
SELECT sv.Masv, sv.Ten ,AVG(sv_dt.KetQua) as DiemTrungBinh
FROM Sinhvien sv 
JOIN Sinhvien_Detai sv_dt ON sv.Masv=sv_dt.Masv
WHERE sv.QueQuan = 'Ha Noi'
GROUP BY sv.masv, sv.ten

-- g
CREATE VIEW cau2_g AS
SELECT 
    COUNT(DISTINCT sv_dt.NoiThucTap) as Sotinh
FROM Sinhvien_Detai sv_dt
WHERE sv_dt.Madt = 'DTOO5'

-- h
CREATE VIEW cau2_h AS
SELECT 
    sv.QueQuan
    COUNT(sv.Masv)
FROM Sinhvien sv 
GROUP BY sv.quequan



-- cau 3

-- a
SELECT 
    Madt,
    COUNT(Masv) as SoSinhVien
FROM Sinhvien_Detai 
GROUP BY Madt
HAVING COUNT(Masv) >= 0

-- b
SELECT
    *
FROM Sinhvien
WHERE Hocluc > (
    SELECT AVG(Hocluc) FROM Sinhvien
    WHERE QueQuan='TPHCM'
)


-- c
UPDATE Sinhvien_Detai
SET ketqua = CASE
    WHEN ketqua + 2 > 10 THEN 10
    ELSE ketqua + 2
END
WHERE Masv IN (
    SELECT Masv FROM sinhvien where Quequan = 'Lam Dong'
)

-- d
SELECT 
    sv.*
FROM Sinhvien sv
JOIN Sinhvien_Detai sv_dt ON sv_dt.Masv=sv.Masv
WHERE sv.QueQuan = sv_dt.NoiThucTap

-- e (chua hieu ro cau nay lam can luyen tap them)
SELECT 
    dt.*
FROM Detai dt 
LEFT JOIN Sinhvien_Detai sv_dt on sv_dt.Madt = dt.Madt
WHERE sv_dt.Madt IS NULL

-- f
SELECT
    dt.*
FROM Detai dt 
JOIN Sinhvien_Detai sv_dt ON sv_dt.Madt = dt.Madt
WHERE sv_dt.Masv IN (
    SELECT Masv FROM Sinhvien
    WHERE Hocluc = (SELECT MAX(Hocluc) FROM Sinhvien)
);


-- g
SELECT
    dt.*
FROM Detai dt 
JOIN Sinhvien_Detai sv_dt ON sv_dt.Madt = dt.Madt
WHERE sv_dt.Masv NOT IN (
    SELECT Masv FROM Sinhvien
    WHERE Hocluc = (SELECT MIN(Hocluc) FROM Sinhvien)
)

-- h
SELECT 
    sv.*,
    dt.KinhPhi
FROM Sinhvien sv
JOIN Sinhvien_Detai sv_dt on sv_dt.Masv = sv.Masv
JOIN Detai dt ON dt.Madt = sv_dt.Madt 
WHERE dt.KinhPhi > (SELECT SUM(KinhPhi)/5 FROM Detai)

-- i
SELECT
    sv.*
FROM Sinhvien sv 
JOIN Sinhvien_Detai sv_dt ON sv.Masv = sv_dt.Masv
WHERE sv.hocluc > (
    SELECT AVG(sv_dt.ketqua) FROM Sinhvien_Detai sv_dt
    WHERE sv_dt.Madt = 'DT001'
)