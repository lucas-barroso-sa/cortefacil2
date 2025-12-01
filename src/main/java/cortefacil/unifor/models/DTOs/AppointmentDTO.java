package cortefacil.unifor.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import cortefacil.unifor.models.entities.Appointment;
import cortefacil.unifor.models.enuns.AppointmentStatus;
import cortefacil.unifor.models.enuns.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentDTO {

    private Long id;

    // Formatação essencial para garantir que o JSON trafegue a data corretamente
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    private AppointmentStatus status;
    private Double total;

    // --- Dados do Barbeiro (User) ---
    private Long barberId;      // Usado no POST (Input)
    private String barberName;  // Usado no GET (Output)

    // --- Dados do Cliente (Customer) ---
    private Long customerId;    // Usado no POST (Input)
    private String customerName;// Usado no GET (Output)
    private String customerPhone;

    // --- Lista de Serviços ---
    private List<ServiceItemDTO> items = new ArrayList<>();


    public AppointmentDTO() {
    }

    public AppointmentDTO(Appointment entity) {
        this.id = entity.getId();
        this.dateTime = entity.getDateTime();
        this.status = entity.getStatus();
        this.total = entity.getTotalValue();

        // Mapeia dados do Barbeiro
        if (entity.getUser() != null) {
            this.barberId = entity.getUser().getId();
            this.barberName = entity.getUser().getName();
        }

        // Mapeia dados do Cliente
        if (entity.getCustomer() != null) {
            this.customerId = entity.getCustomer().getId();
            this.customerName = entity.getCustomer().getName();
            this.customerPhone = entity.getCustomer().getPhone();
        }


        if (entity.getItems() != null) {
            this.items = entity.getItems().stream()
                    .map(ServiceItemDTO::new)
                    .toList();
        }
    }

    // --- Getters e Setters ---

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getBarberId() {
        return barberId;
    }

    public void setBarberId(Long barberId) {
        this.barberId = barberId;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ServiceItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ServiceItemDTO> items) {
        this.items = items;
    }
}