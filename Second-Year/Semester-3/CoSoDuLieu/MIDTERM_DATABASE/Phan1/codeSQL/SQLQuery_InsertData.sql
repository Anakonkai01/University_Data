-- add khach hang
Use QuanLyChoThueToChucSuKien3;
go


CREATE PROCEDURE AddKhachHang
    @TenKH NVARCHAR(100),
    @DiaChi NVARCHAR(255),
    @SoDienThoai NVARCHAR(15),
    @Email NVARCHAR(100)
AS
BEGIN
    DECLARE @NewMaKH NVARCHAR(10);
    
    -- Tạo mã Khách Hàng tự động
    SELECT @NewMaKH = 'KH' + RIGHT('000' + CAST(COALESCE(MAX(CAST(SUBSTRING(MaKH, 3, 4) AS INT)), 0) + 1 AS NVARCHAR), 4)
    FROM KhachHang;

    -- Thêm dữ liệu vào bảng KhachHang
    INSERT INTO KhachHang (MaKH, TenKH, DiaChi, SoDienThoai, Email)
    VALUES (@NewMaKH, @TenKH, @DiaChi, @SoDienThoai, @Email);

    -- Trả về mã khách hàng mới
    PRINT 'Khách hàng mới được thêm với mã: ' + @NewMaKH;
END;




-- add hop dong
GO
CREATE PROCEDURE AddHopDong
    @MaKH NVARCHAR(10),
    @NgayKyKet DATE,
    @TongTien DECIMAL(18, 2),
    @TrangThaiHopDong NVARCHAR(50)
AS
BEGIN
    DECLARE @NewMaHD NVARCHAR(10);
    
    -- Tạo mã Hợp Đồng tự động
    SELECT @NewMaHD = 'HD' + RIGHT('000' + CAST(COALESCE(MAX(CAST(SUBSTRING(MaHD, 3, 4) AS INT)), 0) + 1 AS NVARCHAR), 4)
    FROM HopDong;

    -- Kiểm tra xem MaKH có tồn tại trong bảng KhachHang không
    IF NOT EXISTS (SELECT 1 FROM KhachHang WHERE MaKH = @MaKH)
    BEGIN
        RAISERROR ('MaKH không tồn tại trong bảng KhachHang.', 16, 1);
        RETURN;
    END

    -- Thêm dữ liệu vào bảng HopDong
    INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong)
    VALUES (@NewMaHD, @MaKH, @NgayKyKet, @TongTien, @TrangThaiHopDong);

    -- Trả về mã hợp đồng mới
    PRINT 'Hợp đồng mới được thêm với mã: ' + @NewMaHD;
END;








-- test case
EXEC AddKhachHang 
    @TenKH = N'Nguyen Van A', 
    @DiaChi = N'Hanoi', 
    @SoDienThoai = '0123456789', 
    @Email = 'nguyenvana@example.com';


EXEC AddKhachHang 
    @TenKH = N'Nguyen Van F', 
    @DiaChi = N'Hai Phong', 
    @SoDienThoai = '0911223344', 
    @Email = 'f@gmail.com';

EXEC AddKhachHang 
    @TenKH = N'', 
    @DiaChi = N'Hai Phong', 
    @SoDienThoai = '0911223344', 
    @Email = 'f@gmail.com';

EXEC AddKhachHang 
    @TenKH = N'Tran Van G', 
    @DiaChi = N'Ha Noi', 
    @SoDienThoai = '0123456789', 
    @Email = 'g@gmail.com';


EXEC AddKhachHang 
    @TenKH = N'Pham H', 
    @DiaChi = N'Ha Noi', 
    @SoDienThoai = '0999999999', 
    @Email = 'invalid-email';

DELETE FROM KhachHang;
EXEC AddKhachHang 
    @TenKH = N'Tran Thi I', 
    @DiaChi = N'Vinh', 
    @SoDienThoai = '0988999999', 
    @Email = 'i@gmail.com';


-- test case
delete from HopDong
EXEC AddHopDong 
    @MaKH = 'KH0001', 
    @NgayKyKet = '2024-12-02', 
    @TongTien = 1500000.00, 
    @TrangThaiHopDong = 'Active';
