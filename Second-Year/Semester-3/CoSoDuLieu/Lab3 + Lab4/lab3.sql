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
