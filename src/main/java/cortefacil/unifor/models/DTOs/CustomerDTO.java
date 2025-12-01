package cortefacil.unifor.models.DTOs;

import cortefacil.unifor.models.entities.Customer;

public class CustomerDTO {
    private Long id;
    private String name;
    private String phone;

    public CustomerDTO(){
    }
    public CustomerDTO(Customer entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
