package cortefacil.unifor.repositories;

import cortefacil.unifor.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface ItemRepository extends JpaRepository<Item, Long> {


}
