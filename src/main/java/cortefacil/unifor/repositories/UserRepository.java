package cortefacil.unifor.repositories;

import cortefacil.unifor.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
