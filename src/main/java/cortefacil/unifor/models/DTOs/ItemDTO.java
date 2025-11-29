package cortefacil.unifor.models.DTOs;

import cortefacil.unifor.models.entities.Item;
import cortefacil.unifor.models.enuns.ItemType;

public class ItemDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int minimalQuantity;
    private ItemType type;

    public ItemDTO(Item entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
        this.minimalQuantity = entity.getMinimalQuantity();
        this.type = entity.getType();

    }
    public ItemDTO() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(int minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
