package ua.gradebook.model.beans;

public class LessonsPlan extends ParentBean {
    private Integer disc_id;
    private Integer teacher_id;
    private Integer group_id;
    private Integer hours;

    public Integer getDisc_id() {
        return disc_id;
    }

    public void setDisc_id(Integer disc_id) {
        this.disc_id = disc_id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
