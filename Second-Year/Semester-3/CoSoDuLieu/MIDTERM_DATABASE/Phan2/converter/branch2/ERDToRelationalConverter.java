package branch2;

import java.util.*;

// Enum to represent different types of entities and relationships
enum EntityType {
    STRONG_ENTITY,
    WEAK_ENTITY,
    ONE_TO_ONE,
    ONE_TO_MANY,
    MANY_TO_MANY,
    MULTIVALUED_ATTRIBUTE,
    RELATIONSHIP_WITH_ATTRIBUTE,
    GENERALIZATION
}

// Class to represent an attribute
class Attribute {
    private String name;
    private boolean isPrimaryKey;
    private boolean isForeignKey;
    private boolean isMultivalued;

    public Attribute(String name) {
        this.name = name;
        this.isPrimaryKey = false;
        this.isForeignKey = false;
        this.isMultivalued = false;
    }

    public Attribute(String name, boolean isPrimaryKey) {
        this(name);
        this.isPrimaryKey = isPrimaryKey;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isPrimaryKey() { return isPrimaryKey; }
    public void setPrimaryKey(boolean primaryKey) { isPrimaryKey = primaryKey; }
    public boolean isForeignKey() { return isForeignKey; }
    public void setForeignKey(boolean foreignKey) { isForeignKey = foreignKey; }
    public boolean isMultivalued() { return isMultivalued; }
    public void setMultivalued(boolean multivalued) { isMultivalued = multivalued; }

    @Override
    public String toString() {
        return name + (isPrimaryKey ? " (PK)" : "") + (isForeignKey ? " (FK)" : "");
    }
}

// Class to represent an entity
class Entity {
    private String name;
    private List<Attribute> attributes;
    private EntityType type;
    private List<String> relatedEntities;

    public Entity(String name, EntityType type) {
        this.name = name;
        this.type = type;
        this.attributes = new ArrayList<>();
        this.relatedEntities = new ArrayList<>();
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void addRelatedEntity(String entityName) {
        relatedEntities.add(entityName);
    }

    // Getters
    public String getName() { return name; }
    public List<Attribute> getAttributes() { return attributes; }
    public EntityType getType() { return type; }
    public List<String> getRelatedEntities() { return relatedEntities; }

    // Method to convert entity to relational model representation
    public String toRelationalModel() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(name).append("] (");
        
        // Collect attributes
        List<String> attributeStrings = new ArrayList<>();
        for (Attribute attr : attributes) {
            attributeStrings.add(attr.toString());
        }
        
        sb.append(String.join(", ", attributeStrings));
        
        // Determine primary key and foreign keys
        List<String> pkAttributes = attributes.stream()
                .filter(Attribute::isPrimaryKey)
                .map(Attribute::getName)
                .toList();
        
        sb.append(") (");
        
        if (!pkAttributes.isEmpty()) {
            sb.append("PK: ").append(String.join(", ", pkAttributes));
        }
        
        List<String> fkAttributes = attributes.stream()
                .filter(Attribute::isForeignKey)
                .map(Attribute::getName)
                .toList();
        
        if (!fkAttributes.isEmpty()) {
            if (!pkAttributes.isEmpty()) sb.append(", ");
            sb.append("FK: ").append(String.join(", ", fkAttributes));
        }
        
        sb.append(")");
        
        return sb.toString();
    }
}

// Main converter class
public class ERDToRelationalConverter {
    private List<Entity> entities;

