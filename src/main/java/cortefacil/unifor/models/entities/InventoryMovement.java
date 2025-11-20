package cortefacil.unifor.models.entities;

import cortefacil.unifor.models.enuns.MovementType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private MovementType type;
    private int quantity;
    private LocalDate dateTime;

    private
}
