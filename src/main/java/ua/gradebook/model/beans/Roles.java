package ua.gradebook.model.beans;

public class Roles {
    private int id;
    private String role;

    public Roles() {
    }

    public Roles(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
