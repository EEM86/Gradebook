package ua.gradebook.model.beans;

public class Container {
    private Integer id;
    private Integer parent_id;
    private String name;
    private Integer chief_id;
    private String type;
    private String institution_city;
    private String institution_address;
    private String phone;

    public Container() {
    }

    public Container(int id, int parent_id, String name, int chief_id, String institution_type, String institution_city, String institution_address, String phone) {
        this.id = id;
        this.parent_id = parent_id;
        this.name = name;
        this.chief_id = chief_id;
        this.type = institution_type;
        this.institution_city = institution_city;
        this.institution_address = institution_address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChief_id() {
        return chief_id;
    }

    public void setChief_id(int chief_id) {
        this.chief_id = chief_id;
    }

    public String getInstitution_type() {
        return type;
    }

    public void setInstitution_type(String institution_type) {
        this.type = institution_type;
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
