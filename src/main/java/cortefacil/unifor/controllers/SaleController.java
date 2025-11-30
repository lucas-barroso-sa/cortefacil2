package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.SaleDTO;
import cortefacil.unifor.models.entities.Sale;
import cortefacil.unifor.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<SaleDTO> findAll(){
        return saleService.findAll();
    }

    @PostMapping
    public ResponseEntity<SaleDTO> insert(@RequestBody SaleDTO dto) {
        // 1. Chama o serviço (Regra de Negócio)
        dto = saleService.insert(dto);

        // 2. Cria a URI (O link para acessar a nova venda)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getSaleId()).toUri();

        // 3. Monta a Resposta HTTP 201 Created
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{id}")
    public SaleDTO findById(@PathVariable Long id) {
        return saleService.findById(id);
    }

}
