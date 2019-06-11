package ua.gradebook.model.beans;

import java.util.Objects;

public class LessonsPlan extends ParentBean {
    private Integer discId;
    private Integer teacherId;
    private Integer groupId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonsPlan that = (LessonsPlan) o;
        return Objects.equals(discId, that.discId) &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(hours, that.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discId, teacherId, groupId, hours);
    }

    @Override
    public String toString() {
        return "LessonsPlan{" +
                "id=" + id +
                ", discId=" + discId +
                ", teacherId=" + teacherId +
                ", groupId=" + groupId +
                ", hours=" + hours +
                '}';
    }
}
