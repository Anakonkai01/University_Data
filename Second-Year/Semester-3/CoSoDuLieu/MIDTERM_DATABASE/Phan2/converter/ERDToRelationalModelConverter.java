import java.io.*;
import java.util.*;

public class ERDToRelationalModelConverter {
    private List<String> inputLines;
    private List<String> outputLines;

    public ERDToRelationalModelConverter() {
        this.inputLines = new ArrayList<>();
        this.outputLines = new ArrayList<>();
    }

    // Đọc file input
    public void readFile(String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputLines.add(line.trim());
            }
        }
    }

    // Ghi file output
    public void writeFile(String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String line : outputLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Phân tích và chuyển đổi
    public void parseLines() {
        for (String line : inputLines) {
            if (!line.isEmpty()) {
                outputLines.add(convertToRelationalModel(line));
            }
        }
    }

    // Chuyển đổi ERD sang mô hình quan hệ
    private String convertToRelationalModel(String erdLine) {
        // Sử dụng lớp hỗ trợ ERDParser để xử lý cú pháp
        ERDParser parser = new ERDParser();
        ERDModel model = parser.parse(erdLine);

        // Áp dụng quy tắc chuyển đổi dựa trên loại quan hệ
        if (model.getRelationshipType().equals("n - 1")) {
            model.addForeignKeyToChild();
        } else if (model.getRelationshipType().equals("n - n")) {
            model.createJoinTable();
        }

        return model.toString();
    }

    // Xử lý toàn bộ quy trình
    public void process(String inputFilePath, String outputFilePath) throws IOException {
        readFile(inputFilePath);
        parseLines();
        writeFile(outputFilePath);
    }
}
