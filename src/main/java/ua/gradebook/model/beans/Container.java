package ua.gradebook.model.beans;

import java.util.Objects;

public class Container extends ParentBean {
    private Container parent;
    private String name;
    private Person chief;
    private BranchType type;
    private String institutionCity;
    private String institutionAddress;
    private String phone;

    public Container() {
    }

    public Container(Container parent, String name, Person chief, BranchType type, String institutionCity, String institutionAddress, String phone) {
        this.parent = parent;
        this.name = name;
        this.chief = chief;
        this.type = type;
        this.institutionCity = institutionCity;
        this.institutionAddress = institutionAddress;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    public Person getChief() {
        return chief;
    }

    public void setChief(Person chief) {
        this.chief = chief;
    }

    public BranchType getType() {
        return type;
    }

    public void setType(BranchType type) {
        this.type = type;
    }

    public String getInstitutionCity() {
        return institutionCity;
    }

    public void setInstitutionCity(String institutionCity) {
        this.institutionCity = institutionCity;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(parent, container.parent) &&
                Objects.equals(name, container.name) &&
                Objects.equals(chief, container.chief) &&
                Objects.equals(type, container.type) &&
                Objects.equals(institutionCity, container.institutionCity) &&
                Objects.equals(institutionAddress, container.institutionAddress) &&
                Objects.equals(phone, container.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, name, chief, type, institutionCity, institutionAddress, phone);
    }

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", parentId=" + parent +
                ", name='" + name + '\'' +
                ", chief=" + chief +
                ", type=" + type +
                ", institutionCity='" + institutionCity + '\'' +
                ", institutionAddress='" + institutionAddress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
