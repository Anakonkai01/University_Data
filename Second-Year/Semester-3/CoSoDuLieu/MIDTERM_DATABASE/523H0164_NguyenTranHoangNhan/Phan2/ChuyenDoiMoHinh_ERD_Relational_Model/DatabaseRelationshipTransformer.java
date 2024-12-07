import java.util.*;
import java.io.*;
import java.util.regex.*;

public class DatabaseRelationshipTransformer {
    // Enum để đại diện cho những quan hệ
    enum RelationshipType {
        ONE_TO_MANY,
        MANY_TO_MANY,
        ONE_TO_ONE,
        MULTI_VALUED_ATTRIBUTE,
        PARENT_CHILD
    }

    // Class đại diện cho thực thể hay table
    static class Entity {
        String name;
        List<String> attributes;
        List<String> primaryKeys;
        List<String> foreignKeys;
        List<String> multiValuedAttributes;

        public Entity(String name) {
            this.name = name;
            this.attributes = new ArrayList<>();
            this.primaryKeys = new ArrayList<>();
            this.foreignKeys = new ArrayList<>();
            this.multiValuedAttributes = new ArrayList<>();
        }

        public void addAttribute(String attribute) {
            attributes.add(attribute);
        }

        public void addPrimaryKey(String pk) {
            primaryKeys.add(pk);
        }

        public void addForeignKey(String fk) {
            foreignKeys.add(fk);
        }

        public void addMultiValuedAttribute(String attr) {
            multiValuedAttributes.add(attr);
        }

        public Entity copy() {
            Entity newEntity = new Entity(this.name);
            newEntity.attributes.addAll(this.attributes);
            newEntity.primaryKeys.addAll(this.primaryKeys);
            newEntity.foreignKeys.addAll(this.foreignKeys);
            newEntity.multiValuedAttributes.addAll(this.multiValuedAttributes);
            return newEntity;
        }

        public String formatEntityString() {
            // kết hợp các thuộc tính với khóa chính và khóa ngoại
            List<String> orderedAttributes = new ArrayList<>();
            orderedAttributes.addAll(primaryKeys);
            orderedAttributes.addAll(foreignKeys);

            // xóa khóa chính và khóa ngoại khỏi danh sách thuộc tính để tránh trùng lặp
            List<String> otherAttributes = new ArrayList<>(attributes);
            otherAttributes.removeAll(primaryKeys);
            otherAttributes.removeAll(foreignKeys);

            orderedAttributes.addAll(otherAttributes);

            return String.join(", ", orderedAttributes);
        }

        public String formatKeysString() {
            List<String> keyStrings = new ArrayList<>();
            for (String pk : primaryKeys) {
                keyStrings.add(pk + ": PK");
            }
            for (String fk : foreignKeys) {
                keyStrings.add(fk + ": FK");
            }
            for (String mv : multiValuedAttributes) {
                keyStrings.add(mv + ": Multivalued");
            }
            return String.join(", ", keyStrings);
        }
    }

    // Class đại diện cho một quan hệ
    static class Relationship {
        Entity entity1;
        Entity entity2;
        RelationshipType type;
        String relationshipDescriptor;

        public Relationship(Entity entity1, Entity entity2, RelationshipType type) {
            this.entity1 = entity1;
            this.entity2 = entity2;
            this.type = type;
        }
    }

