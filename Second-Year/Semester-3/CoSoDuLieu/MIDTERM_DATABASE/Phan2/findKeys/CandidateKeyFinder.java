package Phan2.findKeys;

import java.io.*;
import java.util.*;

public class CandidateKeyFinder {

    private String schema;
    private List<String> attributes;
    private Map<Set<String>, Set<String>> functionalDependencies;

    public CandidateKeyFinder() {
        this.attributes = new ArrayList<>();
        this.functionalDependencies = new HashMap<>();
    }

    // Đọc file Input2.txt
    public void readInput(String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            schema = reader.readLine().trim();
            attributes = Arrays.asList(schema.substring(2, schema.length() - 1).split(",\\s*"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                Set<String> lhs = new HashSet<>(Arrays.asList(parts[0].trim().split(",\\s*")));
                Set<String> rhs = new HashSet<>(Arrays.asList(parts[1].trim().split(",\\s*")));
                functionalDependencies.put(lhs, rhs);
            }
        }
    }

    // Tính bao đóng của một tập thuộc tính
    public Set<String> computeClosure(Set<String> attributesSet) {
        Set<String> closure = new HashSet<>(attributesSet);
        boolean changed;

        do {
            changed = false;
            for (Map.Entry<Set<String>, Set<String>> entry : functionalDependencies.entrySet()) {
                if (closure.containsAll(entry.getKey()) && !closure.containsAll(entry.getValue())) {
                    closure.addAll(entry.getValue());
                    changed = true;
                }
            }
        } while (changed);

        return closure;
    }

    // Kiểm tra nếu một tập thuộc tính là khóa
    private boolean isKey(Set<String> candidateKey) {
        return computeClosure(candidateKey).containsAll(attributes);
    }

    // Tìm tất cả khóa ứng viên
    public List<Set<String>> findCandidateKeys() {
        List<Set<String>> candidateKeys = new ArrayList<>();
        int n = attributes.size();
        List<String> attrList = new ArrayList<>(attributes);

        for (int i = 1; i <= n; i++) {
            // Tạo các tập con kích thước i
            List<Set<String>> subsets = generateSubsets(attrList, i);

            for (Set<String> subset : subsets) {
                if (isKey(subset) && !isSupersetOf(candidateKeys, subset)) {
                    candidateKeys.add(subset);
                }
            }
        }

        return candidateKeys;
    }

    // Kiểm tra nếu một tập là tập con của tập khác
    private boolean isSupersetOf(List<Set<String>> candidateKeys, Set<String> subset) {
        for (Set<String> key : candidateKeys) {
            if (key.containsAll(subset)) {
                return true;
            }
        }
        return false;
    }

    // Tạo tất cả tập con kích thước k
    private List<Set<String>> generateSubsets(List<String> list, int k) {
        List<Set<String>> subsets = new ArrayList<>();
        generateSubsetsHelper(list, new HashSet<>(), 0, k, subsets);
        return subsets;
    }

    private void generateSubsetsHelper(List<String> list, Set<String> current, int index, int k, List<Set<String>> subsets) {
        if (current.size() == k) {
            subsets.add(new HashSet<>(current));
            return;
        }

        if (index == list.size()) {
            return;
        }

        current.add(list.get(index));
        generateSubsetsHelper(list, current, index + 1, k, subsets);
        current.remove(list.get(index));
        generateSubsetsHelper(list, current, index + 1, k, subsets);
    }

    // Ghi kết quả vào file Output2.txt
    public void writeOutput(String outputFilePath, List<Set<String>> candidateKeys) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Candidate Keys:\n");
            for (Set<String> key : candidateKeys) {
                writer.write(key.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        try {
            CandidateKeyFinder finder = new CandidateKeyFinder();
            finder.readInput("Input2.txt");
            List<Set<String>> candidateKeys = finder.findCandidateKeys();
            finder.writeOutput("Output2.txt", candidateKeys);
            System.out.println("Candidate keys have been written to Output2.txt");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
