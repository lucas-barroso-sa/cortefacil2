package cortefacil.unifor.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    private double totalValue;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemSale> itemSaleList;

    public Sale() {
        this.date = LocalDateTime.now();
        this.itemSaleList = new ArrayList<>();
    }
    public Sale(LocalDateTime date) {
        this.date = date;
    }
    public void calculateTotal() {
        this.totalValue = itemSaleList.stream().mapToDouble(ItemSale::getSubtotal).sum();

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public List<ItemSale> getItemSaleList() {
        return itemSaleList;
    }

    public void setItemSaleList(List<ItemSale> itemSaleList) {
        this.itemSaleList = itemSaleList;
    }
}
