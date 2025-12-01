package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.ServicesDTO;
import cortefacil.unifor.models.entities.Services;
import cortefacil.unifor.services.ServicesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;

    @GetMapping
    public List<ServicesDTO> findAll(){
        return servicesService.findAll();
    }
    @PostMapping
    public ServicesDTO insert(@RequestBody ServicesDTO dto){
        return servicesService.insert(dto);
    }

}
