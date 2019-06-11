package ua.gradebook.model.beans;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
