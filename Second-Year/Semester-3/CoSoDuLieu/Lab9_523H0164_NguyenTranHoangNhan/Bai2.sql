create database QuanLyThuVien
go
use QuanLyThuVien
go

create table DocGia (
	ma_DocGia int primary key,
	ho varchar(10),
	tenlot varchar (20),
	ten varchar(10),
	ngaysinh date
)

create table NguoiLon(
	maDocGia int,
	constraint fk_maDocGia foreign key (MaDocGia) references DocGia(ma_DocGia),
	SoNha varchar(10),
	Duong varchar(20),
	Quan varchar(10),
	dienthoai varchar(10),
	han_sd date
)

create table TuaSach(
	ma_TuaSach int primary key,
	TuaSach varchar(20),
	NgonNgu varchar(10),
	Bia varchar (10),
	TrangThai varchar(10)
)

create table DauSach (
	isbn varchar(10) primary key,
	ma_tuasach int,
	constraint fk_ma_tuasach foreign key (ma_tuasach) references Tuasach(ma_tuasach),
	NgonNgu varchar(10),
	TacGia varchar(50),
	Bia varchar(10),
	trangThai varchar(10)
)

create table CuonSach(
	isbn varchar(10),
	constraint fk_isbn foreign key (isbn) references DauSach(isbn),
	ma_cuonsach int primary key,
	tinhTrang varchar(10)
)

-- cau 1
go 
create trigger tg_delMuon 
on CuonSach 
after delete 
as 
begin
	declare @MaSachMuon varchar(10)
	
	select @MaSachMuon = d.ma_cuonsach from deleted as d
 
    update CuonSach
    set trangThai = 'no'
	where @MaSachMuon = CuonSach.ma_cuonsach
end
go 

-- cau 2
-- tuong tu vay



-- cau 3
go
create trigger tg_updCuonSach 
on CuonSach 
after update 
as 
begin 	
	declare @TrangThai varchar(10)

	select @TrangThai = i.TrangThai from inserted as i 
	
	update DauSach
	set trangThai = @TrangThai
	where i.isbn = DauSach.isbn
end 


-- cau 5
go 
create trigger tg_InfThongBao 
on 