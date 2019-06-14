package ua.gradebook.model.beans;

import java.util.Objects;

public class GradesJournal extends ParentBean {
    private String discName;
    private Integer discId;
    private String studentName;
    private Integer personId;
    private Integer grade;
    private String teacherName;
    private Integer teacherId;

    public Integer getDiscId() {
        return discId;
    }

    public void setDiscId(Integer discipline) {
        this.discId = discipline;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getDiscName() {
        return discName;
    }

    public void setDiscName(String discName) {
        this.discName = discName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradesJournal that = (GradesJournal) o;
        return Objects.equals(discId, that.discId) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discId, personId, grade, teacherId);
    }

    @Override
    public String toString() {
        return "GradesJournal{" +
                "id=" + id +
                ", discName='" + discName + '\'' +
                ", discId=" + discId +
                ", studentName='" + studentName + '\'' +
                ", personId=" + personId +
                ", grade=" + grade +
                ", teacherName='" + teacherName + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}
