package cortefacil.unifor.repositories;

import cortefacil.unifor.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByActiveTrue();
}
