





-- cau 1  
delete from Nhanvien
where MaNV not in (
  select distinct MaNV from Phancong
)

-- cau 1 su dung left join
delete from Nhanvien
where MaNV in(
  select MaNV from Nhanvien as nv
  left join Phancong as pc on pc.MaNV = nv.MaNV
  where pc.MaNV is null
)


-- cau 2
update Duan
set TruongDuAn = (
  select MaNV from Nhanvien as nv
  join Phancong as pc on pc.MaNV = nv.MaNV
  group by MaNV
  having pc.NgayBD = MIN(NgayBD)
)
where Kinhphi > 100000000
-- cau 2 su dung order by
update Duan
set TruongDuAn = (
  select top 1 MaNV from Nhanvien as nv
  join Phancong as pc on pc.MaNV = nv.MaNV
  order by pc.NgayBD
)
where Kinhphi > 100000000

-- cau 3
select 
  nv.MaNV,
  nv.Hoten,
  SUM(DAY(pc.NgayKT) - DAY(pc.NgayBD)) as TongSoNgay
from Nhanvien as nv 
join Phancong as pc on pc.MaNV = nv.MaNV 
join Duan as da on da.MaDA = pc.MaDA
where da.Kinhphi > 50000000
group by nv.MaNV, nv.Hoten

-- cau 4
select 
  nv.MaNV,
  nv.Hoten
from Nhanvien as nv 
left join Phancong as pc on pc.MaNV = nv.MaNV
where YEAR(pc.NgayBD) = 2023 and pc.MaNV is null


-- cau 5 
select 
  top 1
  da.MaDA,
  da.TenDA,
  count(pc.MaNV) as SoLuongNhanVien
from Duan as da 
join Phancong as pc on pc.MaDA = pc.MaDA
group by da.MaDA, da.TenDA
order by SoLuongNhanVien DESC


-- cau 6
select 
  da.MaDA,
  da.TenDA,
  count(pc.MaNV) as SoLuongNhanVien
from Duan as da 
join Phancong as pc on pc.MaDA = pc.MaDA
where TruongDuAn is null
group by da.MaDA, da.TenDA


-- cau 7
select 
  nv.MaNV,
  nv.Hoten,
  AVG(da.Kinhphi) as trungbinhkinhphi
from Nhanvien as nv 
join Phancong as pc on pc.MaNV = nv.MaNV
join Duan as da on da.MaDA = pc.MaDA

