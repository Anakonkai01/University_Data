CREATE PROCEDURE AddKhachHang
    @TenKH NVARCHAR(100),
    @DiaChi NVARCHAR(255),
    @SoDienThoai VARCHAR(15),
    @Email VARCHAR(100)
AS
BEGIN
    DECLARE @NewMaKH VARCHAR(10);

    -- Sinh khóa chính tự động
    SELECT @NewMaKH = 'KH' + RIGHT('0000' + CAST(COALESCE(MAX(CAST(SUBSTRING(MaKH, 3, 4) AS INT)), 0) + 1 AS VARCHAR), 4)
    FROM KhachHang;

    -- Kiểm tra tính hợp lệ của số điện thoại và email
    IF EXISTS (SELECT 1 FROM KhachHang WHERE SoDienThoai = @SoDienThoai OR Email = @Email)
    BEGIN
        PRINT 'Hủy, Số điện thoại hoặc email đã tồn tại trong hệ thống.';
        RETURN;
    END;

    -- Thêm khách hàng mới
    INSERT INTO KhachHang (MaKH, TenKH, DiaChi, SoDienThoai, Email)
    VALUES (@NewMaKH, @TenKH, @DiaChi, @SoDienThoai, @Email);

    PRINT 'Khách hàng mới đã được thêm với mã: ' + @NewMaKH;
END;
GO





CREATE PROCEDURE AddHopDong
    @MaKH VARCHAR(10),
    @NgayKyKet DATE,
    @TongTien DECIMAL(18, 2),
    @TrangThaiHopDong VARCHAR(50)
AS
BEGIN
    DECLARE @NewMaHD VARCHAR(10);

    -- Sinh MaHD mới
    SELECT @NewMaHD = 'HD' + RIGHT('0000' + CAST(COALESCE(MAX(CAST(SUBSTRING(MaHD, 3, 4) AS INT)), 0) + 1 AS VARCHAR), 4)
    FROM HopDong;

    -- Kiểm tra tính hợp lệ
    IF NOT EXISTS (SELECT 1 FROM KhachHang WHERE MaKH = @MaKH)
    BEGIN
        PRINT 'Hủy, Mã khách hàng không tồn tại.';
        RETURN;
    END;

    IF @TongTien <= 0
    BEGIN
        PRINT 'Hủy, tổng tiền phải lớn hơn 0.';
        RETURN;
    END;

    -- Thêm hợp đồng mới
    INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong)
    VALUES (@NewMaHD, @MaKH, @NgayKyKet, @TongTien, @TrangThaiHopDong);

    PRINT 'Hợp đồng mới đã được thêm với mã: ' + @NewMaHD;
END;
GO





CREATE PROCEDURE AddSuKien
    @MaHD VARCHAR(10),
    @TenSuKien VARCHAR(100),
    @NgayToChuc DATE,
    @NgayKetThuc DATE,
    @DiaDiem VARCHAR(255),
    @MoTa VARCHAR(1000)
AS
BEGIN
    DECLARE @NewMaSK VARCHAR(10);

    -- Sinh MaSK mới
    SELECT @NewMaSK = 'SK' + RIGHT('0000' + CAST(COALESCE(MAX(CAST(SUBSTRING(MaSK, 3, 4) AS INT)), 0) + 1 AS VARCHAR), 4)
    FROM SuKien;

    -- Kiểm tra sự tồn tại của MaHD
    IF NOT EXISTS (SELECT 1 FROM HopDong WHERE MaHD = @MaHD)
    BEGIN
        PRINT 'Hủy, mã hợp đồng không tồn tại trong hệ thống.';
        RETURN;
    END;

    -- Kiểm tra xem MaHD đã được sử dụng trong SuKien hay chưa (đảm bảo quan hệ 1-1)
    IF EXISTS (SELECT 1 FROM SuKien WHERE MaHD = @MaHD)
    BEGIN
        PRINT 'Hủy, mã hợp đồng đã được liên kết với một sự kiện khác.';
        RETURN;
    END;

    -- Kiểm tra logic ngày
    IF @NgayToChuc > @NgayKetThuc
    BEGIN
        PRINT 'Hủy, ngày tổ chức không được lớn hơn ngày kết thúc.';
        RETURN;
    END;

    -- Thêm sự kiện mới
    INSERT INTO SuKien (MaSK, MaHD, TenSuKien, NgayToChuc, NgayKetThuc, DiaDiem, MoTa)
    VALUES (@NewMaSK, @MaHD, @TenSuKien, @NgayToChuc, @NgayKetThuc, @DiaDiem, @MoTa);

    PRINT 'Sự kiện mới đã được thêm với mã: ' + @NewMaSK;
END;
GO


drop proc AddHopDong
drop proc AddKhachHang
drop proc AddSuKien

