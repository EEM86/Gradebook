package ua.gradebook.model.beans;

import java.util.Objects;

public class LessonsPlan extends ParentBean {
    private Discipline discipline;
    private Person teacher;
    private Container group;
    private Integer hours;

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Container getGroup() {
        return group;
    }

    public void setGroup(Container group) {
        this.group = group;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LessonsPlan that = (LessonsPlan) o;
        return Objects.equals(discipline, that.discipline) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(group, that.group) &&
                Objects.equals(hours, that.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discipline, teacher, group, hours);
    }

    @Override
    public String toString() {
        return "LessonsPlan{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", teacher=" + teacher +
                ", group=" + group +
                ", hours=" + hours +
                '}';
    }
}
