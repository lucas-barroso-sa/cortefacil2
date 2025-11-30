package cortefacil.unifor.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import cortefacil.unifor.models.entities.Sale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDTO {
    private Long saleId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    private double totalValue;
    private List<ItemSaleDTO> itemSaleDTOList = new ArrayList<>();

    public SaleDTO(Sale entity) {
        this.saleId = entity.getId();
        this.date = entity.getDate();
        this.totalValue = entity.getTotalValue();
        if (entity.getItemSaleList() != null) {
            this.itemSaleDTOList = entity.getItemSaleList().stream().map(obj -> new ItemSaleDTO(obj)).toList();
        }
    }
    public SaleDTO() {}

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
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

    public List<ItemSaleDTO> getItemSaleDTOList() {
        return itemSaleDTOList;
    }

    public void setItemSaleDTOList(List<ItemSaleDTO> itemSaleDTOList) {
        this.itemSaleDTOList = itemSaleDTOList;
    }
}
