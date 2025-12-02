package cortefacil.unifor.factories;

import cortefacil.unifor.models.entities.InventoryMovement;
import cortefacil.unifor.models.entities.Item;
import cortefacil.unifor.models.enuns.MovementType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InventoryMovementFactory {

    /**
     * Método Factory Method: Encapsula a lógica de criação.
     * Garante que ninguém crie uma movimentação sem data ou com quantidade negativa.
     */
    public InventoryMovement createMovement(Item item, int quantity, MovementType type) {

        // 1. Regras de Validação (Defense Programming)
        if (item == null) {
            throw new IllegalArgumentException("O item da movimentação não pode ser nulo.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        // 2. Instanciação
        InventoryMovement movement = new InventoryMovement();

        // 3. Configuração
        movement.setItem(item);
        movement.setQuantity(quantity);
        movement.setType(type);

        // Regra de Negócio: A data é sempre "agora"
        movement.setDateTime(LocalDateTime.now());

        return movement;
    }
}