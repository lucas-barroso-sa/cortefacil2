package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.CustomerDTO;
import cortefacil.unifor.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerContoller {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerDTO insert(CustomerDTO dto) {
        return customerService.insert(dto);
    }


}
