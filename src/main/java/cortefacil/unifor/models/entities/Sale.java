package cortefacil.unifor.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private double totalValue;

    @OneToMany(cascade=CascadeType.ALL)
    private List<ItemSale> itemSaleList;

    public Sale() {
    }
    public Sale(LocalDate date, double totalValue) {
        this.date = date;
        this.totalValue = totalValue;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
