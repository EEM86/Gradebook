package ua.gradebook.model.beans;

import java.util.Objects;

public class Discipline  extends ParentBean {
    private String discName;

    public String getDiscName() {
        return discName;
    }

    public void setDiscName(String name) {
        this.discName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(discName, that.discName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discName);
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", discName='" + discName + '\'' +
                '}';
    }
}
