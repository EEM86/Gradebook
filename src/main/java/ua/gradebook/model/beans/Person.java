package ua.gradebook.model.beans;

import java.time.LocalDate;

public class Person {
    Integer person_id;
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

    public Person(Integer person_id, Roles role, Container container, String firstName, String lastName,
                  String address, LocalDate birthday, Integer departmentId, Integer curatorId,
                  Integer groupId, String login, String password) {
        this.person_id = person_id;
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

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
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

    public Integer getPerson_id() {
        return person_id;
    }

    public Roles getRole() {
        return role;
    }

    public Container getContainer() {
        return container;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public Integer getCuratorId() {
        return curatorId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
