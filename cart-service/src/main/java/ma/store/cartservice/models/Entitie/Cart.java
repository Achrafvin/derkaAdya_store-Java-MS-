package ma.store.cartservice.models.Entitie;

import jakarta.persistence.*;
import lombok.*;
import ma.store.cartservice.models.User;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "db_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItem> cartItems;
    private Long userId;
}
