package cortefacil.unifor.models.key;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AppointmentServiceId implements Serializable {

    private Long appointmentId;
    private Long serviceId;

    // Construtores, Getters, Setters e os OBRIGATÓRIOS hashCode e equals
    // (O código é idêntico ao SaleItemId, apenas com nomes diferentes)

    public AppointmentServiceId() {}

    public AppointmentServiceId(Long appointmentId, Long serviceId) {
        this.appointmentId = appointmentId;
        this.serviceId = serviceId;
    }

    // hashCode() e equals() - Omitidos para brevidade, mas OBRIGATÓRIOS.
}