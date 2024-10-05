-- cau 1
create table phong (
	maphong char(3) primary key,
	tenphong varchar(40), 
	diachi varchar(50),
	tel char(10)
);

create table dmnn(
	mann char(2)primary key,
	tennn varchar(20)
);

create table nhanvien (
	manv char(5) primary key,
	hoten varchar(40),
	gioitinh char(3),
	ngaysinh date,
	luong int,
	maphong char(3),
	sdt char(10),
	ngaybc date
);

create table tdnn(
	manv char(5),
	mann char(2),
	tdo char(1),
	primary key(manv,mann)
);

-- cau 2

INSERT INTO PHONG VALUES
('HCA', 'Hành chính tổ hợp', '123, Láng Hạ, Đống Đa, Hà Nội', '04 8585793'),
('KDA', 'Kinh Doanh', '123, Láng Hạ, Đống Đa, Hà Nội', '04 8574943'),
('KTA', 'Kỹ thuật', '123, Láng Hạ, Đống Đa, Hà Nội', '04 9480485'),
('QTA', 'Quản trị', '123, Láng Hạ, Đống Đa, Hà Nội', '04 8508585');

INSERT INTO DMNN VALUES
('01', 'Anh'),
('02', 'Nga'),
('03', 'Pháp'),
('04', 'Nhật'),
('05', 'Trung Quốc'),
('06', 'Hàn Quốc');


INSERT INTO NHANVIEN VALUES
('HC001', 'Nguyễn Thị Hà', 'Nữ', '1950-08-27', 2500000, 'HCA', NULL, '1975-02-08'),
('HC002', 'Trần Văn Nam', 'Nam', '1975-06-12', 3000000, 'HCA', NULL, '1997-06-08'),
('HC003', 'Nguyễn Thanh Huyền', 'Nữ', '1978-07-03', 1500000, 'HCA', NULL, '1999-09-24'),
('KD001', 'Lê Tuyết Anh', 'Nữ', '1977-02-03', 2500000, 'KDA', NULL, '2001-10-02'),
('KD002', 'Nguyễn Anh Tú', 'Nam', '1942-07-04', 2600000, 'KDA', NULL, '1999-09-24'),
('KD003', 'Phạm An Thái', 'Nam', '1977-05-09', 1600000, 'KDA', NULL, '1999-09-24'),
('KD004', 'Lê Văn Hải', 'Nam', '1976-01-02', 2700000, 'KDA', NULL, '1997-06-08'),
('KD005', 'Nguyễn Phương Minh', 'Nam', '1980-01-02', 2000000, 'KDA', NULL, '2001-10-02'),
('KT001', 'Trần Đình Khâm', 'Nam', '1981-12-02', 2700000, 'KTA', NULL, '2005-01-01'),
('KT002', 'Nguyễn Mạnh Hùng', 'Nam', '1980-08-16', 2300000, 'KTA', NULL, '2005-01-01'),
('KT003', 'Phạm Thanh Sơn', 'Nam', '1984-08-20', 2000000, 'KTA', NULL, '2005-01-01'),
('KT004', 'Vũ Thị Hoài', 'Nữ', '1980-12-05', 2500000, 'KTA', NULL, '2001-10-02'),
('KT005', 'Nguyễn Thu Lan', 'Nữ', '1977-10-05', 3000000, 'KTA', NULL, '2001-10-02'),
('KT006', 'Trần Hoài Nam', 'Nam', '1978-07-02', 2800000, 'KTA', NULL, '1997-06-08'),
('KT007', 'Hoàng Nam Sơn', 'Nam', '1940-12-03', 3000000, 'KTA', NULL, '1965-07-02'),
('KT008', 'Lê Thu Trang', 'Nữ', '1950-07-06', 2500000, 'KTA', NULL, '1968-08-02'),
('KT009', 'Khúc Nam Hải', 'Nam', '1980-07-22', 2000000, 'KTA', NULL, '2005-01-01'),
('KT010', 'Phùng Trung Dũng', 'Nam', '1978-08-28', 2200000, 'KTA', NULL, '1999-09-24');

INSERT INTO TDNN VALUES
('HC001', '01', 'A'), ('HC001', '02', 'B'),
('HC002', '01', 'C'), ('HC002', '03', 'C'),
('HC003', '01', 'D'),
('KD001', '01', 'C'), ('KD001', '02', 'B'),
('KD002', '01', 'D'), ('KD002', '02', 'A'),
('KD003', '01', 'B'), ('KD003', '02', 'C'),
('KD004', '01', 'C'), ('KD004', '04', 'A'),
('KD004', '05', 'A'),
('KD005', '01', 'B'), ('KD005', '02', 'D'),
('KD005', '03', 'B'), ('KD005', '04', 'B'),
('KT001', '01', 'D'), ('KT001', '04', 'E'),
('KT002', '01', 'C'), ('KT002', '02', 'B'),
('KT003', '01', 'D'), ('KT003', '03', 'C'),
('KT004', '01', 'D'),
('KT005', '01', 'C');


