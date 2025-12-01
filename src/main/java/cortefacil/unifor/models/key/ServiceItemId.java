package cortefacil.unifor.models.key;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ServiceItemId implements Serializable {

    private Long appointmentId;
    private Long serviceId;



    public ServiceItemId() {}

    public ServiceItemId(Long appointmentId, Long serviceId) {
        this.appointmentId = appointmentId;
        this.serviceId = serviceId;
    }


    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ServiceItemId that = (ServiceItemId) o;
        return Objects.equals(appointmentId, that.appointmentId) && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, serviceId);
    }
}