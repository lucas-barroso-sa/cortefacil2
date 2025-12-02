package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.InventoryMovementDTO;
import cortefacil.unifor.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/inventory-movements")
public class InventoryMovementController {
    @Autowired
    private  ItemService itemService;

    public InventoryMovementController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<InventoryMovementDTO> findAll() {
       return itemService.findAllMovements();
    }
}