-- cau 3.1
INSERT INTO NHANVIEN VALUES
('QT001', '[Tên của bạn]', 'Nam', '2000-01-01', 150000, 'QTA', NULL, '2024-01-01');

INSERT INTO TDNN VALUES
('QT001', '01', 'C'),
('QT001', '04', 'A');

-- cau 3.2
SELECT NV.*, P.TENPHONG, D.TENNN, T.TDO
FROM NHANVIEN NV
JOIN PHONG P ON NV.MAPHONG = P.MAPHONG
JOIN TDNN T ON NV.MANV = T.MANV
JOIN DMNN D ON T.MANN = D.MANN
WHERE NV.MANV = 'QT001';


-- cau 4
-- 1
SELECT * FROM NHANVIEN WHERE MANV = 'KT001';

-- 3
SELECT * FROM NHANVIEN WHERE GIOITINH = 'Nữ';

-- 4
SELECT * FROM NHANVIEN WHERE HOTEN LIKE 'Nguyễn%';

-- 5
SELECT * FROM NHANVIEN WHERE HOTEN LIKE '%Văn%';

-- 6
SELECT *, DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYSINH) AS TUOI
FROM NHANVIEN
WHERE DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYSINH) < 30;

-- 7
SELECT *, DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYSINH) AS TUOI
FROM NHANVIEN
WHERE DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYSINH) BETWEEN 25 AND 30;

-- 8
SELECT DISTINCT MANV FROM TDNN 
WHERE MANN = '01' AND TDO <= 'C';

-- 9
SELECT * FROM NHANVIEN WHERE DATE_PART('year', NGAYBC) < 2000;

-- 10
SELECT *, DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYBC) AS THAM_NIEN
FROM NHANVIEN
WHERE DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYBC) > 10;

-- 11
SELECT *
FROM NHANVIEN
WHERE (GIOITINH = 'Nam' AND DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYSINH) >= 60)
   OR (GIOITINH = 'Nữ' AND DATE_PART('year', CURRENT_DATE) - DATE_PART('year', NGAYSINH) >= 55);

-- 12
SELECT MAPHONG, TENPHONG, TEL FROM PHONG;

-- 13
SELECT HOTEN, NGAYSINH, NGAYBC FROM NHANVIEN LIMIT 2;

-- 14
SELECT MANV, HOTEN, NGAYSINH, LUONG
FROM NHANVIEN
WHERE LUONG BETWEEN 2000000 AND 3000000;

-- 15
SELECT * FROM NHANVIEN WHERE SDT IS NULL;

-- 16
SELECT * FROM NHANVIEN WHERE DATE_PART('month', NGAYSINH) = 3;

-- 17
SELECT * FROM NHANVIEN ORDER BY LUONG ASC;

-- 18
SELECT AVG(LUONG) AS LUONG_TB
FROM NHANVIEN NV
JOIN PHONG P ON NV.MAPHONG = P.MAPHONG
WHERE P.TENPHONG = 'Kinh Doanh';

-- 19
SELECT COUNT(*) AS SO_NHAN_VIEN, AVG(LUONG) AS LUONG_TB
FROM NHANVIEN NV
JOIN PHONG P ON NV.MAPHONG = P.MAPHONG
WHERE P.TENPHONG = 'Kinh Doanh';

-- 20
SELECT P.TENPHONG, SUM(NV.LUONG) AS TONG_LUONG
FROM NHANVIEN NV
JOIN PHONG P ON NV.MAPHONG = P.MAPHONG
GROUP BY P.TENPHONG;

-- 21
SELECT P.TENPHONG, SUM(NV.LUONG) AS TONG_LUONG
FROM NHANVIEN NV
JOIN PHONG P ON NV.MAPHONG = P.MAPHONG
GROUP BY P.TENPHONG
HAVING SUM(NV.LUONG) > 5000000;

-- 22
SELECT NV.MANV, NV.HOTEN, NV.MAPHONG, P.TENPHONG
FROM NHANVIEN NV
JOIN PHONG P ON NV.MAPHONG = P.MAPHONG;

-- 23
SELECT NV.MANV, NV.HOTEN, P.MAPHONG, P.TENPHONG
FROM NHANVIEN NV
LEFT JOIN PHONG P ON NV.MAPHONG = P.MAPHONG;

-- 24
SELECT P.MAPHONG, P.TENPHONG, NV.MANV, NV.HOTEN
FROM NHANVIEN NV
RIGHT JOIN PHONG P ON NV.MAPHONG = P.MAPHONG;
