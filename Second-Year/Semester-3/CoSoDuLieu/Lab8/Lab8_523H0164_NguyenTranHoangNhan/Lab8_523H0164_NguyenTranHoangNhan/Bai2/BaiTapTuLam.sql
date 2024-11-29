



-- bai tap tu lam
-- Tạo cơ sở dữ liệu
CREATE DATABASE QuanLySinhVien;
GO

USE QuanLySinhVien;
GO

-- Tạo bảng Lop
CREATE TABLE Lop (
    malop VARCHAR(10) PRIMARY KEY,
    tenlop NVARCHAR(50) NOT NULL
);

-- Tạo bảng Sinhvien
CREATE TABLE Sinhvien (
    masv VARCHAR(10) PRIMARY KEY,
    hoten NVARCHAR(50) NOT NULL,
    ngaysinh DATE NOT NULL,
    malop VARCHAR(10),
    FOREIGN KEY (malop) REFERENCES Lop(malop)
);

-- Tạo bảng Monhoc
CREATE TABLE Monhoc (
    mamh VARCHAR(10) PRIMARY KEY,
    tenmh NVARCHAR(50) NOT NULL
);

-- Tạo bảng Ketqua
CREATE TABLE Ketqua (
    masv VARCHAR(10),
    mamh VARCHAR(10),
    diem DECIMAL(4, 2),
    PRIMARY KEY (masv, mamh),
    FOREIGN KEY (masv) REFERENCES Sinhvien(masv),
    FOREIGN KEY (mamh) REFERENCES Monhoc(mamh)
);




-- cau a
go
create function	LayHoTen (@hoten varchar(50))
returns table 
as
return (
	select 
		left (@hoten, charindex(' ', @hoten) - 1) as ho,
		substring(@hoten, charindex(' ',@hoten) + 1, len(@hoten) - charindex(' ',@hoten) - charindex(' ',reverse(@hoten))- 1) as holot,
		right(@hoten,charindex(' ',reverse(@hoten)) - 1) as ten
)
go

-- cau b
go
create function HienThiThongTinSinhVien()
returns table 
as 
return (
	select 
		hoten,
		convert(varchar(10),ngaysinh, 103) as ngaysinh,
		case datename(dw,ngaysinh)
			when 'Monday' then 'Thu Hai'
			when 'Tuesday' then 'Thu Ba'
			when 'Wednesday' then 'Thu Tu'
			when 'Thursday' then 'Thu Nam'
			when 'Friday' then 'Thu Sau'
			when 'Saturday' then 'Thu Bay'
			when 'Sunday' then 'Chu Nhat'
		end as Thu
	from Sinhvien
)
go

-- cau c
go 
create function DanhSachSinhVienHocYeu()
returns table 
as 
return (
	select
		sv.masv,
		sv.hoten,
		year(sv.ngaysinh) as namsinh,
		round(avg(kq.diem),1) as trungbinh
	from sinhvien as sv
	join ketqua as kq on sv.masv = kq.masv
	group by sv.masv, sv.hoten, year(sv.ngaysinh)
	having avg(kq.diem) < 5
)
go


-- cau d
go
create function LayThongTinSinhVien (@masv nvarchar(10))
returns table
as
return (
	select
		sv.masv,
		l.malop,
		l.tenlop
	from sinhvien as sv 
	join lop as l on l.malop = sv.malop
	where @masv = sv.masv
)
go

-- cau e
go 
create function DiemTrungBinhMonHoc (@mamh nvarchar(10))
returns table 
as
return (
	select
		mamh,
		round(avg(diem),1) as TrungBinh
	from ketqua
	where mamh = @mamh
	group by mamh
)
go

-- cau f
go
create function DiemTrungBinhCaoNhat (@malop nvarchar(10))
returns table 
as 
return (
	select top 1
		sv.masv,
		sv.hoten,
		round(avg(kq.diem),1) as trungbinh
	from sinhvien as sv
	join ketqua as kq on kq.masv= sv.masv
	where sv.malop = @malop
	group by sv.masv, sv.hoten
	order by trungbinh desc
)
go

-- g
go
create function TaoMaLop (@makhoa nvarchar(10))
returns nvarchar(10)
as 
begin
	declare @malopmoi nvarchar(10)
	declare @sott int

	select @sott = count(*) + 1 from lop where left(malop, len(@makhoa)) = @makhoa

	set @malopmoi = @makhoa + right('0' + cast(@sott as nvarchar(10)),2)
	return @malopmoi
end
go

