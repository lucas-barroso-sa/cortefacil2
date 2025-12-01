package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.AppointmentDTO;
import cortefacil.unifor.models.DTOs.CustomerDTO;
import cortefacil.unifor.models.DTOs.ServiceItemDTO;
import cortefacil.unifor.models.entities.*;
import cortefacil.unifor.models.enuns.AppointmentStatus;
import cortefacil.unifor.models.exceptions.ResourceNotFoundException;
import cortefacil.unifor.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private UserService userService;
    private AppointmentRepository appointmentRepository;
    private CustomerService customerService;
    private ServicesService servicesService;
    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService, CustomerService customerService, ServicesService servicesService) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.customerService = customerService;
        this.servicesService = servicesService;
    }

    @Transactional
    public List<AppointmentDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDTO> list = appointments.stream().map(obj -> new AppointmentDTO(obj)).toList();
        return  list;
    }
    @Transactional
    public AppointmentDTO findById(long id) {
        Appointment result = appointmentRepository.findById(id).orElse(null);
        if(result == null) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        return new AppointmentDTO(result);
    }
    @Transactional
    public AppointmentDTO insert(AppointmentDTO appointmentDTO) {
        // 1. Instancia o Agendamento
        Appointment appointment = new Appointment();

        // 2. Define Data e Status Inicial
        appointment.setDateTime(appointmentDTO.getDateTime());
        appointment.setStatus(AppointmentStatus.PENDING); // Status padrão

        // 3. Busca e Define o Barbeiro (User)
        // Usamos o método auxiliar que criamos no UserService
        User barber = userService.findEntityById(appointmentDTO.getBarberId());
        appointment.setUser(barber);

        Customer customer;
        if (appointmentDTO.getCustomerId() != null) {
            // Fluxo normal: Busca existente
            customer = customerService.findEntityById(appointmentDTO.getCustomerId());
        } else {
            // Fluxo novo: Cria o cliente na hora
            Customer novo = new Customer();
            novo.setName(appointmentDTO.getCustomerName());
            novo.setPhone(appointmentDTO.getCustomerPhone()); // Pega do DTO atualizado
            customerService.insertEntity(novo); // Salva para gerar o ID
            appointment.setCustomer(novo);
        }


        // 5. Adiciona os Serviços (Itens)
        for (ServiceItemDTO itemDto : appointmentDTO.getItems()) {
            // Busca o serviço no catálogo
            Services services = servicesService.findEntityById(itemDto.getServiceId());
            // Cria o item de junção (Appointment + Service)
            // O construtor do ServiceItem já pega o preço atual do serviço e congela
            ServiceItem serviceItem = new ServiceItem(appointment, services);

            // Adiciona na lista (Set) do agendamento
            appointment.getItems().add(serviceItem);
        }

        // 6. Calcula o total (Soma dos preços dos serviços)
        appointment.calculateTotal();

        // 7. Salva no banco (Cascade salva os itens automaticamente)
        appointment = appointmentRepository.save(appointment);

        // 8. Retorna o DTO
        return new AppointmentDTO(appointment);
    }

}
