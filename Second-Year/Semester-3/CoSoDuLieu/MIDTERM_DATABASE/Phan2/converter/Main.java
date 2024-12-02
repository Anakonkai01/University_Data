import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            // Test case 1: Thực thể mạnh
            // runTestCase(
            //         "testcase1_input.txt",
            //         "testcase1_output.txt",
            //         """
            //                 [Sinh vien] (Masv, Hoten, Ngaysinh) (Masv: PK)
            //                 """,
            //         """
            //                 [Sinh vien] (Masv, Hoten, Ngaysinh) (Masv: PK)
            //                 """);

            // Test case 2: Thực thể yếu
            // runTestCase(
            //         "testcase2_input.txt",
            //         "testcase2_output.txt",
            //         """
            //                 [Nhan than] (MaNT, Hoten) (MaNT: Partial Key) - [Nhan vien] (MaNV) (MaNV: PK): n - 1
            //                 """,
            //         """
            //                 [Nhan vien] (MaNV) (MaNV: PK)
            //                 [Nhan than] (MaNT, MaNV, Hoten) (PK: MaNT, MaNV) (FK: MaNV)
            //                 """);

            // Test case 3: Mối quan hệ 1-1
            runTestCase(
                    "testcase3_input.txt",
                    "testcase3_output.txt",
                    """
                            [Nhan vien] (MaNV, Ten) (MaNV: PK) - [Phong ban] (MaPB, TenPB) (MaPB: PK): 1 - 1
                            """,
                    """
                            [Nhan vien] (MaNV, Ten, MaPB) (PK: MaNV, FK: MaPB)
                            [Phong ban] (MaPB, TenPB) (PK: MaPB)
                            """);

            // Test case 4: Mối quan hệ 1-n
            runTestCase(
                    "testcase4_input.txt",
                    "testcase4_output.txt",
                    """
                            [Lop hoc] (MaLop, TenLop) (MaLop: PK) - [Sinh vien] (Masv, Hoten) (Masv: PK): 1 - n
                            """,
                    """
                            [Lop hoc] (MaLop, TenLop) (PK: MaLop)
                            [Sinh vien] (Masv, Hoten, MaLop) (PK: Masv, FK: MaLop)
                            """);

            // Test case 5: Mối quan hệ n-n
            runTestCase(
                    "testcase5_input.txt",
                    "testcase5_output.txt",
                    """
                            [Sinh vien] (Masv, Hoten) (Masv: PK) - [Mon hoc] (MaMH, TenMH) (MaMH: PK): n - n
                            """,
                    """
                            [Sinh vien] (Masv, Hoten) (PK: Masv)
                            [Mon hoc] (MaMH, TenMH) (PK: MaMH)
                            [Sinhvien_Monhoc] (Masv, MaMH) (PK: Masv, MaMH, FK: Masv, MaMH)
                            """);

            // Test case 6: Thuộc tính đa trị
            // runTestCase(
            //         "testcase6_input.txt",
            //         "testcase6_output.txt",
            //         """
            //                 [Nhan vien] (MaNV, Ten, SoDT) (MaNV: PK, SoDT: Multivalued)
            //                 """,
            //         """
            //                 [Nhan vien] (MaNV, Ten) (PK: MaNV)
            //                 [Nhanvien_SoDT] (MaNV, SoDT) (PK: MaNV, SoDT, FK: MaNV)
            //                 """);

            // Test case 7: Mối quan hệ có thuộc tính
            runTestCase(
                    "testcase7_input.txt",
                    "testcase7_output.txt",
                    """
                            [Nhan vien] (MaNV, Ten) (MaNV: PK) - [Du an] (MaDA, TenDA) (MaDA: PK) với [ThoiGian]
                            """,
                    """
                            [Nhan vien] (MaNV, Ten) (PK: MaNV)
                            [Du an] (MaDA, TenDA) (PK: MaDA)
                            [Nhanvien_Duan] (MaNV, MaDA, ThoiGian) (PK: MaNV, MaDA, FK: MaNV, MaDA)
                            """);

            // Test case 8: Thực thể con (tổng quát hóa/ chuyên biệt hóa)
            // runTestCase(
            //         "testcase8_input.txt",
            //         "testcase8_output.txt",
            //         """
            //                 [Nhan vien] (MaNV, Ten, Loai) (MaNV: PK)
            //                 Loai: IT, HR
            //                 """,
            //         """
            //                 [Nhan vien] (MaNV, Ten, Loai) (PK: MaNV)
            //                 [Nhanvien_IT] (MaNV) (PK: MaNV, FK: MaNV)
            //                 [Nhanvien_HR] (MaNV) (PK: MaNV, FK: MaNV)
            //                 """);

        } catch (IOException e) {
            System.err.println("Test case execution failed: " + e.getMessage());
        }
    }

    private static void runTestCase(String inputFile, String outputFile, String inputContent,
            String expectedOutputContent) throws IOException {
        // Ghi dữ liệu test case vào file input
        writeToFile(inputFile, inputContent);

        // Chạy quá trình chuyển đổi
        ERDToRelationalModelConverter converter = new ERDToRelationalModelConverter();
        converter.process(inputFile, outputFile);

        // Đọc kết quả từ file output
        String actualOutputContent = readFromFile(outputFile);

        // So sánh kết quả
        if (actualOutputContent.trim().equals(expectedOutputContent.trim())) {
            System.out.println("Test case passed: " + inputFile);
        } else {
            System.out.println("Test case failed: " + inputFile);
            System.out.println("Expected:\n" + expectedOutputContent);
            System.out.println("Actual:\n" + actualOutputContent);
        }
    }

    private static void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private static String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
