package cortefacil.unifor.services;

import cortefacil.unifor.factories.InventoryMovementFactory;
import cortefacil.unifor.models.DTOs.InventoryMovementDTO;
import cortefacil.unifor.models.DTOs.ItemDTO;
import cortefacil.unifor.models.entities.InventoryMovement;
import cortefacil.unifor.models.entities.Item;
import cortefacil.unifor.models.enuns.MovementType;
import cortefacil.unifor.models.exceptions.DataBaseException;
import cortefacil.unifor.models.exceptions.ResourceNotFoundException;
import cortefacil.unifor.repositories.InventoryMovementRepository;
import cortefacil.unifor.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private InventoryMovementFactory movementFactory;
    private InventoryMovementRepository inventoryMovementRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item findEntityById(Long id) {
        return itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item not found"));
    }

    @Transactional
    public List<ItemDTO> findAll() {
        // ALTERAÇÃO: Busca apenas os ativos
        List<Item> items = itemRepository.findByActiveTrue();
        return items.stream().map(ItemDTO::new).toList();
    }


    public ItemDTO findById(Long id) {
        Item result = itemRepository.findById(id).orElse(null);
        if(result == null) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        return new ItemDTO(result);
    }

    public Item convertDTOToEntity(ItemDTO dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        item.setMinimalQuantity(dto.getMinimalQuantity());
        item.setType(dto.getType());
        return item;

    }

    @Transactional
    public ItemDTO insert(ItemDTO dto) {

        Item item = convertDTOToEntity(dto);
        itemRepository.save(item);
        return new ItemDTO(item);
    }

    @Transactional
    public ItemDTO updateByID(ItemDTO dto, Long id) {
        try{
            Item item = itemRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Item not found with id: " + dto.getId()));
            updateEntityFromDTO(item, dto);
            Item entity = itemRepository.save(item);
            return new ItemDTO(entity);
        }catch(Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public void updateEntityFromDTO(Item entity,ItemDTO dto) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getPrice()!= 0) {
            entity.setPrice(dto.getPrice());
        }
        if (dto.getMinimalQuantity()!= 0) {
            entity.setMinimalQuantity(dto.getMinimalQuantity());
        }
        if(dto.getType()!=null) {
            entity.setType(dto.getType());
        }
    }

    public void updateStockQuantity(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {

            throw new IllegalArgumentException("O ID do recurso é obrigatório para a exclusão.");
        }
        try {
            Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));


            item.setActive(false);


            itemRepository.save(item);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Property not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation: Property ID " + id + " is referenced by other data (e.g., Reservations).");
        }
    }
    @Transactional
    public void addStock(Long itemId, int quantity) {
        Item item = findEntityById(itemId); // Seu método auxiliar que busca a entidade

        InventoryMovement movement = movementFactory.createMovement(item, quantity, MovementType.ENTRADA);
        inventoryMovementRepository.save(movement); // Salva o histórico

        item.setQuantity(item.getQuantity() + quantity);
        itemRepository.save(item);
    }

    /**
     * Método para Baixar Estoque (Ex: Venda ou Uso)
     */
    @Transactional
    public void decreaseStock(Long itemId, int quantity) {
        Item item = findEntityById(itemId);

        if (item.getQuantity() < quantity) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        // 1. FABRICA A MOVIMENTAÇÃO (EXIT)
        InventoryMovement movement = movementFactory.createMovement(item, quantity, MovementType.SAIDA);
        inventoryMovementRepository.save(movement);

        // 2. ATUALIZA O SALDO DO ITEM
        item.setQuantity(item.getQuantity() - quantity);
        itemRepository.save(item);
    }
    @Transactional
    public List<InventoryMovementDTO> findAllMovements() {
        List<InventoryMovement> list = inventoryMovementRepository.findAll(Sort.by(Sort.Direction.DESC, "dateTime"));
        return list.stream().map(obj -> new InventoryMovementDTO(obj)).toList();
    }

}




