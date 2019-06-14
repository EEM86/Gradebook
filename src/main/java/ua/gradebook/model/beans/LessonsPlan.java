package ua.gradebook.model.beans;

import java.util.Objects;

public class LessonsPlan extends ParentBean {
    private Integer discId;
    private String discName;
    private Integer teacherId;
    private String teacherName;
    private Integer groupId;
    private String groupName;
    private Integer hours;

    public Integer getDiscId() {
        return discId;
    }

    public void setDiscId(Integer discId) {
        this.discId = discId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDiscName() {
        return discName;
    }

    public void setDiscName(String discName) {
        this.discName = discName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LessonsPlan that = (LessonsPlan) o;
        return Objects.equals(discId, that.discId) &&
                Objects.equals(discName, that.discName) &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(hours, that.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discId, discName, teacherId, teacherName, groupId, groupName, hours);
    }

    @Override
    public String toString() {
        return "LessonsPlan{" +
                "id=" + id +
                ", discId=" + discId +
                ", discName='" + discName + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", groupId=" + groupId +
                ", groupName=" + groupName +
                ", hours=" + hours +
                '}';
    }
}
