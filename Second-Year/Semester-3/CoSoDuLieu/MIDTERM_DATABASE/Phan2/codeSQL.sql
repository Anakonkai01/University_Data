CREATE DATABASE QuanLyChoThueToChucSuKien;
GO
USE QuanLyChoThueToChucSuKien;
GO




-- Bảng KhachHang
CREATE TABLE KhachHang (
    MaKH VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    TenKH NVARCHAR(100) NOT NULL,
    DiaChi NVARCHAR(255),
    SoDienThoai VARCHAR(15),
    Email NVARCHAR(100)
);

-- Bảng HopDong
CREATE TABLE HopDong (
    MaHD VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaKH VARCHAR(10) NOT NULL,
    NgayKyKet DATE NOT NULL,
    TongTien DECIMAL(18, 2) NOT NULL,
    TrangThaiHopDong NVARCHAR(50) NOT NULL,
    CONSTRAINT FK_HopDong_KhachHang FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);

-- Bảng SuKien
CREATE TABLE SuKien (
    MaSuKien VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaHD VARCHAR(10) NOT NULL,
    TenSuKien NVARCHAR(100) NOT NULL,
    NgayToChuc DATE NOT NULL,
    NgayKetThuc DATE NOT NULL,
    DiaDiem NVARCHAR(255),
    MoTa TEXT,
    CONSTRAINT FK_SuKien_HopDong FOREIGN KEY (MaHD) REFERENCES HopDong(MaHD)
);

-- Bảng DichVu
CREATE TABLE DichVu (
    MaDV VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    TenDV NVARCHAR(100) NOT NULL,
    DonGia DECIMAL(18, 2) NOT NULL,
    MoTa NVARCHAR(255),
    DonViTinh NVARCHAR(50),
    TinhTrang NVARCHAR(50)
);

-- Bảng ChiTietDichVuHopDong
CREATE TABLE ChiTietDichVuHopDong (
    MaChiTietDV VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaHD VARCHAR(10) NOT NULL,
    MaDV VARCHAR(10) NOT NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(18, 2) NOT NULL,
    ThanhTien AS (SoLuong * DonGia) PERSISTED, -- Tính tự động
    CONSTRAINT FK_ChiTietDVHD_HopDong FOREIGN KEY (MaHD) REFERENCES HopDong(MaHD),
    CONSTRAINT FK_ChiTietDVHD_DichVu FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV)
);

-- Bảng Loa
CREATE TABLE Loa (
    MaLoa VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaDV VARCHAR(10) NOT NULL,
    LoaiLoa NVARCHAR(100),
    CongSuat DECIMAL(18, 2),
    DonGia DECIMAL(18, 2),
    CONSTRAINT FK_Loa_DichVu FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV)
);

-- Bảng Den
CREATE TABLE Den (
    MaDen VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaDV VARCHAR(10) NOT NULL,
    LoaiDen NVARCHAR(100),
    CongSuat DECIMAL(18, 2),
    DonGia DECIMAL(18, 2),
    CONSTRAINT FK_Den_DichVu FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV)
);

-- Bảng NhaBanhU
CREATE TABLE NhaBanhU (
    MaNhaBanhU VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaDV VARCHAR(10) NOT NULL,
    DienTich DECIMAL(18, 2),
    DonGia DECIMAL(18, 2),
    CONSTRAINT FK_NhaBanhU_DichVu FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV)
);

-- Bảng NhaTienChe
CREATE TABLE NhaTienChe (
    MaNhaTienChe VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaDV VARCHAR(10) NOT NULL,
    DienTich DECIMAL(18, 2),
    DonGia DECIMAL(18, 2),
    CONSTRAINT FK_NhaTienChe_DichVu FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV)
);

-- Bảng CongHoiCho
CREATE TABLE CongHoiCho (
    MaCongHoiCho VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaDV VARCHAR(10) NOT NULL,
    LoaiCong NVARCHAR(100),
    DonGia DECIMAL(18, 2),
    CONSTRAINT FK_CongHoiCho_DichVu FOREIGN KEY (MaDV) REFERENCES DichVu(MaDV)
);

-- Bảng ThanhToan
CREATE TABLE ThanhToan (
    MaThanhToan VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaHD VARCHAR(10) NOT NULL,
    SoTien DECIMAL(18, 2) NOT NULL,
    NgayThanhToan DATE NOT NULL,
    PhuongThuc NVARCHAR(50),
    TrangThai NVARCHAR(50),
    CONSTRAINT FK_ThanhToan_HopDong FOREIGN KEY (MaHD) REFERENCES HopDong(MaHD)
);

