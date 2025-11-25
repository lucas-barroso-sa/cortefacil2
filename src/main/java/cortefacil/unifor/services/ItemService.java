package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.ItemDTO;
import cortefacil.unifor.models.entities.Item;
import cortefacil.unifor.models.exceptions.ResourceNotFound;
import cortefacil.unifor.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public List<ItemDTO> findAll() {
        try {
            List<Item> items = itemRepository.findAll();
            List<ItemDTO> result = items.stream().map( i -> new ItemDTO(i)).toList();
            return result;
        }catch(Exception e) {
            throw new ResourceNotFound(e.getMessage());
        }

    }

}
