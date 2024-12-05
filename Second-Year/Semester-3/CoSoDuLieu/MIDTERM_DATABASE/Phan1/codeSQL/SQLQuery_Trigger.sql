CREATE TRIGGER trg_CheckHopDong
ON HopDong
AFTER INSERT, UPDATE
AS
BEGIN
    -- Khai báo biến đếm lỗi
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

    -- Kiểm tra ràng buộc Not Null (NgayKyKet không được NULL)
    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE NgayKyKet IS NULL
    )
    BEGIN
        RAISERROR (N'NgayKyKet không được NULL.', 16, 1);
        SET @ErrorCount = @ErrorCount + 1;
    END;

    -- Nếu có lỗi, rollback giao dịch
    IF @ErrorCount > 0
    BEGIN
        ROLLBACK TRANSACTION;
        RETURN;
    END;

    -- Kết thúc Trigger
END;
GO
