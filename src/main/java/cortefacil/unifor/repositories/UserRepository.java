package cortefacil.unifor.repositories;

import cortefacil.unifor.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    // Em UserRepository.java
    UserDetails findByUsername(String username);
}
