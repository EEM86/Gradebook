package ua.gradebook.model.beans;

import java.time.LocalDate;

public class Person {
    Integer id;
    Roles role;
    Container container;
    String firstName;
    String lastName;
    String address;
    LocalDate birthday;
    Integer departmentId;
    Integer curatorId;
    Integer groupId;
    String login;
    String password;

    public Person() {
    }

    public Person(Integer id, Roles role, Container container, String firstName, String lastName,
                  String address, LocalDate birthday, Integer departmentId, Integer curatorId,
                  Integer groupId, String login, String password) {
        this.id = id;
        this.role = role;
        this.container = container;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.departmentId = departmentId;
        this.curatorId = curatorId;
        this.groupId = groupId;
        this.login = login;
        this.password = password;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setCuratorId(Integer curatorId) {
        this.curatorId = curatorId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
