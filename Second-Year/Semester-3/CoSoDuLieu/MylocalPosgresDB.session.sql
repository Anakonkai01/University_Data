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
-- Cau lenh trong postgres
CREATE VIEW cau2_a AS
SELECT * FROM sinhvien
WHERE EXTRACT(YEAR FROM AGE(NgaySinh)) < 20 AND hocluc > 8.5;
-- Cau lenh trong SQL server
-- CREATE VIEW cau2_a AS
-- SELECT * FROM Sinhvien
-- WHERE DATEDIFF(YEAR, NgaySinh, GETDATE()) < 20 AND HocLuc > 8.5;

CREATE VIEW cau2_b AS
SELECT * FROM detai 
WHERE KinhPhi > 1000000;


CREATE VIEW cau2_c AS
SELECT sinhvien.*, sinhvien_detai.KetQua FROM Sinhvien
JOIN sinhvien_detai ON sinhvien.Masv = sinhvien_detai.Masv
WHERE sinhvien_detai.KetQua >= 8 AND EXTRACT(YEAR FROM AGE(Sinhvien.NgaySinh)) < 20 AND sinhvien_detai.KetQua > 8;

