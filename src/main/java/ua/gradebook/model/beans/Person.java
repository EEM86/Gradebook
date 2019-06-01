package ua.gradebook.model.beans;

import java.sql.Date;

public class Person extends ParentBean {
    Integer roleId;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    Date birthday;
    Container container;
    Integer departmentId;
    Integer curatorId;
    Integer groupId;
    String login;
    String password;

    public Person() {
    }

    public Person(Integer roleId, Container container, String firstName, String lastName,
                  String address, Date birthday, Integer departmentId, Integer curatorId,
                  Integer groupId, String login, String password) {
        this.roleId = roleId;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(Integer curatorId) {
        this.curatorId = curatorId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
