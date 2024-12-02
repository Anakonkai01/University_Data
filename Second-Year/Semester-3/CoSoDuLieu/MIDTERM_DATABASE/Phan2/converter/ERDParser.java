public class ERDParser {

    // Phân tích một dòng ERD và trả về mô hình đối tượng
    public ERDModel parse(String erdLine) {
        String[] parts = erdLine.split("–");
        String leftPart = parts[0].trim();
        String rightPart = parts[1].trim();
        String[] relationAndType = rightPart.split(":");

        String leftEntity = leftPart.split("\\(")[0].trim();
        String rightEntity = relationAndType[0].trim();
        String relationshipType = relationAndType[1].trim();

        return new ERDModel(leftEntity, rightEntity, relationshipType);
    }
}

