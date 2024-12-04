-- Add HopDong with optional SuKien relationship
CREATE PROCEDURE AddHopDong
    @MaKH NVARCHAR(10),
    @NgayKyKet DATE,
    @TongTien DECIMAL(18, 2),
    @TrangThaiHopDong NVARCHAR(50),
    @MaSK NVARCHAR(10) = NULL -- Optional MaSK for linking SuKien
AS
BEGIN
    DECLARE @NewMaHD NVARCHAR(10);
    
    -- Generate new MaHD
    SELECT @NewMaHD = 'HD' + RIGHT('000' + CAST(COALESCE(MAX(CAST(SUBSTRING(MaHD, 3, 4) AS INT)), 0) + 1 AS NVARCHAR), 4)
    FROM HopDong;

    -- Check if MaKH exists
    IF NOT EXISTS (SELECT 1 FROM KhachHang WHERE MaKH = @MaKH)
    BEGIN
        RAISERROR ('MaKH không tồn tại trong bảng KhachHang.', 16, 1);
        RETURN;
    END

    -- Insert new HopDong
    INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong, MaSK)
    VALUES (@NewMaHD, @MaKH, @NgayKyKet, @TongTien, @TrangThaiHopDong, @MaSK);

    PRINT 'Hợp đồng mới được thêm với mã: ' + @NewMaHD;
END;
GO

-- Add SuKien
CREATE PROCEDURE AddSuKien
    @MaHD NVARCHAR(10),
    @TenSuKien NVARCHAR(100),
    @NgayToChuc DATE,
    @NgayKetThuc DATE,
    @DiaDiem NVARCHAR(255),
    @MoTa NVARCHAR(1000)
AS
BEGIN
    DECLARE @NewMaSK NVARCHAR(10);
    
    -- Generate new MaSK
    SELECT @NewMaSK = 'SK' + RIGHT('000' + CAST(COALESCE(MAX(CAST(SUBSTRING(MaSK, 3, 4) AS INT)), 0) + 1 AS NVARCHAR), 4)
    FROM SuKien;

    -- Check if MaHD exists
    IF NOT EXISTS (SELECT 1 FROM HopDong WHERE MaHD = @MaHD)
    BEGIN
        RAISERROR ('MaHD không tồn tại trong bảng HopDong.', 16, 1);
        RETURN;
    END

    -- Insert new SuKien
    INSERT INTO SuKien (MaSK, MaHD, TenSuKien, NgayToChuc, NgayKetThuc, DiaDiem, MoTa)
    VALUES (@NewMaSK, @MaHD, @TenSuKien, @NgayToChuc, @NgayKetThuc, @DiaDiem, @MoTa);

    PRINT 'Sự kiện mới được thêm với mã: ' + @NewMaSK;
END;
GO




-- test case
EXEC AddHopDong 
    @MaKH = 'KH0001', 
    @NgayKyKet = '2024-12-02', 
    @TongTien = 1500000.00, 
    @TrangThaiHopDong = 'Active', 
    @MaSK = NULL;

EXEC AddSuKien
    @MaHD = 'HD0001',
    @TenSuKien = N'Sự kiện Giáng sinh',
    @NgayToChuc = '2024-12-24',
    @NgayKetThuc = '2024-12-25',
    @DiaDiem = N'Hà Nội',
    @MoTa = N'Lễ hội mừng Giáng sinh.';

