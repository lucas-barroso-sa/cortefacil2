package cortefacil.unifor.models.entities;

import cortefacil.unifor.models.enuns.MovementType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private MovementType type;
    private int quantity;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public InventoryMovement(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InventoryMovement that = (InventoryMovement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
