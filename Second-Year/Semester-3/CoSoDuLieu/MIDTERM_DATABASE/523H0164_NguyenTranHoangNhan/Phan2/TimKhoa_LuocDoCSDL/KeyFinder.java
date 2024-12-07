import java.io.*;
import java.util.*;

public class KeyFinder {
    // Lưu trữ các thuộc tính của lược đồ
    private List<String> attributes;
    // Lưu trữ các phụ thuộc hàm
    private List<FunctionalDependency> dependencies;

    // Lớp biểu diễn phụ thuộc hàm
    private class FunctionalDependency {
        Set<String> left;  // Các thuộc tính bên trái
        Set<String> right; // Các thuộc tính bên phải

        FunctionalDependency(Set<String> left, Set<String> right) {
            this.left = left;
            this.right = right;
        }
    }

    public KeyFinder() {
        attributes = new ArrayList<>();
        dependencies = new ArrayList<>();
    }

    // Đọc input từ file
    public void readInput(String inputFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            // Đọc dòng đầu tiên chứa các thuộc tính
            String attributeLine = br.readLine();
            parseAttributes(attributeLine);

            // Đọc các phụ thuộc hàm
            String line;
            while ((line = br.readLine()) != null) {
                parseDependency(line);
            }
        }
    }

    // Phân tích chuỗi thuộc tính
    private void parseAttributes(String line) {
        // Loại bỏ các ký tự không cần thiết và tách các thuộc tính
        line = line.replaceAll("[R()]", "").trim();
        String[] attrs = line.split(",\\s*");
        attributes.addAll(Arrays.asList(attrs));
    }

    // Phân tích phụ thuộc hàm
    private void parseDependency(String line) {
        String[] parts = line.split("->");
        if (parts.length != 2) return; // Bỏ qua nếu không đúng định dạng

        // Phân tích các thuộc tính bên trái
        Set<String> leftAttributes = new HashSet<>();
        for (char c : parts[0].trim().toCharArray()) {
            leftAttributes.add(String.valueOf(c));
        }

        // Phân tích các thuộc tính bên phải
        Set<String> rightAttributes = new HashSet<>();
        for (char c : parts[1].trim().toCharArray()) {
            rightAttributes.add(String.valueOf(c));
        }

        // Thêm phụ thuộc hàm vào danh sách
        dependencies.add(new FunctionalDependency(leftAttributes, rightAttributes));
    }

    // Tính bao đóng của một tập thuộc tính
    private Set<String> closure(Set<String> initialSet) {
        Set<String> closure = new HashSet<>(initialSet);
        boolean changed;  // Biến kiểm tra xem tập thuộc tính đã thay đổi chưa

        // vòng lặp thực hiện cho đến khi không thể thêm thuộc tính mới vào tập
        do {
            changed = false;
            for (FunctionalDependency fd : dependencies) {
                if (closure.containsAll(fd.left)) {
                    for (String attr : fd.right) {
                        if (!closure.contains(attr)) {
                            closure.add(attr);
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);

        return closure;
    }

    // Tìm tất cả các khóa của lược đồ (không lọc tối thiểu)
    public Set<Set<String>> findAllKeys() {
        Set<Set<String>> allKeys = new HashSet<>();
        int n = attributes.size();

        // Duyệt qua tất cả các bitmask (2^n tổ hợp)
        for (int mask = 1; mask < (1 << n); mask++) {
            Set<String> subset = new HashSet<>();

            // Tạo tập con dựa trên bitmask
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(attributes.get(i));
                }
            }

            // Kiểm tra nếu tập con này là một khóa
            if (closure(subset).containsAll(attributes)) {
                allKeys.add(subset); // Thêm vào tất cả các khóa
            }
        }

        return allKeys;
    }

    // Ghi tất cả các khóa ra file
    public void writeAllKeys(String outputFile) throws IOException {
        Set<Set<String>> allKeys = findAllKeys();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Tổng số khóa tìm được: " + allKeys.size());
            bw.newLine();

            for (Set<String> key : allKeys) {
                bw.write(key.toString());
                bw.newLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            KeyFinder keyFinder = new KeyFinder();
            keyFinder.readInput("input2.txt");
            keyFinder.writeAllKeys("output2.txt");
            System.out.println("Đã tìm và ghi tất cả các khóa thành công!");
        } catch (IOException e) {
            System.err.println("Lỗi khi xử lý file: " + e.getMessage());
        }
    }
}
