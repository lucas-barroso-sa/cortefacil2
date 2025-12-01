package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.ServicesDTO;
import cortefacil.unifor.models.entities.Item;
import cortefacil.unifor.models.entities.Services;
import cortefacil.unifor.models.exceptions.ResourceNotFoundException;
import cortefacil.unifor.repositories.ServicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {
    private ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<ServicesDTO> findAll() {
        List<Services> services = servicesRepository.findAll();
        List<ServicesDTO> servicesDTOs = services.stream().map(obj-> new ServicesDTO(obj)).toList();
        return servicesDTOs;
    }

    public Services findEntityById(Long id) {
        return servicesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item not found"));

    }
    @Transactional
    public ServicesDTO insert(ServicesDTO servicesDTO) {
        Services services = new Services();
        services.setName(servicesDTO.getName());
        services.setPrice(servicesDTO.getPrice());
        servicesRepository.save(services);
        return new ServicesDTO(services);
    }

}
