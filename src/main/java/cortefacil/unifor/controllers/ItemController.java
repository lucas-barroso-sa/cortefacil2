package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.ItemDTO;
import cortefacil.unifor.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDTO> findAll(){
        return itemService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO insert( @RequestBody ItemDTO dto) {
        return itemService.insert(dto);
    }

    @GetMapping(value = "/{id}")
    public ItemDTO findById(@PathVariable Long id){
        return itemService.findById(id);
    }


    @PutMapping(value = "/{id}")
    public ItemDTO updateByID(@PathVariable Long id,@RequestBody ItemDTO dto) {
        return itemService.updateByID(dto, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
    }

}
