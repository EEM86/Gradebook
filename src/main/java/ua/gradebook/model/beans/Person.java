package ua.gradebook.model.beans;

import java.sql.Date;
import java.time.LocalDate;

public class Person extends ParentBean {
    Integer id;
    Integer roleId;
    Container container;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    Date birthday;
    Integer departmentId;
    Integer curatorId;
    Integer groupId;
    String login;
    String password;

    public Person() {
    }

    public Person(Integer person_id, Integer roleId, Container container, String firstName, String lastName,
                  String address, Date birthday, Integer departmentId, Integer curatorId,
                  Integer groupId, String login, String password) {
        this.id = person_id;
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

    public void setPerson_id(Integer person_id) {
        this.id = person_id;
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

    public void setBirthday(Date birthday) {
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
        return id;
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

    public Date getBirthday() {
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
