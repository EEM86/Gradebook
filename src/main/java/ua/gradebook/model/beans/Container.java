package ua.gradebook.model.beans;

public class Container extends ParentBean {
    private String name;
    private Integer parent_id;
    private Integer chief_id;
    private Integer type;
    private String institution_city;
    private String institution_address;
    private String phone;

    public Container() {
    }

    public Container(String name, Integer parent_id, Integer chief_id, Integer type, String institution_city, String institution_address, String phone) {
        this.name = name;
        this.parent_id = parent_id;
        this.chief_id = chief_id;
        this.type = type;
        this.institution_city = institution_city;
        this.institution_address = institution_address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getChief_id() {
        return chief_id;
    }

    public void setChief_id(Integer chief_id) {
        this.chief_id = chief_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInstitution_city() {
        return institution_city;
    }

    public void setInstitution_city(String institution_city) {
        this.institution_city = institution_city;
    }

    public String getInstitution_address() {
        return institution_address;
    }

    public void setInstitution_address(String institution_address) {
        this.institution_address = institution_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
