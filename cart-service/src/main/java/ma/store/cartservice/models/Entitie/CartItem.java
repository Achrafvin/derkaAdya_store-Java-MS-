package ma.store.cartservice.models.Entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.store.cartservice.models.Product;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "db_cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    private int qte;
    private Double price;
    @Transient
    private Product product;
    private Long productId;
}
