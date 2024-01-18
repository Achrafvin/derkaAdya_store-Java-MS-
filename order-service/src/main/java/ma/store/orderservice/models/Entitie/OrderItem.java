package ma.store.orderservice.models.Entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.store.orderservice.models.Product;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "db_ordertitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private int qte;
    private Double price;
    @Transient
    private Product product;
    private Long productId;
}
