package ua.gradebook.model.beans;

public class BranchType extends ParentBean {
    private String typeName;

    public BranchType() {
    }

    public BranchType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
