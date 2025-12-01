package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.CustomerDTO;
import cortefacil.unifor.models.entities.Customer;
import cortefacil.unifor.models.exceptions.ResourceNotFoundException;
import cortefacil.unifor.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private  CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findEntityById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer with id " + id + " not found");
        }
        return customer;
    }
    @Transactional
    public void insertEntity(Customer entity) {
        customerRepository.save(entity);
    }
    public CustomerDTO insert(CustomerDTO dto) {
        Customer entity = new Customer();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        customerRepository.save(entity);
        return new CustomerDTO(entity);
    }

}
