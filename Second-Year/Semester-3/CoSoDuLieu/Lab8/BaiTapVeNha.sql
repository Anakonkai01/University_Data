CREATE DATABASE QuanLyNGK;
GO

USE QuanLyNGK;
GO

CREATE TABLE NSX (
    maNSX VARCHAR(10) PRIMARY KEY,
    tenNSX NVARCHAR(50) NOT NULL
);

CREATE TABLE NGK (
    MaNGK VARCHAR(10) PRIMARY KEY,
    TenNGK NVARCHAR(50) NOT NULL,
    DVT NVARCHAR(10) NOT NULL, 
    soluong INT NOT NULL,
    dongia DECIMAL(10, 2) NOT NULL,
    Maloai VARCHAR(10),
    FOREIGN KEY (Maloai) REFERENCES NSX(maNSX)
);

CREATE TABLE Hoadon (
    sohd VARCHAR(10) PRIMARY KEY, 
    ngaylap DATE NOT NULL 
);

CREATE TABLE CTHD (
    sohd VARCHAR(10), 
    MaNGK VARCHAR(10), 
    soluong INT NOT NULL, 
    dongia DECIMAL(10, 2) NOT NULL, 
    PRIMARY KEY (sohd, MaNGK),
    FOREIGN KEY (sohd) REFERENCES Hoadon(sohd),
    FOREIGN KEY (MaNGK) REFERENCES NGK(MaNGK)
);


-- cau a
go 
create function PhatSinhSoHoaDon (@ngaylap date)
returns nvarchar(10)
as
begin 
	declare @sohoadonmoi nvarchar(10)
	declare @sott int

	select @sott = count(*) + 1 from Hoadon where ngaylap = @ngaylap

	set @sohoadonmoi = right('0' + cast(day(@ngaylap) as nvarchar(2)),2) + right('0' + cast(month(@ngaylap) as nvarchar(2)),2) + right(cast(year(@ngaylap) as nvarchar(4)),2) + cast(@sott as nvarchar)
	return @sohoadonmoi
end 
go


-- cau b
create proc ThemHoadonMoi
as 
begin
	declare @ngaylap date = getdate()
	declare @sohd nvarchar(10)

	set @sohd = dbo.PhatSinhSoHoaDon(@ngaylap)

	insert into Hoadon values
	(@sohd,@ngaylap)
end

-- cau c
go
create function PhatSinhMaNGK (@maNSX nvarchar(10))
returns nvarchar(10) 
as
begin 
	declare @maNGKmoi nvarchar(10)
	declare @sott int 

	select @sott = count(*) + 1 from NGK where left (MaNGK,len(@maNSX)) = @maNSX

	set @maNGKmoi = @maNSX + right('000' + cast(@sott as nvarchar(3)),3)
	return @maNGKmoi
end 
go

go
create proc ThemNGKMoi (@tenNGK nvarchar(50),@DVT nvarchar(10),@soluong int, @dongia decimal(10,2), @maNSX nvarchar(10))
as 
begin
	 declare @maNGK nvarchar(10)

	 set @maNGK = dbo.PhatSinhMaNGK(@maNSX)

	 insert into NGK values
	 (@maNGK, @tenNGK, @DVT, @soluong, @dongia, @maNSX)
end
go

-- cau d
create proc ThemCTHD (@sohd nvarchar(10), @maNGK nvarchar(10), @soluong int)
as
begin 
	declare @dongia decimal(10,2)

	if exists (select 1 from NGK where MaNGK = @maNGK and soluong >= @soluong)
	begin
		select @dongia = dongia * 1.5 from NGK where MaNGK = @maNGK

		insert into CTHD values
		(@sohd, @maNGK, @soluong, @dongia)
	end 
	else
	begin 
		print 'so luong khong hop le'
	end 
end 
go

-- cau e
go 
create function TongTienHoaDon (@sohd nvarchar(10))
returns decimal(10,2)
as 
begin 
	declare @tong decimal(10,2)

	select @tong = sum(soluong * dongia) from CTHD where @sohd = sohd
	return @tong
end 
go

-- cau f
go
create function NGKBanThang32016()
returns table 
as 
return (
	select distinct n.MaNGK, n.TenNGK 
	from NGK as n
	join CTHD as ct on n.MaNGK = ct.MaNGK
	join Hoadon as hd on hd.sohd = ct.sohd
	where month(hd.ngaylap) = 3 and year(hd.ngaylap) = 2016
)