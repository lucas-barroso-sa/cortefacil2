package cortefacil.unifor.models.DTOs;

import cortefacil.unifor.models.entities.Services;

public class ServicesDTO {

    private Long id;
    private String name;
    private double price;

    public ServicesDTO() {}
    public ServicesDTO(Services entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
