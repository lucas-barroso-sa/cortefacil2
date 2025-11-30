package cortefacil.unifor.models.DTOs;

import cortefacil.unifor.models.entities.ItemSale;
import cortefacil.unifor.models.key.SaleItemId;

public class ItemSaleDTO {
    private Long itemId;
    private String itemName;
    private int quantity;
    private double subtotal;

    public ItemSaleDTO(ItemSale entity){
        this.itemId = entity.getItem().getId();
        this.itemName = entity.getItem().getName();
        this.quantity = entity.getQuantity();
        this.subtotal = entity.getSubtotal();

    }
    public ItemSaleDTO() {}

    public String getItemName() {
        return itemName;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }


    public void setId(Long itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Long getId() {
        return itemId;
    }

}
