package ua.gradebook.model.beans;

public class Role extends ParentBean {
    private String roleName;

    public Role() {
    }

    public Role(Integer id, String role) {
        this.id = id;
        this.roleName = role;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", role='" + roleName + '\'' +
                '}';
    }
}
