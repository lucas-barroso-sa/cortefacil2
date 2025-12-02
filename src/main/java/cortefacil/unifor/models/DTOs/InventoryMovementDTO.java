package cortefacil.unifor.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import cortefacil.unifor.models.entities.InventoryMovement;
import cortefacil.unifor.models.enuns.MovementType;

import java.time.LocalDateTime;

public class InventoryMovementDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    private int quantity;
    private MovementType type; // ENTRY ou EXIT
    private String itemName;   // Nome do item para exibir na lista

    public InventoryMovementDTO() {
    }

    public InventoryMovementDTO(InventoryMovement entity) {
        this.id = entity.getId();
        this.dateTime = entity.getDateTime();
        this.quantity = entity.getQuantity();
        this.type = entity.getType();

        if (entity.getItem() != null) {
            this.itemName = entity.getItem().getName();
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public MovementType getType() { return type; }
    public void setType(MovementType type) { this.type = type; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
}