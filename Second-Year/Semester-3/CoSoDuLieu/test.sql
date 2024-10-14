

--g. Thống kê giảm dần số lượng NGK được bán trong năm 2023. Thông tin hiển thị bao 
-- gồm: MaNGK, TenNGK, số lượng bán.

-- g
select
    n.MaNGK, 
    n.TenNGK,
    SUM(ct.soluong) as soluongban
from NGK as n 
join CTHD as ct  on ct.mangk = n.mangk 
join hoadon as hd on hd.sohd = ct.sohd 
where YEAR(ngaylap) = 2023
group by n.MaNGK, n.TenNGK
order by soluongban DESC


-- f
select
    kh.Mskh, 
    kh.hoten,
    COUNT(ct.soluong) as soluot
from khachhang as kh 
join hoadon as hd on hd.mskh = kh.mskh 
where YEAR(ngaylap) = 2023
group by kh.mskh,kh.hoten
order by soluot DESC



-- h (ban lai sau)
select
    top 1
    n.MaNGK, 
    n.TenNGK,
    SUM(ct.soluong) as soluongban
from NGK as n 
join CTHD as ct  on ct.mangk = n.mangk 
join hoadon as hd on hd.sohd = ct.sohd 
where YEAR(ngaylap) = 2023
group by n.MaNGK, n.TenNGK
order by soluongban DESC 



select 
    hd.sohd, 
    hd.ngaylap,
    SUM(ct.soluong * ct.dongia) as tongtien
from hoadon as hd
join cthd as ct on ct.sohd = hd.sohd
group by hd.sohd, hd.ngaylap
having SUM(ct.soluong * ct.dongia) > 10000000



-- cau 3
select 
    n.*
from ngk as n 
join cthd as ct on ct.mangk = n.mangk 
join hoadon as hd on hd.sohd = ct.sohd 
where year(hd.ngaylap) = 2018 and MONTH(hd.ngaylap) in (7,8,9)
-- where ngaylap between '2018-07-01' and '2018-09-30'


select 
    n.MaNGK, 
    n.TenNGK,
    sum(ct.soluong) as soluongban
from ngk as n
join cthd as ct on ct.mangk = n.mangk 
group by n.mangk, n.tenngk 




-- e 
select 
    hd.sohd
from hoadon as hd
join cthd as ct on ct.sohd = hd.sohd 
join ngk as n on n.mangk = ct.mangk
join loaingk as l on l.maloai = n.maloaingk
where l.tenloai = 'nuoc co ga' 

INTERSECT

select 
    hd.sohd
from hoadon as hd
join cthd as ct on ct.sohd = hd.sohd 
join ngk as n on n.mangk = ct.mangk
join loaingk as l on l.maloai = n.maloaingk
where l.tenloai = 'nuoc ngot'



-- liet ke nhung nuoc giai khat chua duoc ban lan nao 


select 
    *
FROM NGK 
WHERE MaNGK NOT IN (
    select MaNGK FROM CTHD 
)

select n.* from ngk as n
left join cthd as ct on ct.mangk = n.mangk 
where ct.mangk is null 


-- Liệt kê Tên NGK không được mua trong tháng 3 năm 2024.
select 
    n.MaNGK, 
    n.TenNGK
from NGK as n 
where n.MaNGK NOT IN(
    select ct.MaNGK from CTHD as ct
    join Hoadon as hd on hd.sohd = ct.sohd 
    where YEAR(hd.ngaylap) = 2024 and month(ngaylap) = 3
)

select 
    n.MaNGK, 
    n.TenNGK
from NGK as n 
left join cthd as ct on ct.mangk = n.mangk
where ct.sohd not in (
    select hd.sohd from hoadon 
    where YEAR(hd.ngaylap) = 2024 and MONTH(hd.ngaylap) = 3
);




SELECT n.MaNGK, n.TenNGK
FROM NGK AS n
LEFT JOIN CTHD AS ct ON ct.MaNGK = n.MaNGK
JOIN Hoadon AS hd ON ct.Sohd = hd.Sohd and (YEAR(hd.Ngaylap)= 2024 and MONTH(hd.Ngaylap) = 3) 
WHERE ct.mangk IS NULL;


SELECT n.MaNGK, n.TenNGK
FROM NGK AS n
LEFT JOIN CTHD AS ct ON ct.MaNGK = n.MaNGK
LEFT JOIN Hoadon AS hd ON ct.Sohd = hd.Sohd 
    AND (YEAR(hd.Ngaylap) = 2024 AND MONTH(hd.Ngaylap) = 3)
WHERE hd.Sohd IS NULL;

