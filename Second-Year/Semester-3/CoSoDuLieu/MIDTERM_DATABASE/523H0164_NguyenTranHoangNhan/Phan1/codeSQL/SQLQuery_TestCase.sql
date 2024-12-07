USE QuanLyChoThueToChucSuKien;
GO

-- valid
EXEC AddKhachHang 
    @TenKH = N'Nguyễn Văn B',
    @DiaChi = N'456 Đường XYZ',
    @SoDienThoai = '0901234568',
    @Email = 'nguyenvanb@gmail.com';

-- invalid
EXEC AddKhachHang 
    @TenKH = N'Lê Thị C',
    @DiaChi = N'789 Đường DEF',
    @SoDienThoai = '0901234567', -- Số đã tồn tại
    @Email = 'lethic@gmail.com';

-- valid
EXEC AddHopDong 
    @MaKH = 'KH0002',
    @NgayKyKet = '2024-12-10',
    @TongTien = 2000000,
    @TrangThaiHopDong = N'Hoàn thành';

-- invalid
EXEC AddHopDong 
    @MaKH = 'KH9999', -- Không tồn tại
    @NgayKyKet = '2024-12-15',
    @TongTien = 1500000,
    @TrangThaiHopDong = N'Chờ xử lý';

-- valid
EXEC AddSuKien 
    @MaHD = 'HD0002',
    @TenSuKien = N'Khai trương chi nhánh mới',
    @NgayToChuc = '2024-12-20',
    @NgayKetThuc = '2024-12-21',
    @DiaDiem = N'Tòa Nhà ABC',
    @MoTa = N'Lễ khai trương chi nhánh mới.';

-- invalid
EXEC AddSuKien 
    @MaHD = 'HD0002',
    @TenSuKien = N'Tất niên công ty',
    @NgayToChuc = '2024-12-25',
    @NgayKetThuc = '2024-12-20', -- Ngày tổ chức lớn hơn ngày kết thúc
    @DiaDiem = N'Tòa Nhà DEF',
    @MoTa = N'Tất niên công ty năm 2024.';
