package ua.gradebook.model.beans;

import java.util.Objects;

public class GradesJournal extends ParentBean {
    private Discipline discipline;
    private Person student;
    private Integer grade;
    private Person teacher;

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GradesJournal that = (GradesJournal) o;
        return Objects.equals(discipline, that.discipline) &&
                Objects.equals(student, that.student) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discipline, student, grade, teacher);
    }

    @Override
    public String toString() {
        return "GradesJournal{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", person=" + student +
                ", grade=" + grade +
                ", teacher=" + teacher +
                '}';
    }
}
