package cortefacil.unifor.models.entities;

import cortefacil.unifor.models.key.SaleItemId;
import jakarta.persistence.*;

@Entity
public class ItemSale {
    private int quantity;
    private double price;
    private double subtotal;

    @EmbeddedId
    private SaleItemId id = new SaleItemId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("saleId") // Vincula ao atributo 'saleId' da chave composta
    @JoinColumn(name = "sale_id")
    private BarSale sale; // Sua classe VendaBar

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId") // Vincula ao atributo 'itemId' da chave composta
    @JoinColumn(name = "item_id")
    private Item item;

}
