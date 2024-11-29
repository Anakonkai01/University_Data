


create database QuanLySinhVien
go
use QuanLySinhVien
go

drop table sinhvien

create table SINHVIEN (
	HoSV Nvarchar(30),
	TenSV Nvarchar(30),
	MaSV varchar(10) primary key,
	NgaySinh date,
	Phai Nvarchar(30),
	MaKhoa varchar(10)
)

create table MONHOC (
	TenMH Nvarchar(30),
	MaMH varchar(10) primary key,
	SoTiet int
)

create table KHOA (
	MaKhoa varchar(10) primary key,
	TenKhoa Nvarchar(30)
)

create table KETQUA (
	MaSV varchar(10),
	MaMH varchar(10),
	LanThi int,
	Diem float
	primary key(MaSV, MaMH, LanThi)
)

go

alter table SINHVIEN add constraint FK_SINHVIEN_MaKhoa_KHOA foreign key(MaKhoa) references KHOA(MaKhoa)
alter table KETQUA add constraint FK_KETQUA_MaSV_SINHVIEN foreign key(MaSV) references SINHVIEN(MaSV)
alter table KETQUA add constraint FK_KETQUA_MaMH_SINHVIEN foreign key(MaMH) references MONHOC(MaMH)

go

-- cau 2 cua thay 
insert into KHOA values('CNTT', N'Công Nghệ Thông Tin')
go
set dateformat dmy
go 
create proc p_Them_SV(@Hosv nvarchar(30),@Tensv nvarchar(30),@Masv varchar(10),@NgaySinh date,@Phai nvarchar(30),@MaKhoa varchar(20))
as
begin
	if(exists(select * from SINHVIEN where masv =@Masv))
		raiserror( N'Mã sinh viên đã tồn tại',16,1)
	else if( not exists(select * from Khoa where Makhoa=@MaKhoa))
		raiserror( N'Mã khoa chưa có',16,1)
	else if(datediff(yy,@NgaySinh,getdate()) <=18 or datediff(yy,@NgaySinh,getdate()) >=40)
		raiserror( N' Tuổi phải từ 18 đến 40',16,1)
	else
		insert into SINHVIEN values(@Hosv,@Tensv,@Masv,@NgaySinh,@Phai,@MaKhoa)
end
exec p_Them_SV N'Trần',N'Thanh Hậu','01004340','18/11/2000',N'Nam','CNTT'
drop proc p_Them_SV
go 


-- cau 3
create proc p_Them_KetQua (@MaSV varchar(10), @MaMH varchar(10), @LanThi int, @Diem float)
as begin 

	if not exists(select * from SINHVIEN where MaSV = @MaSV)
	begin 
		raiserror(N'Mã Sinh viên không tồn tại',16,1);
		return;
	end

	if not exists(select * from MONHOC where MaMH = @MaMH)
	begin 
		raiserror(N'Mã môn học không tồn tại',16,1);
		return;
	end 

	if exists(select * from KETQUA where MaSV = @MaSV and MaMH = @MaMH and LanThi = @LanThi)
	begin 
		raiserror(N'Kết quả, môn học, lần thi đã tồn tại',16,1);
		return;
	end 

	insert into KETQUA (MaSV,MaMH,LanThi,Diem) values 
	(@MaSV,@MaMH,@LanThi,@Diem);

end
go


EXEC p_Them_KetQua 
    @MaSV = '01004340',
    @MaMH = 'CNTT101',
    @LanThi = 1,
    @Diem = 8.5;
go

-- cau 4
create proc SoLuongSinhVienKhoa (@MaKhoa varchar(10))
as 
begin 
	declare @SoLuong int;

	select @SoLuong = COUNT(*)
	from SINHVIEN 
	where MaKhoa = @MaKhoa;

	print 'Số lượng sinh viên trong khoa' + @MaKhoa + 'là' + cast(@SoLuong as varchar);
end
go 

EXEC SoLuongSinhVienKhoa @MaKhoa = 'CNTT';
go

-- cau 5 
create proc DanhSachSinhVienKhoa (@MaKhoa varchar(10))
as
begin 
	select * 
	from SINHVIEN
	where MaKhoa = @MaKhoa;
end 

EXEC DanhSachSinhVienKhoa @MaKhoa = 'CNTT';
go

-- cau 6

create proc ThongKeSoLuongSinhVienMoiKhoa 
as
begin 
	select k.MaKhoa, k.TenKhoa, COUNT(s.masv) as SoLuongSinhvien 
	from Khoa as k 
	join SINHVIEN as s on k.MaKhoa = s.MaKhoa
	group by k.MaKhoa, k.TenKhoa;
end 

EXEC ThongKeSoLuongSinhVienMoiKhoa;
go 


-- cau 7
create proc XemKetQuaHocTap (@MaSv varchar(10))
as
begin 
	select k.MaSV, mh.TenMH, k.lanthi, k.diem 
	from KETQUA as k 
	join MONHOC as mh on k.MaMH = mh.MaMH
	where k.MaSV = @MaSv;
end 

EXEC XemKetQuaHocTap @MaSV = '123456789';
go 

-- cau 8 
create function DemSoLuongSinhVienKhoa (@MaKhoa varchar(10))
returns int 
as
begin 
	declare @SoLuong int;

	select @SoLuong = count(*)
	from SINHVIEN
	where MaKhoa = @MaKhoa;

	return @SoLuong;
end
go

select dbo.DemSoLuongSinhvienKhoa('CNTT') as SoLuongSinhVien;

