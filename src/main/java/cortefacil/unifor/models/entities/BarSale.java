package cortefacil.unifor.models.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_barSale")
public class BarSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private double totalValue;

    @OneToMany()
    private Set<ItemSale> items = new HashSet<>();


}
