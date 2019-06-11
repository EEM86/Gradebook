package ua.gradebook.model.beans;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

public class Person extends ParentBean {
    private Integer roleId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Date birthday;
    private Container container;
    private Integer departmentId;
    private Integer curatorId;
    private Integer groupId;
    private String login;
    private String password;

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

    public static boolean isAdmin() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (SimpleGrantedAuthority role : authorities ) {
            if ("ROLE_ADMIN".equalsIgnoreCase(role.getAuthority())) {
                return true;
            }
        }
        return false;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(roleId, person.roleId) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phone, person.phone) &&
                Objects.equals(address, person.address) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(container, person.container) &&
                Objects.equals(departmentId, person.departmentId) &&
                Objects.equals(curatorId, person.curatorId) &&
                Objects.equals(groupId, person.groupId) &&
                Objects.equals(login, person.login) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleId, firstName, lastName, email, phone, address, birthday, container, departmentId, curatorId, groupId, login, password);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", container=" + container +
                ", departmentId=" + departmentId +
                ", curatorId=" + curatorId +
                ", groupId=" + groupId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
