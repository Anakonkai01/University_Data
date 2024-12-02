CREATE TRIGGER trg_CheckHopDong
ON HopDong
AFTER INSERT, UPDATE
AS
BEGIN
    -- Kiểm tra khóa ngoại (MaKH phải tồn tại trong bảng KhachHang)
    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE NOT EXISTS (SELECT 1 FROM KhachHang WHERE KhachHang.MaKH = inserted.MaKH)
    )
    BEGIN
        RAISERROR ('MaKH không tồn tại trong bảng KhachHang.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;

    -- Kiểm tra miền giá trị (TongTien phải >= 0)
    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE TongTien < 0
    )
    BEGIN
        RAISERROR ('TongTien phải lớn hơn hoặc bằng 0.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;

    -- Lệnh Trigger kết thúc
END;



-- test case

INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong)
VALUES ('HD0006', 'KH0001', '2023-12-01', 12000000, 'Active');


INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong)
VALUES ('HD0007', 'KH999', '2023-12-01', 15000000, 'Active');

INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong)
VALUES ('HD0008', 'KH0001', '2023-12-01', -10000000, 'Active');

INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong)
VALUES ('HD0009', 'KH0001', NULL, 10000000, 'Active');

UPDATE HopDong
SET TongTien = 18000000

