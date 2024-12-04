package branch1;

import java.util.*;
import java.io.*;

// Lớp biểu diễn Thuộc tính
class Attribute {
    String name;
    boolean isPrimaryKey;
    boolean isForeignKey;
    boolean isMultivalued;

    public Attribute(String name, boolean isPrimaryKey, boolean isForeignKey, boolean isMultivalued) {
        this.name = name;
        this.isPrimaryKey = isPrimaryKey;
        this.isForeignKey = isForeignKey;
        this.isMultivalued = isMultivalued;
    }
}

// Lớp biểu diễn Thực thể
class Entity {
    String name;
    List<Attribute> attributes;

    public Entity(String name) {
        this.name = name;
        this.attributes = new ArrayList<>();
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }
}

// Lớp biểu diễn Quan hệ
class Relationship {
    String name;
    Entity entity1;
    Entity entity2;
    String cardinality; // VD: "1-1", "1-N", "N-N"

    public Relationship(String name, Entity entity1, Entity entity2, String cardinality) {
        this.name = name;
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.cardinality = cardinality;
    }
}







public class ERDToRelationalConverter {

    // Chuyển đổi thực thể mạnh
    public void convertStrongEntity(Entity entity) {
        System.out.println("Table: " + entity.name);
        for (Attribute attribute : entity.attributes) {
            System.out.println("- " + attribute.name +
                (attribute.isPrimaryKey ? " (PK)" : "") +
                (attribute.isForeignKey ? " (FK)" : ""));
        }
    }

    // Chuyển đổi thực thể yếu
    public void convertWeakEntity(Entity weakEntity, Entity strongEntity) {
        System.out.println("Table: " + weakEntity.name);
        for (Attribute attribute : weakEntity.attributes) {
            System.out.println("- " + attribute.name +
                (attribute.isPrimaryKey ? " (PK)" : "") +
                (attribute.isForeignKey ? " (FK)" : ""));
        }
        System.out.println("- " + strongEntity.name + "_id (FK)");
    }

    // Chuyển đổi quan hệ
    public void convertRelationship(Relationship relationship) {
        switch (relationship.cardinality) {
            case "1-1":
                System.out.println("1-1 Relationship: Add FK to one of the tables.");
                break;
            case "1-N":
                System.out.println("1-N Relationship: Add FK to the 'N' side.");
                break;
            case "N-N":
                System.out.println("N-N Relationship: Create a new join table.");
                break;
            default:
                System.out.println("Unsupported cardinality: " + relationship.cardinality);
        }
    }
}
