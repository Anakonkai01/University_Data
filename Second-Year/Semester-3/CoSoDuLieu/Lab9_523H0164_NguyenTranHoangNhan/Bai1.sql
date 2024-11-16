create database QuanLyBanSach;
go
use QuanLyBanSach;
go 


create table NhomSach(
	MaNhom char(10) primary key,
	TenNhom nvarchar(25)	
)

create table NhanVien(
	MaNV char(5) primary key,
	HoLot nvarchar(25),
	TenNV nvarchar(10),
	Phai nvarchar(3),
	NgaySinh smalldatetime,
	DiaChi nvarchar(40)
)

create table DanhMucSach(
	MaSach char(5) primary key,
	TenSach nvarchar(40),
	TacGia nvarchar(20),
	MaNhom char(10),
	DonGia numeric(5),
	SLTon numeric(5),
	constraint fk_MaNhom foreign key (MaNhom) references NhomSach(MaNhom)
)

create table HoaDon(
	MaHD char(5) primary key,
	NgayBan smalldatetime, 
	MaNV char(5),
	constraint fk_manv foreign key (MaNV) references NhanVien(MaNV)
)

create table ChiTietHoaDon(
	MaHD char(5),
	MaSach char(5),
	primary key (MaHD,MaSach),
	SoLuong numeric(5),
	constraint fk_MaHD foreign key (MaHD) references HoaDon(MaHD),
	constraint fk_MaSach foreign key (MaSach) references DanhMucSach(MaSach)
)



-- cau 1
go
create trigger DemSoLuongTinChenVao
on NhomSach
after insert 
as 
begin 
	declare @SL int
	select @SL = count(*) from inserted

	print 'So Luong = ' + convert(char(5),@SL)
end
go

insert into NhomSach values
('ns3','Ngon Tinh'),
('ns2','Day Nay An')


-- cau 2
create table HoaDon_Luu(
	MaHD char(5) primary key,
	NgayBan smalldatetime, 
	MaNV char(5),
	constraint fk_manv1 foreign key (MaNV) references NhanVien(MaNV)
)
go

create trigger chenThongTinVaoHoaDonLuu
on HoaDon
after insert
as 
begin 
	insert into HoaDon_Luu
	select * from inserted
end


-- cau 3
go
create trigger CapNhapTongTien_afterInsert
on ChiTietHoaDon
after insert
as 
begin
	-- Cập nhật tổng trị giá cho tất cả các hóa đơn bị ảnh hưởng
	update HoaDon
	set TongTriGia = (
		select SUM(SoLuong * DonGia) 
		from ChiTietHoaDon, DanhMucSach
		where ChiTietHoaDon.MaHD = HoaDon.MaHD and DanhMucSach.MaSach = ChiTietHoaDon.MaSach
	)
	from HoaDon
	join inserted on HoaDon.MaHD = INSERTED.MaHD;
end
go

go
create trigger CapNhapTongTien_afterDelete
on ChiTietHoaDon
after delete
as 
begin
	-- Cập nhật tổng trị giá cho tất cả các hóa đơn bị ảnh hưởng
	update HoaDon
	set TongTriGia = (
		select SUM(SoLuong * DonGia) 
		from ChiTietHoaDon, DanhMucSach
		where ChiTietHoaDon.MaHD = HoaDon.MaHD and DanhMucSach.MaSach = ChiTietHoaDon.MaSach
	)
	from HoaDon
	join inserted on HoaDon.MaHD = INSERTED.MaHD;
end
go
	
go
create trigger CapNhapTongTien_afterUpdate
on ChiTietHoaDon
after update
as 
begin
	-- Cập nhật tổng trị giá cho tất cả các hóa đơn bị ảnh hưởng
	update HoaDon
	set TongTriGia = (
		select SUM(SoLuong * DonGia) 
		from ChiTietHoaDon, DanhMucSach
		where ChiTietHoaDon.MaHD = HoaDon.MaHD and DanhMucSach.MaSach = ChiTietHoaDon.MaSach
	)
	from HoaDon
	join inserted on HoaDon.MaHD = INSERTED.MaHD;
end
go



-- cau 4
alter table ChiTietHoaDon add GiaBan decimal(10,2)
go
create trigger kiemtraRangBuoc_AfterInsert
on ChiTietHoaDon
after insert, update
as 
begin 
	declare @GiaBan decimal(10,2)
	declare @DonGia numeric(5)

	select @GiaBan = GiaBan from inserted
	select @DonGia = DonGia from DanhMucSach

	if(@GiaBan <> @DonGia)
	begin 
		print 'ko duoc phep'
		rollback
	end
end

-- cau 5
alter table HoaDon add NgayLap smalldatetime
go
create trigger tg_RangBuocNgayHoaDon
on HoaDon
after insert,update
as 
begin 
	declare @ngayban smalldatetime
	declare @ngaylap smalldatetime
	
	select @ngayban = NgayBan from inserted
	select @ngaylap = NgayLap from inserted
	if(@ngayban < @ngaylap)
	begin
		print 'ko duoc'
		rollback
	end
end 