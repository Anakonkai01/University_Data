CREATE TRIGGER trg_CheckHopDong
ON HopDong
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @ErrorCount INT = 0;

    -- Kiểm tra khóa ngoại (MaKH phải tồn tại trong KhachHang)
    IF EXISTS (
        SELECT 1
        FROM inserted i
        LEFT JOIN KhachHang k ON i.MaKH = k.MaKH
        WHERE k.MaKH IS NULL
    )
    BEGIN
        RAISERROR (N'MaKH không tồn tại trong bảng KhachHang.', 16, 1);
        SET @ErrorCount = @ErrorCount + 1;
    END;

    -- Kiểm tra ràng buộc miền giá trị (TongTien phải >= 0)
    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE TongTien < 0
    )
    BEGIN
        RAISERROR (N'TongTien phải lớn hơn hoặc bằng 0.', 16, 1);
        SET @ErrorCount = @ErrorCount + 1;
    END;

    -- Kiểm tra mã hợp đồng đã tồn tại trong HopDong
    IF EXISTS (
        SELECT 1
        FROM inserted i
        JOIN HopDong h ON i.MaHD = h.MaHD
    )
    BEGIN
        RAISERROR (N'Mã hợp đồng đã tồn tại trong hệ thống.', 16, 1);
        SET @ErrorCount = @ErrorCount + 1;
    END;

    IF @ErrorCount > 0
    BEGIN
        ROLLBACK TRANSACTION;
        RETURN;
    END;
END;
GO



CREATE TRIGGER trg_CheckKhachHang
ON KhachHang
AFTER INSERT, UPDATE
AS
BEGIN
    -- Kiểm tra tính hợp lệ
    IF EXISTS (
        SELECT 1
        FROM inserted i
        WHERE i.TenKH IS NULL OR i.DiaChi IS NULL
    )
    BEGIN
        RAISERROR (N'Tên khách hàng và địa chỉ không được NULL.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;

    -- Kiểm tra trùng lặp số điện thoại hoặc email
    IF EXISTS (
        SELECT 1
        FROM inserted i
        JOIN KhachHang k ON (i.SoDienThoai = k.SoDienThoai OR i.Email = k.Email)
        WHERE i.MaKH <> k.MaKH
    )
    BEGIN
        RAISERROR (N'Số điện thoại hoặc email đã tồn tại.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;
END;
GO



CREATE TRIGGER trg_CheckSuKien
ON SuKien
AFTER INSERT, UPDATE
AS
BEGIN
    -- Kiểm tra sự tồn tại của MaHD
    IF EXISTS (
        SELECT 1
        FROM inserted i
        LEFT JOIN HopDong h ON i.MaHD = h.MaHD
        WHERE h.MaHD IS NULL
    )
    BEGIN
        RAISERROR (N'Mã hợp đồng không tồn tại trong hệ thống.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;

    -- Kiểm tra mối quan hệ 1-1 giữa HopDong và SuKien
    IF EXISTS (
        SELECT 1
        FROM inserted i
        JOIN SuKien s ON i.MaHD = s.MaHD
        WHERE i.MaSK <> s.MaSK
    )
    BEGIN
        RAISERROR (N'Mã hợp đồng đã được liên kết với một sự kiện khác.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;

    -- Kiểm tra logic ngày
    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE NgayToChuc > NgayKetThuc
    )
    BEGIN
        RAISERROR (N'Ngày tổ chức không được lớn hơn ngày kết thúc.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;
END;
GO


drop trigger trg_CheckHopDong
drop trigger trg_CheckKhachHang
drop trigger trg_CheckSuKien


