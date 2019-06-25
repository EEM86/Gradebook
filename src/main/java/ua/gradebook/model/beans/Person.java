package ua.gradebook.model.beans;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//import javax.validation.constraints.NotNull;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

public class Person extends ParentBean {
    private Role role;
//    @Size(min=2, max=20, message = "{name.size.error}")
    @NotBlank(message = "Please, enter first name!")
//    @NotNull(message = "Please, enter first name!")
    private String firstName;

//    @Size(min=2, max=20)
//    @NotNull(message = "Please, enter last name!")
    @NotEmpty(message = "Please, enter last name!")
    private String lastName;
//    @NotNull
    @Size(min=5, max=30, message = "{email.size.error}")
    private String email;
//    @NotNull
//    @Size(min=2, max=15)
    private String phone;
//    @NotNull
//    @Size(min=2, max=30)
    private String address;
//    @NotNull
    private Date birthday;
    private Container department;
    private Person curator;
    private Container group;
/*    @NotNull
    @Max(20)*/
    private String login;
/*    @NotNull
    @Max(20)*/
    private String password;

    public Person() {
    }

    public Person(Role role, Container department, String firstName, String lastName,
                  String address, Date birthday, Person curator,
                  Container group, String login, String password) {
        this.role = role;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.curator = curator;
        this.group = group;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Container getDepartment() {
        return department;
    }

    public void setDepartment(Container department) {
        this.department = department;
    }

    public Person getCurator() {
        return curator;
    }

    public void setCurator(Person curator) {
        this.curator = curator;
    }

    public Container getGroup() {
        return group;
    }

    public void setGroup(Container group) {
        this.group = group;
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
        return Objects.equals(role, person.role) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phone, person.phone) &&
                Objects.equals(address, person.address) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(department, person.department) &&
                Objects.equals(curator, person.curator) &&
                Objects.equals(group, person.group) &&
                Objects.equals(login, person.login) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, firstName, lastName, email, phone, address, birthday, department, curator, group, login, password);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", department=" + department +
                ", curator=" + curator +
                ", group=" + group +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
