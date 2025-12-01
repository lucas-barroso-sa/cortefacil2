package cortefacil.unifor.repositories;

import cortefacil.unifor.models.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
