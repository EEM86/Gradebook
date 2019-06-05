package ua.gradebook.model.beans;

public class GradesJournal extends ParentBean {
    private Integer discId;
    private Integer personId;
    private Integer grade;
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
}