-- Bảng NhanSu
CREATE TABLE NhanSu (
    MaNS VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    TenNS NVARCHAR(100) NOT NULL,
    ChucVu NVARCHAR(50),
    SoDienThoai VARCHAR(15),
    Email NVARCHAR(100)
);

-- Bảng PhanCong
CREATE TABLE PhanCong (
    MaPhanCong VARCHAR(10) PRIMARY KEY, -- Tự phát sinh
    MaSuKien VARCHAR(10) NOT NULL,
    MaNS VARCHAR(10) NOT NULL,
    VaiTro NVARCHAR(100),
    CONSTRAINT FK_PhanCong_SuKien FOREIGN KEY (MaSuKien) REFERENCES SuKien(MaSuKien),
    CONSTRAINT FK_PhanCong_NhanSu FOREIGN KEY (MaNS) REFERENCES NhanSu(MaNS)
);



-- b 
GO

CREATE FUNCTION GenerateMaKH()
RETURNS VARCHAR(10)
AS
BEGIN
    DECLARE @NewMaKH VARCHAR(10);
    SELECT @NewMaKH = 'KH' + RIGHT('0000' + CAST(ISNULL(MAX(CAST(SUBSTRING(MaKH, 3, 4) AS INT)), 0) + 1 AS VARCHAR), 4)
    FROM KhachHang;
    RETURN @NewMaKH;
END;

GO

CREATE PROCEDURE AddKhachHang
    @TenKH NVARCHAR(100),
    @DiaChi NVARCHAR(255),
    @SoDienThoai VARCHAR(15),
    @Email NVARCHAR(100)
AS
BEGIN
    DECLARE @MaKH VARCHAR(10) = dbo.GenerateMaKH();
    INSERT INTO KhachHang (MaKH, TenKH, DiaChi, SoDienThoai, Email)
    VALUES (@MaKH, @TenKH, @DiaChi, @SoDienThoai, @Email);
END;

GO

CREATE FUNCTION GenerateMaHD()
RETURNS VARCHAR(10)
AS
BEGIN
    DECLARE @NewMaHD VARCHAR(10);
    SELECT @NewMaHD = 'HD' + RIGHT('0000' + CAST(ISNULL(MAX(CAST(SUBSTRING(MaHD, 3, 4) AS INT)), 0) + 1 AS VARCHAR), 4)
    FROM HopDong;
    RETURN @NewMaHD;
END;

GO

CREATE PROCEDURE AddHopDong
    @MaKH VARCHAR(10),
    @NgayKyKet DATE,
    @TongTien DECIMAL(18, 2),
    @TrangThaiHopDong NVARCHAR(50)
AS
BEGIN
    DECLARE @MaHD VARCHAR(10) = dbo.GenerateMaHD();
    INSERT INTO HopDong (MaHD, MaKH, NgayKyKet, TongTien, TrangThaiHopDong)
    VALUES (@MaHD, @MaKH, @NgayKyKet, @TongTien, @TrangThaiHopDong);
END;


-- c
GO

CREATE TRIGGER trg_CheckHopDong
ON HopDong
AFTER INSERT, UPDATE
AS
BEGIN
    -- Kiểm tra TongTien > 0
    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE TongTien <= 0
    )
    BEGIN
        RAISERROR('TongTien phải lớn hơn 0.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;

    -- Kiểm tra MaKH tồn tại trong KhachHang
    IF EXISTS (
        SELECT 1
        FROM inserted i
        LEFT JOIN KhachHang k ON i.MaKH = k.MaKH
        WHERE k.MaKH IS NULL
    )
    BEGIN
        RAISERROR('MaKH không tồn tại trong bảng KhachHang.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END;
END;


GO


-- test case

EXEC AddKhachHang
    @TenKH = N'Nguyen Van A',
    @DiaChi = N'Ha Noi',
    @SoDienThoai = '0123456789',
    @Email = 'a@gmail.com';


EXEC AddHopDong
    @MaKH = 'KH0001',
    @NgayKyKet = '2024-12-01',
    @TongTien = 5000000,
    @TrangThaiHopDong = N'Đang hoạt động';


-- test trigger
EXEC AddHopDong
    @MaKH = 'KH0001',
    @NgayKyKet = '2024-12-01',
    @TongTien = 0,
    @TrangThaiHopDong = N'Đang hoạt động';
-- Lỗi: TongTien phải lớn hơn 0.

EXEC AddHopDong
    @MaKH = 'KH9999', -- Không tồn tại
    @NgayKyKet = '2024-12-01',
    @TongTien = 5000000,
    @TrangThaiHopDong = N'Đang hoạt động';
-- Lỗi: MaKH không tồn tại trong bảng KhachHang.





