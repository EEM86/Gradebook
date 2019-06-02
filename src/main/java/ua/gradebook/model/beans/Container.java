package ua.gradebook.model.beans;

public class Container extends ParentBean {
    private Integer parentId;
    private String name;
    private Integer chiefId;
    private Integer type;
    private String institutionCity;
    private String institutionAddress;
    private String phone;

    public Container() {
    }

    public Container(String name, Integer parentId, Integer chief_id, Integer type, String institution_city, String institution_address, String phone) {
        this.name = name;
        this.parentId = parentId;
        this.chiefId = chief_id;
        this.type = type;
        this.institutionCity = institution_city;
        this.institutionAddress = institution_address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getChiefId() {
        return chiefId;
    }

    public void setChiefId(Integer chiefId) {
        this.chiefId = chiefId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
}
