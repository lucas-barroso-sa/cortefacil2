package cortefacil.unifor.models.key;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SaleItemId implements Serializable {

    // Nomes corrigidos para camelCase: 'saleId' e 'itemId'
    private Long saleId;
    private Long itemId;


    public SaleItemId() {
    }

    // Nomes dos parâmetros corrigidos
    public SaleItemId(Long saleId, Long itemId) {
        this.saleId = saleId;
        this.itemId = itemId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        // Correção de Bug: Atribuição correta
        this.saleId = saleId;
    }

    public Long getItemId() {
        // Getter com nome corrigido para 'getItemId'
        return itemId;
    }

    public void setItemId(Long itemId) {
        // Correção de Bug: Atribuição correta
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaleItemId that = (SaleItemId) o;
        // Uso dos campos corrigidos
        return Objects.equals(saleId, that.saleId) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, itemId);
    }
}