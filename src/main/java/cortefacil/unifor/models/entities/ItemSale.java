package cortefacil.unifor.models.entities;

import cortefacil.unifor.models.key.SaleItemId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ItemSale {
    private String itemName;
    private int quantity;
    private double price; // Preço unitário no momento da venda
    private double subtotal;

    @EmbeddedId
    private SaleItemId id;

    // Relacionamentos:
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("saleId")
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;


    public ItemSale(){
    }

    public ItemSale(int quantity, Sale sale, Item item) {

        // INICIALIZAÇÃO DA CHAVE COMPOSTA (@EmbeddedId)
        this.id = new SaleItemId(sale.getId(), item.getId());


        // O Hibernate usa estas referências para popular o @MapsId
        this.sale = sale;
        this.item = item;

        this.itemName = item.getName();
        this.quantity = quantity;
        this.price = item.getPrice();
        this.subtotal = quantity * item.getPrice();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public SaleItemId getId() {
        return id;
    }

    public void setId(SaleItemId id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}