package cortefacil.unifor.models.DTOs;

public class StockUpdateDTO {
    private int quantity;

    public StockUpdateDTO() {}

    public StockUpdateDTO(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}