create database QuanLyBanHang
go
use QuanLyBanHang
go

create table KHACHHANG (
	MAKHACHHANG varchar(30) primary key,
	TENCONGTY nvarchar(30),
	TENGIAODICH nvarchar(30),
	DIACHI nvarchar (100),
	EMAIL varchar(30),
	DIENTHOAI varchar(30),
	FAX varchar(30)
)

create table DONDATHANG (
	SOHOADON varchar(30) primary key,
	MAKHACHHANG varchar(30),
	MANHANVIEN varchar(30),
	NGAYDATHANG date,
	NGAYCHYENHANG date,
	NOIGIAOHANG nvarchar(30)
)

create table NHANVIEN (
	MANHANVIEN varchar(30) primary key,
	HO nvarchar(30),
	TEN nvarchar(30),
	NGAYSINH date,
	NGAYLAMVIEC date,
	DIACHI nvarchar(30),
	DIENTHOAI varchar (30),
	LUONGCOBAN int,
	PHUCAP int
)

create table NHACUNGCAP (
	MACONGTY varchar(30) primary key,
	TENCONGTY nvarchar(30),
	TENGIAODIH nvarchar(30),
	DIACHI nvarchar(30),
	DIENTHOAI varchar(30),
	FAX varchar(30),
	EMAIL varchar(30)
)

create table CHITIETDATHANG (
	SOHOADON varchar(30),
	MAHANG varchar(30),
	GIABAN int,
	SOLUONG int,
	MUCGIAMGIA int
)

create table MATHANG (
	MAHANG varchar(30) primary key,
	TENHANG varchar(30),
	MACONGTY varchar(30),
	MALOAIHANG varchar(30),
	SOLUONG int,
	DONVITINH varchar(30),
	GIAHANG int
)

create table LOAIHANG (
	MALOAIHANG varchar(30) primary key,
	TENLOAIHANG varchar(30)
)


alter table DONDATHANG add constraint FK_DDH_KH foreign key(MAKHACHHANG) references KHACHHANG(MAKHACHHANG)
alter table DONDATHANG add constraint FK_DDH_NV foreign key(MANHANVIEN) references NHANVIEN(MANHANVIEN)
alter table CHITIETDATHANG add constraint FK_CTDH_DDH foreign key(SOHOADON) references DONDATHANG(SOHOADON)
alter table CHITIETDATHANG add constraint FK_CTDH_MH foreign key(MAHANG) references MATHANG(MAHANG)
alter table MATHANG add constraint FK_MH_NCC foreign key(MACONGTY) references NHACUNGCAP(MACONGTY)
alter table MATHANG add constraint FK_MH_LH foreign key(MALOAIHANG) references LOAIHANG(MALOAIHANG)

go


alter table CHITIETDATHANG add constraint df_SOLUONG DEFAULT 1  FOR SOLUONG
alter table CHITIETDATHANG add constraint df_MUCGIAMGIA DEFAULT 0  FOR MUCGIAMGIA
alter table DONDATHANG add constraint check_NGAYCHUYENHANG_NGAYDATHANG CHECK(NGAYCHYENHANG >= NGAYDATHANG)
alter table NHANVIEN add constraint check_TUOI CHECK(year(GETDATE()) - year(NGAYSINH) >= 18)
go
-- cau 1
create proc ThemNhaCungCap(@MaCongTy varchar(30), @TenCongTy nvarchar(30, @TenGiaoDich varchar(100), @DiaChi nvarchar(100), @DienThoai varchar(30), @Fax varchar(30), @Email varchar(30))
as 
begin 
	if exists(select * from NHACUNGCAP where MACONGTY = @MaCongTy)
	begin 
		print 'Nha cung cap voi ma  nay da ton tai.';
		return;
	end 

	insert into NHACUNGCAP (MACONGTY, TENCONGTY, TENGIAODIH, DIACHI, DIENTHOAI, FAX, EMAIL)
	values (@MaCongTy, @TenCongTy, @TenGiaoDich, @DiaChi, @DienThoai, @Fax, @Email);

	print 'Nha cung cap moi da duocj them thanh cong.';
end 

EXEC ThemNhaCungCap 
    @MaCongTy = 'NCC001', 
    @TenCongTy = N'Công ty ABC', 
    @TenGiaoDich = N'ABC Ltd.', 
    @DiaChi = N'123 Đường ABC', 
    @DienThoai = '0123456789', 
    @Fax = '0123456789', 
    @Email = 'abc@example.com';


go 

-- cau 2
create proc ThemMatHang (@MaHang varchar(30), @TenHang nvarchar(30), @MaCongTy varchar(30), @MaLoaiHang varchar(30), @SoLuong int, @DonViTinh varchar(30), @GiaHang int)
as 
begin 
	if @MaHang in (select MAHANG from MATHANG)
	begin 
		print 'Ma hang da ton tai';
		return;
	end 

	if @MaCongTy is null or not exists (select MACONGTY from NHACUNGCAP where MACONGTY = @MaCongTy)
	begin 
		print 'Ma cong ty khong ton tai trong bang nha cung cap';
		return;
	end 

	insert into MATHANG values 
	(@MaHang, @TenHang, @MaCongTy, @MaLoaiHang, @SoLuong, @DonViTinh, @GiaHang);
	
end 

EXEC ThemMatHang 
    @MaHang = 'MH001', 
    @TenHang = N'Hàng A', 
    @MaCongTy = 'NCC001', 
    @MaLoaiHang = 'LH001', 
    @SoLuong = 100, 
    @DonViTinh = N'Cái', 
    @GiaHang = 20000;

go

create proc ThongKeSoLuongHangBan @MaHang varchar(30)
as 
begin 
	declare @TongSoLuong int;

	select @TongSoLuong = SUM(SOLUONG)
	from CHITIETDATHANG
	where MAHANG = @MaHang;

	if @TongSoLuong is null 
	begin 
		print 'khong co du lieu ban hang chyo ma hang nay';
	end 
	else 
	begin 
		print 'tong so luong hang ban duoc cua ma hang' + @MaHang + 'la' + CAST(@TongSoLuong as varchar);
	end 
end 

EXEC ThongKeSoLuongHangBan @MaHang = 'MH001';
go