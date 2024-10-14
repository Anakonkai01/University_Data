create database quanlyduancongty;
use quanlyduancongty;

-- phan 1
create table LoaiCV(
	MaLoaiCV varchar(50) primary key,
	TenLoaiCV varchar(100)
);

create table Phongban(
	MaPhong varchar(50) primary key,
	TenPhong varchar(100)
);

insert into LoaiCV values
('L001','Quan Ly');

insert into Phongban values
('P001','Phong Ke Toan');

select * from Phongban
where TenPhong like '%Phong%'

--phan 2
create table Nhanvien(
	MaNV varchar(50) primary key,
	Hoten varchar(100),
	GioiTinh varchar(10),
	Diachi varchar(100),
	MaPhong varchar(50),
	foreign key (MaPhong) references Phongban(MaPhong),
	MaLoaiCV varchar(50),
	foreign key (MaLoaiCV) references LoaiCV(MaLoaiCV)
)

alter table Nhanvien add constraint ck_gioitinh check(GioiTinh in ('Nam','Nu'));

insert into Nhanvien values
('NV001','Nguyen Van A','Nam','Ha Noi','P001','L001');

select 
	MaNV,
	Hoten
from Nhanvien
where MaPhong = 'P001'

delete from Phongban
where MaPhong = 'P001' and MaPhong not in(select MaPhong from Nhanvien);


-- phan 3
create table Duan(
	MaDA varchar(50) primary key,
	TenDA varchar(100) unique,
	TruongDA varchar(50),
	foreign key (TruongDA) references Nhanvien(MaNV),
	Kinhphi decimal(18,2) check(Kinhphi > 5000),
	diadiem varchar(100)
);

insert into Duan values 
('DA001','Du An A','NV001',100000000,'Ha Noi');


update Duan
set Kinhphi = case
		when Kinhphi + 20000000 > 150000000 then 150000000
		else Kinhphi + 20000000
	end 
where MaDA ='DA001';


create table Phancong (
	MaDA varchar(50),
	MaNV varchar(50),
	primary key(MaDA,MaNV),
	NgayBD date default GETDATE(),
	NgayKT date,
	constraint ck_ngaybd_ngaykt check(NgayBD <= NgayKT)
);

insert into Phancong values
('DA001','NV001','2023-01-01','2023-12-31');

select 
	nv.MaNV,
	nv.Hoten,
	da.TenDA
from Nhanvien as nv
join Phancong as pc on pc.MaNV = nv.MaNV
join Duan as da on da.MaDA = pc.MaDA;


-- phan 4
select 
	nv.*
from Nhanvien as nv
join Phancong as pc on pc.MaNV = nv.MaNV
join Duan as da on da.MaDA = pc.MaDA
where da.Kinhphi > 50000000 and da.diadiem = 'Tp.HCM' and pc.NgayKT > '2022-01-01'


select 
	nv.MaNV,
	SUM(da.Kinhphi)