    // chuyển đổi quan hệ thành các thực thể
    public static List<Entity> transformRelationship(Relationship relationship) {
        List<Entity> transformedEntities = new ArrayList<>();

        switch (relationship.type) {
            case ONE_TO_MANY:
                // thêm khóa ngoại vào bên nhiều
                Entity manyEntity = relationship.entity2.copy();
                manyEntity.addAttribute(relationship.entity1.primaryKeys.get(0));
                manyEntity.addForeignKey(relationship.entity1.primaryKeys.get(0));

                transformedEntities.add(relationship.entity1);
                transformedEntities.add(manyEntity);
                break;

            case PARENT_CHILD:
                // xem như là 1-1 và thêm khóa ngoại vào bên con
                Entity childEntity = relationship.entity2.copy();
                childEntity.addAttribute(relationship.entity1.primaryKeys.get(0));
                childEntity.addForeignKey(relationship.entity1.primaryKeys.get(0));

                transformedEntities.add(relationship.entity1);
                transformedEntities.add(childEntity);
                break;

            case MANY_TO_MANY:
                // tạo thực thể trung gian
                Entity intermediaryEntity = new Entity(
                    relationship.entity1.name + "_" + relationship.entity2.name
                );
                intermediaryEntity.addAttribute(relationship.entity1.primaryKeys.get(0));
                intermediaryEntity.addAttribute(relationship.entity2.primaryKeys.get(0));
                intermediaryEntity.addPrimaryKey(relationship.entity1.primaryKeys.get(0));
                intermediaryEntity.addPrimaryKey(relationship.entity2.primaryKeys.get(0));
                intermediaryEntity.addForeignKey(relationship.entity1.primaryKeys.get(0));
                intermediaryEntity.addForeignKey(relationship.entity2.primaryKeys.get(0));

                transformedEntities.add(relationship.entity1);
                transformedEntities.add(relationship.entity2);
                transformedEntities.add(intermediaryEntity);
                break;

            case ONE_TO_ONE:
                // thêm khóa ngoại vào bên thứ 2
                Entity oneToOneEntity = relationship.entity2.copy();
                oneToOneEntity.addAttribute(relationship.entity1.primaryKeys.get(0));
                oneToOneEntity.addForeignKey(relationship.entity1.primaryKeys.get(0));

                transformedEntities.add(relationship.entity1);
                transformedEntities.add(oneToOneEntity);
                break;

            case MULTI_VALUED_ATTRIBUTE:
                // xử lý thuộc tính đa giá trị
                Entity mainEntity = relationship.entity1.copy();
                // xóa thuộc tính đa giá trị khỏi thực thể chính
                mainEntity.attributes.removeAll(relationship.entity1.multiValuedAttributes);

                for (String mvAttr : relationship.entity1.multiValuedAttributes) {
                    //  tạo bảng riêng cho mỗi thuộc tính đa giá trị
                    Entity multiValuedEntity = new Entity(
                        relationship.entity1.name + "_" + mvAttr
                    );
                    multiValuedEntity.addAttribute(relationship.entity1.primaryKeys.get(0));
                    multiValuedEntity.addAttribute(mvAttr);
                    multiValuedEntity.addPrimaryKey(relationship.entity1.primaryKeys.get(0));
                    multiValuedEntity.addPrimaryKey(mvAttr);
                    multiValuedEntity.addForeignKey(relationship.entity1.primaryKeys.get(0));

                    transformedEntities.add(mainEntity);
                    transformedEntities.add(multiValuedEntity);
                }
                break;
        }

        return transformedEntities;
    }

