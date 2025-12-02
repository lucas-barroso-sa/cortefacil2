package cortefacil.unifor.repositories;

import cortefacil.unifor.models.entities.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
}
