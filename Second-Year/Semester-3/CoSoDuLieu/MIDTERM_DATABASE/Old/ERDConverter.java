import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ERDConverter {

    public static void main(String[] args) {
        String inputFile = "Input1.txt";
        String outputFile = "Output1.txt";

        try {
            // Parse the input file and build entities and relationships
            Map<String, Entity> entities = parseInputFile(inputFile);

            // Convert ERD to relational model
            convertERDToRelationalModel(entities);

            // Write the output to the file
            writeOutputFile(outputFile, entities);

            System.out.println("Conversion completed. Check the Output1.txt file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Parse the input file and build entities and relationships
    public static Map<String, Entity> parseInputFile(String inputFile) throws IOException {
        Map<String, Entity> entities = new LinkedHashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            // Skip empty lines
            if (line.trim().isEmpty())
                continue;

            // Parse the line
            parseLine(line, entities);
        }
        reader.close();
        return entities;
    }

    // Parse each line of the input file
    public static void parseLine(String line, Map<String, Entity> entities) {
        // Regular expressions to extract entities and relationships
        Pattern entityPattern = Pattern.compile("\\[(.+?)\\]\\s*\\((.*?)\\)\\s*(?:\\((.+?)\\))?");
        Pattern relationshipPattern = Pattern
                .compile("\\s*–\\s*\\[(.+?)\\]\\s*\\((.*?)\\)\\s*(?:\\((.+?)\\))?\\s*:?\\s*(.+)?");

        Matcher matcher = entityPattern.matcher(line);

        if (matcher.find()) {
            // Parse first entity
            String entityName1 = matcher.group(1).trim();
            String attributes1 = matcher.group(2).trim();
            String keys1 = matcher.group(3);

            Entity entity1 = entities.getOrDefault(entityName1, new Entity(entityName1));
            entity1.addAttributes(parseAttributes(attributes1));
            if (keys1 != null) {
                entity1.setPrimaryKey(parsePrimaryKey(keys1));
            }
            entities.put(entityName1, entity1);

            // Check for relationship
            int relationshipIndex = matcher.end();
            String remainingLine = line.substring(relationshipIndex);

            Matcher relMatcher = relationshipPattern.matcher(remainingLine);
            if (relMatcher.find()) {
                // Parse second entity
                String entityName2 = relMatcher.group(1).trim();
                String attributes2 = relMatcher.group(2).trim();
                String keys2 = relMatcher.group(3);
                String relationshipType = relMatcher.group(4);

                Entity entity2 = entities.getOrDefault(entityName2, new Entity(entityName2));
                entity2.addAttributes(parseAttributes(attributes2));
                if (keys2 != null) {
                    entity2.setPrimaryKey(parsePrimaryKey(keys2));
                }
                entities.put(entityName2, entity2);

                // Parse relationship
                if (relationshipType != null) {
                    relationshipType = relationshipType.trim();
                    // Handle Parent-Child relationship
                    if (relationshipType.equalsIgnoreCase("Cha – con")
                            || relationshipType.equalsIgnoreCase("Cha - con")) {
                        entity2.setParentEntity(entity1);
                        entity2.setInheritance(true);
                    } else {
                        // Create relationships
                        entity1.addRelationship(new Relationship(relationshipType, entity2));
                    }
                }
            }
        }
    }

    // Parse attributes from a string
    public static List<String> parseAttributes(String attributesStr) {
        List<String> attributes = new ArrayList<>();
        String[] attrs = attributesStr.split(",");
        for (String attr : attrs) {
            attributes.add(attr.trim());
        }
        return attributes;
    }

    // Parse primary key from a string
    public static String parsePrimaryKey(String keysStr) {
        if (keysStr.contains(":")) {
            String[] parts = keysStr.split(":");
            return parts[0].trim();
        }
        return keysStr.trim();
    }

    // Convert ERD to relational model
    public static void convertERDToRelationalModel(Map<String, Entity> entities) {
        for (Entity entity : entities.values()) {
            // Handle inheritance
            if (entity.isInheritance() && entity.getParentEntity() != null) {
                // In child table, primary key is also foreign key to parent table
                entity.addAttribute(entity.getParentEntity().getPrimaryKey());
                entity.setPrimaryKey(entity.getParentEntity().getPrimaryKey());
                entity.addForeignKey(entity.getParentEntity().getPrimaryKey());
            }

            // Handle relationships
            for (Relationship rel : entity.getRelationships()) {
                String type = rel.getType();
                Entity targetEntity = rel.getTargetEntity();

                if (type.equalsIgnoreCase("n – 1") || type.equalsIgnoreCase("n - 1") || type.equalsIgnoreCase("1 – n")
                        || type.equalsIgnoreCase("1 - n")) {
                    // Add foreign key in the 'many' side
                    if (type.startsWith("n")) {
                        // 'entity' is the 'many' side
                        entity.addAttribute(targetEntity.getPrimaryKey());
                        entity.addForeignKey(targetEntity.getPrimaryKey());
                    } else {
                        // 'entity' is the 'one' side
                        targetEntity.addAttribute(entity.getPrimaryKey());
                        targetEntity.addForeignKey(entity.getPrimaryKey());
                    }
                } else if (type.equalsIgnoreCase("n – n") || type.equalsIgnoreCase("n - n")) {
                    // For n-n relationships, create a new junction table
                    String junctionTableName = entity.getName() + "_" + targetEntity.getName();
                    Entity junctionTable = new Entity(junctionTableName);
                    junctionTable.addAttribute(entity.getPrimaryKey());
                    junctionTable.addAttribute(targetEntity.getPrimaryKey());
                    junctionTable.addForeignKey(entity.getPrimaryKey());
                    junctionTable.addForeignKey(targetEntity.getPrimaryKey());
                    junctionTable.setIsJunctionTable(true);

                    // Add to entities map
                    entities.put(junctionTableName, junctionTable);
                } else if (type.equalsIgnoreCase("1 – 1") || type.equalsIgnoreCase("1 - 1")) {
                    // For 1-1 relationships, add foreign key to one of the tables
                    // Here, we add foreign key to the second entity
                    targetEntity.addAttribute(entity.getPrimaryKey());
                    targetEntity.addForeignKey(entity.getPrimaryKey());
                }
                // Other relationship types can be handled similarly
            }
        }
    }

    // Write the output file
    public static void writeOutputFile(String outputFile, Map<String, Entity> entities) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
    
        for (Entity entity : entities.values()) {
            // Skip writing junction tables here (optional)
            if (entity.isJunctionTable()) {
                // You can write the junction tables if needed
                continue;
            }
    
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(entity.getName()).append("] (");
            // Append attributes
            for (String attr : entity.getAttributes()) {
                sb.append(attr).append(", ");
            }
            // Remove trailing comma and space
            if (entity.getAttributes().size() > 0) {
                sb.setLength(sb.length() - 2);
            }
            sb.append(")");
    
            // Append keys
            if (entity.getPrimaryKey() != null) {
                sb.append(" (").append(entity.getPrimaryKey()).append(": PK");
                // Append foreign keys
                if (entity.getForeignKeys().contains(entity.getPrimaryKey())) {
                    sb.append(", FK");
                }
                sb.append(")");
            }
            for (String fk : entity.getForeignKeys()) {
                if (fk != null && !fk.equals(entity.getPrimaryKey())) {
                    sb.append(" (").append(fk).append(": FK)");
                }
            }
    
            // Append relationships
            for (Relationship rel : entity.getRelationships()) {
                Entity targetEntity = rel.getTargetEntity();
                String type = rel.getType();
    
                sb.append(" – [").append(targetEntity.getName()).append("]");
                sb.append(" (");
                for (String attr : targetEntity.getAttributes()) {
                    sb.append(attr).append(", ");
                }
                if (targetEntity.getAttributes().size() > 0) {
                    sb.setLength(sb.length() - 2);
                }
                sb.append(")");
                // Append keys
                if (targetEntity.getPrimaryKey() != null) {
                    sb.append(" (").append(targetEntity.getPrimaryKey()).append(": PK)");
                }
                sb.append(": ").append(type);
            }
    
            // Write to file
            writer.write(sb.toString());
            writer.newLine();
        }
        writer.close();
    }
}

// Entity class
class Entity {
    private String name;
    private Set<String> attributes;
    private String primaryKey;
    private List<Relationship> relationships;
    private Set<String> foreignKeys;
    private Entity parentEntity;
    private boolean isInheritance;
    private boolean isJunctionTable;

    public Entity(String name) {
        this.name = name;
        this.attributes = new LinkedHashSet<>();
        this.relationships = new ArrayList<>();
        this.foreignKeys = new HashSet<>();
        this.isInheritance = false;
        this.isJunctionTable = false;
    }

    public void addAttributes(List<String> attrs) {
        this.attributes.addAll(attrs);
    }

    public void addAttribute(String attr) {
        this.attributes.add(attr);
    }

    public Set<String> getAttributes() {
        return attributes;
    }

    public void setPrimaryKey(String pk) {
        this.primaryKey = pk;
        this.attributes.add(pk);
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void addRelationship(Relationship rel) {
        this.relationships.add(rel);
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void addForeignKey(String fk) {
        this.foreignKeys.add(fk);
    }

    public Set<String> getForeignKeys() {
        return foreignKeys;
    }

    public String getName() {
        return name;
    }

    public Entity getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(Entity parentEntity) {
        this.parentEntity = parentEntity;
    }

    public boolean isInheritance() {
        return isInheritance;
    }

    public void setInheritance(boolean inheritance) {
        isInheritance = inheritance;
    }

    public boolean isJunctionTable() {
        return isJunctionTable;
    }

    public void setIsJunctionTable(boolean junctionTable) {
        isJunctionTable = junctionTable;
    }
}

// Relationship class
class Relationship {
    private String type; // e.g., "1-1", "1-n", "n-n", "Parent-Child"
    private Entity targetEntity;

    public Relationship(String type, Entity targetEntity) {
        this.type = type;
        this.targetEntity = targetEntity;
    }

    public String getType() {
        return type;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }
}
