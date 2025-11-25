package cortefacil.unifor.models.entities;

import cortefacil.unifor.models.key.SaleItemId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ItemSale {
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

    // CONSTRUTOR CORRIGIDO: Removido o parâmetro 'subtotal' redundante e adicionada a lógica JPA
    public ItemSale(int quantity, double price, Sale sale, Item item) {

        // 1. ✅ INICIALIZAÇÃO DA CHAVE COMPOSTA (@EmbeddedId)
        // Isso é obrigatório! Garante que o objeto 'id' não é nulo.
        this.id = new SaleItemId(sale.getId(), item.getId());

        // 2. ✅ ATRIBUIÇÃO DAS ENTIDADES
        // O Hibernate usa estas referências para popular o @MapsId
        this.sale = sale;
        this.item = item;

        // 3. Atribuição dos atributos da linha de venda
        this.quantity = quantity;
        this.price = price;
        this.subtotal = (double) quantity * price;
    }

    // ... restante dos Getters e Setters (Mantidos como estavam)

    // ...
}