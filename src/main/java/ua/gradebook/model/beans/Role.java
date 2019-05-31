package ua.gradebook.model.beans;

public class Role extends ParentBean {
    private String role;

    public Role() {
    }

    public Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", role='" + role + '\'' +
                '}';
    }
}
