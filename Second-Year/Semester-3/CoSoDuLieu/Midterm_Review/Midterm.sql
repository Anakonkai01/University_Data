SELECT NGK.TenNGK
FROM NGK
LEFT JOIN CTHD ON NGK.MaNGK = CTHD.MaNGK
LEFT JOIN Hoadon ON CTHD.Sohd = Hoadon.Sohd
WHERE Hoadon.Sohd IS NOT NULL AND YEAR(ngaylap) = 2024 AND MONTH(ngaylap) <> 3;