    // đọc từ file input và ghi kết quả vào file output
    // mỗi quan hệ được chuyển thành các thực thể và được ghi vào file output
    public static void processInputFile(String inputFile, String outputFile) throws IOException {
        // mở file input và output
        // sử dụng BufferedReader và BufferedWriter để đọc và ghi file
        BufferedReader reader = new BufferedReader(new FileReader(inputFile)); 
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // bỏ qua dòng trống

            // đọc từng dòng và chuyển thành quan hệ
            Relationship relationship = parseRelationshipLine(line); // gọi hàm parseRelationshipLine để phân tích dòng đầu vào

            // chuyển quan hệ thành các thực thể
            List<Entity> transformedEntities = transformRelationship(relationship); // gọi hàm transformRelationship để chuyển quan hệ thành các thực thể

            // ghi kết quả vào file output
            if (relationship.type == RelationshipType.MULTI_VALUED_ATTRIBUTE) { // xử lý thuộc tính đa giá trị
                //  thực thể chính và thực thể đa giá trị được kết nối bằng dấu -
                Entity mainEntity = transformedEntities.get(0);
                Entity multiValuedEntity = transformedEntities.get(1);

                // kết nối thực thể chính và thực thể đa giá trị
                String outputLine = String.format("[%s] (%s) (%s) -[%s] (%s) (%s): 1 - n",
                    mainEntity.name,
                    mainEntity.formatEntityString(),
                    mainEntity.formatKeysString(),
                    multiValuedEntity.name,
                    multiValuedEntity.formatEntityString(),
                    multiValuedEntity.formatKeysString()
                );
                writer.write(outputLine);
                writer.newLine();
            } else if (relationship.type == RelationshipType.MANY_TO_MANY) { // xử lý quan hệ nhiều - nhiều
                Entity entity1 = transformedEntities.get(0);
                Entity entity2 = transformedEntities.get(1);
                Entity intermediaryEntity = transformedEntities.get(2);

                // kết nối thực thể 1 và thực thể trung gian
                String outputLine1 = String.format("[%s] (%s) (%s) - [%s] (%s) (%s): 1 - n",
                    entity1.name,
                    entity1.formatEntityString(),
                    entity1.formatKeysString(),
                    intermediaryEntity.name,
                    intermediaryEntity.formatEntityString(),
                    intermediaryEntity.formatKeysString()
                );
                writer.write(outputLine1);
                writer.newLine();

                // kết nối thực thể 2 và thực thể trung gian
                String outputLine2 = String.format("[%s] (%s) (%s) - [%s] (%s) (%s): 1 - n",
                    entity2.name,
                    entity2.formatEntityString(),
                    entity2.formatKeysString(),
                    intermediaryEntity.name,
                    intermediaryEntity.formatEntityString(),
                    intermediaryEntity.formatKeysString()
                );
                writer.write(outputLine2);
                writer.newLine();
            } else { // xử lý các trường hợp còn lại: 1 - n, 1 - 1, cha - con
                // kết nối 2 thực thể với nhau
                Entity entity1 = transformedEntities.get(0);
                Entity entity2 = transformedEntities.get(1);

                String outputLine = String.format("[%s] (%s) (%s) - [%s] (%s) (%s): %s",
                    entity1.name,
                    entity1.formatEntityString(),
                    entity1.formatKeysString(),
                    entity2.name,
                    entity2.formatEntityString(),
                    entity2.formatKeysString(),
                    relationship.relationshipDescriptor
                );
                writer.write(outputLine);
                writer.newLine();
            }
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

    
    // phân tích dòng đầu vào và trả về một quan hệ
    private static Relationship parseRelationshipLine(String line) {
        try {
            // Phân tích dòng đầu vào để trích xuất thông tin về các thực thể và quan hệ
            Pattern pattern = Pattern.compile(
                "\\[(?<entity1Name>.+?)\\]\\s*\\((?<entity1Attrs>.+?)\\)\\s*\\((?<entity1Keys>.+?)\\)\\s*-\\s*" +
                "\\[(?<entity2Name>.+?)\\]\\s*\\((?<entity2Attrs>.+?)\\)\\s*\\((?<entity2Keys>.+?)\\)(?::\\s*(?<relationshipType>.+))?"
            ); // sử dụng regex để phân tích dòng đầu vào thành các nhóm tương ứng với thông tin về các thực thể và quan hệ
            Matcher matcher = pattern.matcher(line);

            if (!matcher.matches()) {
                // Xử lý thuộc tính đa giá trị
                if (line.contains(": Multivalued")) {
                    return parseMultivaluedAttribute(line);
                }

                throw new IllegalArgumentException("Invalid input format: " + line);
            }

            // Trích xuất thông tin về các thực thể và quan hệ
            String entity1Name = matcher.group("entity1Name").trim();
            String entity1Attrs = matcher.group("entity1Attrs").trim();
            String entity1Keys = matcher.group("entity1Keys").trim();

            String entity2Name = matcher.group("entity2Name").trim();
            String entity2Attrs = matcher.group("entity2Attrs").trim();
            String entity2Keys = matcher.group("entity2Keys").trim();

            String relationshipType = matcher.group("relationshipType");
            if (relationshipType == null) {
                throw new IllegalArgumentException("Relationship type missing in line: " + line);
            }
            relationshipType = relationshipType.trim();

            // Phân tích thông tin về các thực thể
            Entity entity1 = parseEntity(entity1Name, entity1Attrs, entity1Keys);
            Entity entity2 = parseEntity(entity2Name, entity2Attrs, entity2Keys);

            // Xác định loại quan hệ
            RelationshipType type;
            if (relationshipType.equalsIgnoreCase("1 - n")) {
                type = RelationshipType.ONE_TO_MANY;
            } else if (relationshipType.equalsIgnoreCase("n - n")) {
                type = RelationshipType.MANY_TO_MANY;
            } else if (relationshipType.equalsIgnoreCase("1 - 1")) {
                type = RelationshipType.ONE_TO_ONE;
            } else if (relationshipType.equalsIgnoreCase("Cha - Con")) {
                type = RelationshipType.PARENT_CHILD;
            } else {
                throw new IllegalArgumentException("Không nhận ra mối quan hệ: " + relationshipType);
            }

            // Tạo quan hệ
            Relationship relationship = new Relationship(entity1, entity2, type);
            relationship.relationshipDescriptor = type == RelationshipType.PARENT_CHILD ? "1 - 1" : relationshipType;
            return relationship;

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid input format: " + line, e);
        }
    }

    // phân tích thuộc tính đa giá trị từ chuỗi
    private static Relationship parseMultivaluedAttribute(String line) {
        // Xử lý thuộc tính đa giá trị
        Pattern pattern = Pattern.compile("\\[(.+?)\\]\\s*\\((.+?)\\)\\s*\\((.+?)\\)");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid input format for multivalued attribute: " + line);
        }

        String entityName = matcher.group(1);
        String attributesStr = matcher.group(2);
        String keysStr = matcher.group(3);

        Entity entity = parseEntity(entityName, attributesStr, keysStr);

        // Tạo quan hệ
        Relationship relationship = new Relationship(entity, null, RelationshipType.MULTI_VALUED_ATTRIBUTE);
        relationship.relationshipDescriptor = "1 - n";
        return relationship;
    }

    // phân tích một thực thể từ chuỗi
    private static Entity parseEntity(String entityName, String attributesStr, String keysStr) {
        Entity entity = new Entity(entityName);
        String[] attributes = attributesStr.split(",\\s*");
        for (String attr : attributes) entity.addAttribute(attr.trim());

        // Xác định khóa chính, khóa ngoại và thuộc tính đa giá trị
        String[] keys = keysStr.split(",\\s*");
        for (String key : keys) {
            if (key.contains(": PK")) {
                entity.addPrimaryKey(key.replace(": PK", "").trim());
            } else if (key.contains(": FK")) {
                entity.addForeignKey(key.replace(": FK", "").trim());
            } else if (key.contains(": Multivalued")) {
                entity.addMultiValuedAttribute(key.replace(": Multivalued", "").trim());
            }
        }

        return entity; 
    }

    public static void main(String[] args) {
        try {
            // Process the input file and write the output to another file
            processInputFile("input1.txt", "output1.txt");
            System.out.println("Transformation completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
