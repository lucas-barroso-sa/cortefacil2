package cortefacil.unifor.models.key;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class SaleItemId {
    private Long SaleID;
    private Long ItemID;


    public SaleItemId() {
    }
    public SaleItemId(Long SaleID, Long ItemID) {
        this.SaleID = SaleID;
        this.ItemID = ItemID;
    }

    public Long getSaleID() {
        return SaleID;
    }

    public void setSaleID(Long saleID) {
        SaleID = saleID;
    }

    public Long getItemID() {
        return ItemID;
    }

    public void setItemID(Long itemID) {
        ItemID = itemID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaleItemId that = (SaleItemId) o;
        return Objects.equals(SaleID, that.SaleID) && Objects.equals(ItemID, that.ItemID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SaleID, ItemID);
    }
}