    public ERDToRelationalConverter() {
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // Convert specific entity types
    public List<String> convertToRelationalModel() {
        List<String> relationalModels = new ArrayList<>();
        
        for (Entity entity : entities) {
            switch (entity.getType()) {
                case STRONG_ENTITY:
                    relationalModels.add(convertStrongEntity(entity));
                    break;
                case WEAK_ENTITY:
                    relationalModels.addAll(convertWeakEntity(entity));
                    break;
                case ONE_TO_ONE:
                    relationalModels.addAll(convertOneToOne(entity));
                    break;
                case ONE_TO_MANY:
                    relationalModels.addAll(convertOneToMany(entity));
                    break;
                case MANY_TO_MANY:
                    relationalModels.addAll(convertManyToMany(entity));
                    break;
                case MULTIVALUED_ATTRIBUTE:
                    relationalModels.addAll(convertMultivaluedAttribute(entity));
                    break;
                case RELATIONSHIP_WITH_ATTRIBUTE:
                    relationalModels.addAll(convertRelationshipWithAttribute(entity));
                    break;
                case GENERALIZATION:
                    relationalModels.addAll(convertGeneralization(entity));
                    break;
            }
        }
        
        return relationalModels;
    }

    // Conversion methods for different entity types
    private String convertStrongEntity(Entity entity) {
        return entity.toRelationalModel();
    }

    private List<String> convertWeakEntity(Entity entity) {
        List<String> models = new ArrayList<>();
        
        // First add the parent (strong) entity if exists
        if (!entity.getRelatedEntities().isEmpty()) {
            String parentEntity = entity.getRelatedEntities().get(0);
            models.add(String.format("[%s] (%s: PK)", parentEntity, parentEntity));
        }
        
        // Create weak entity table with compound primary key
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(entity.getName()).append("] (");
        
        List<String> attributes = entity.getAttributes().stream()
                .map(Attribute::getName)
                .toList();
        
        // Include parent entity's primary key as foreign key
        if (!entity.getRelatedEntities().isEmpty()) {
            String parentEntityPK = entity.getRelatedEntities().get(0);
            attributes = new ArrayList<>(attributes);
            attributes.add(parentEntityPK);
        }
        
        sb.append(String.join(", ", attributes));
        
        // Compound primary key
        sb.append(") (PK: ");
        sb.append(String.join(", ", attributes));
        
        // Add foreign key
        if (!entity.getRelatedEntities().isEmpty()) {
            String parentEntityPK = entity.getRelatedEntities().get(0);
            sb.append(", FK: ").append(parentEntityPK);
        }
        
        sb.append(")");
        
        models.add(sb.toString());
        return models;
    }

    private List<String> convertOneToOne(Entity entity) {
        List<String> models = new ArrayList<>();
        
        // Combine tables or add foreign key
        if (!entity.getRelatedEntities().isEmpty()) {
            String relatedEntity = entity.getRelatedEntities().get(0);
            
            // Option 1: Add foreign key to one of the tables
            models.add(String.format("[%s] (%s, %s) (PK: %s, FK: %s)", 
                entity.getName(), 
                entity.getName(), 
                relatedEntity, 
                entity.getName(),
                relatedEntity));
            
            models.add(String.format("[%s] (%s) (PK: %s)", 
                relatedEntity, 
                relatedEntity, 
                relatedEntity));
        }
        
        return models;
    }

    private List<String> convertOneToMany(Entity entity) {
        List<String> models = new ArrayList<>();
        
        // First add the "one" side entity
        if (!entity.getRelatedEntities().isEmpty()) {
            String parentEntity = entity.getRelatedEntities().get(0);
            models.add(String.format("[%s] (%s, ...) (PK: %s)", 
                parentEntity, parentEntity, parentEntity));
            
            // Add foreign key to the "many" side
            models.add(String.format("[%s] (%s, %s) (PK: %s, FK: %s)", 
                entity.getName(), 
                entity.getName(), 
                parentEntity, 
                entity.getName(),
                parentEntity));
        }
        
        return models;
    }

    private List<String> convertManyToMany(Entity entity) {
        List<String> models = new ArrayList<>();
        
        // Add both related entities
        if (entity.getRelatedEntities().size() >= 2) {
            String entity1 = entity.getRelatedEntities().get(0);
            String entity2 = entity.getRelatedEntities().get(1);
            
            // First entities
            models.add(String.format("[%s] (%s) (PK: %s)", entity1, entity1, entity1));
            models.add(String.format("[%s] (%s) (PK: %s)", entity2, entity2, entity2));
            
            // Join table
            models.add(String.format("[%s_%s] (%s, %s) (PK: %s, %s, FK: %s, %s)", 
                entity1, entity2, 
                entity1, entity2, 
                entity1, entity2, 
                entity1, entity2));
        }
        
        return models;
    }

    private List<String> convertMultivaluedAttribute(Entity entity) {
        List<String> models = new ArrayList<>();
        
        // Parent entity
        models.add(String.format("[%s] (%s) (PK: %s)", 
            entity.getName(), 
            String.join(", ", 
                entity.getAttributes().stream()
                    .filter(attr -> !attr.isMultivalued())
                    .map(Attribute::getName)
                    .toList()),
            entity.getName()));
        
        // Multivalued attribute table
        models.add(String.format("[%s_MultiValue] (%s, MultiValue) (PK: %s, MultiValue, FK: %s)", 
            entity.getName(), 
            entity.getName(), 
            entity.getName(), 
            entity.getName()));
        
        return models;
    }

    private List<String> convertRelationshipWithAttribute(Entity entity) {
        List<String> models = new ArrayList<>();
        
        // Related entities
        if (entity.getRelatedEntities().size() >= 2) {
            String entity1 = entity.getRelatedEntities().get(0);
            String entity2 = entity.getRelatedEntities().get(1);
            
            // First entities
            models.add(String.format("[%s] (%s) (PK: %s)", entity1, entity1, entity1));
            models.add(String.format("[%s] (%s) (PK: %s)", entity2, entity2, entity2));
            
            // Relationship table with additional attributes
            models.add(String.format("[%s_%s] (%s, %s, AdditionalAttributes) (PK: %s, %s, FK: %s, %s)", 
                entity1, entity2, 
                entity1, entity2, 
                entity1, entity2, 
                entity1, entity2));
        }
        
        return models;
    }

    private List<String> convertGeneralization(Entity entity) {
        List<String> models = new ArrayList<>();
        
        // Parent entity
        models.add(String.format("[%s] (%s, Type) (PK: %s)", 
            entity.getName(), 
            entity.getName(), 
            entity.getName()));
        
        // Subtype entities
        models.add(String.format("[%s_Subtype1] (%s, SubtypeSpecificAttributes) (FK: %s)", 
            entity.getName(), 
            entity.getName(), 
            entity.getName()));
        
        models.add(String.format("[%s_Subtype2] (%s, SubtypeSpecificAttributes) (FK: %s)", 
            entity.getName(), 
            entity.getName(), 
            entity.getName()));
        
        return models;
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        ERDToRelationalConverter converter = new ERDToRelationalConverter();
        
        // Example: Strong Entity
        Entity student = new Entity("Sinh vien", EntityType.STRONG_ENTITY);
        student.addAttribute(new Attribute("Masv", true));
        student.addAttribute(new Attribute("Hoten"));
        student.addAttribute(new Attribute("Ngaysinh"));
        converter.addEntity(student);
        
        // Example: Weak Entity
        Entity relative = new Entity("Nhan than", EntityType.WEAK_ENTITY);
        relative.addAttribute(new Attribute("MaNT"));
        relative.addAttribute(new Attribute("Hoten"));
        relative.addRelatedEntity("Nhan vien");
        converter.addEntity(relative);
        
        // Convert and print results
        List<String> relationalModels = converter.convertToRelationalModel();
        System.out.println("Relational Model Conversion:");
        for (String model : relationalModels) {
            System.out.println(model);
        }
    }
}