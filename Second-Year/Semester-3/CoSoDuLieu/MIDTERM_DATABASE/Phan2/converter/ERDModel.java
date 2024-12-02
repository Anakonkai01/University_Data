public class ERDModel {
    private String parentEntity;
    private String childEntity;
    private String relationshipType;

    public ERDModel(String parentEntity, String childEntity, String relationshipType) {
        this.parentEntity = parentEntity;
        this.childEntity = childEntity;
        this.relationshipType = relationshipType;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void addForeignKeyToChild() {
        childEntity += " (FK)";
    }

    public void createJoinTable() {
        System.out.println("Join table logic to be implemented.");
    }

    @Override
    public String toString() {
        return "[" + parentEntity + "] – [" + childEntity + "]: " + relationshipType;
    }
}
