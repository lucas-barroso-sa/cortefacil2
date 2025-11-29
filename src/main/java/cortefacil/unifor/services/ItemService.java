package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.ItemDTO;
import cortefacil.unifor.models.entities.Item;
import cortefacil.unifor.models.exceptions.DataBaseException;
import cortefacil.unifor.models.exceptions.ResourceNotFoundException;
import cortefacil.unifor.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public List<ItemDTO> findAll() {
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDTO> result = items.stream().map( i -> new ItemDTO(i)).toList();
            return result;
        }catch(Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

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

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {

            throw new IllegalArgumentException("O ID do recurso é obrigatório para a exclusão.");
        }
        try {
            itemRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Property not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation: Property ID " + id + " is referenced by other data (e.g., Reservations).");
        }
    }

}

