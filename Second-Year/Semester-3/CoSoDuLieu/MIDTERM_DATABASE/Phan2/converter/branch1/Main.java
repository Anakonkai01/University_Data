package branch1;

public class Main {
    public static void main(String[] args) {
        // Tạo thực thể
        Entity sinhVien = new Entity("SinhVien");
        sinhVien.addAttribute(new Attribute("MaSV", true, false, false));
        sinhVien.addAttribute(new Attribute("HoTen", false, false, false));
        sinhVien.addAttribute(new Attribute("NgaySinh", false, false, false));

        Entity lopHoc = new Entity("LopHoc");
        lopHoc.addAttribute(new Attribute("MaLop", true, false, false));
        lopHoc.addAttribute(new Attribute("TenLop", false, false, false));

        // Tạo quan hệ
        Relationship quanHe1N = new Relationship("ThuocLop", sinhVien, lopHoc, "1-N");

        // Chuyển đổi
        ERDToRelationalConverter converter = new ERDToRelationalConverter();
        converter.convertStrongEntity(sinhVien);
        converter.convertStrongEntity(lopHoc);
        converter.convertRelationship(quanHe1N);
    }
}

