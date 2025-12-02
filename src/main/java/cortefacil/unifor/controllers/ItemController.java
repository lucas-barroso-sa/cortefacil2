package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.ItemDTO;
import cortefacil.unifor.models.DTOs.StockUpdateDTO;
import cortefacil.unifor.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    //ajustar formato de input
    @PutMapping(value = "/{id}")
    public ItemDTO updateByID(@PathVariable Long id,@RequestBody ItemDTO dto) {
        return itemService.updateByID(dto, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @PatchMapping(value = "/{id}/add-stock")
    public ResponseEntity<Void> addStock(@PathVariable Long id, @RequestBody StockUpdateDTO dto) {
        itemService.addStock(id, dto.getQuantity());
        return ResponseEntity.noContent().build();
    }

    // 2. Baixar Estoque (Uso interno / Perda / Ajuste)
    // URL: PATCH /items/{id}/decrease-stock
    @PatchMapping(value = "/{id}/decrease-stock")
    public ResponseEntity<Void> decreaseStock(@PathVariable Long id, @RequestBody StockUpdateDTO dto) {
        itemService.decreaseStock(id, dto.getQuantity());
        return ResponseEntity.noContent().build();
    }
}



