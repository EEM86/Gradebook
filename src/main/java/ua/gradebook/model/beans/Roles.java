package ua.gradebook.model.beans;

public class Roles {
    private String role;

    public Roles() {
    }

    public Roles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                ", role='" + role + '\'' +
                '}';
    }
}
