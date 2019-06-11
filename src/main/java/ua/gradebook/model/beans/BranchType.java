package ua.gradebook.model.beans;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchType that = (BranchType) o;
        return Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }

    @Override
    public String toString() {
        return "BranchType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
