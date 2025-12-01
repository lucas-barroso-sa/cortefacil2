package cortefacil.unifor.models.entities;

import cortefacil.unifor.models.key.ServiceItemId;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_service_item")
public class ServiceItem {

    @EmbeddedId
    private ServiceItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("appointmentId")
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private Services services;

    private Double price; // Preço congelado do serviço

    public ServiceItem() {}

    public ServiceItem(Appointment appointment, Services services) {
        // Inicializa a chave composta
        this.id = new ServiceItemId(appointment.getId(), services.getId());

        this.appointment = appointment;
        this.services = services;

        // Congela o preço atual do serviço do banco
        this.price = services.getPrice();
    }

    // Getters e Setters
    public ServiceItemId getId() { return id; }
    public void setId(ServiceItemId id) { this.id = id; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public Services getService() { return services; }
    public void setService(Services services) { this.services = services; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    // Equals e HashCode (Obrigatórios para Set<>)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceItem that = (ServiceItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}