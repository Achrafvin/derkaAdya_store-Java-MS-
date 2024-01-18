package ma.store.cartservice.models.dto;

import lombok.*;
import ma.store.cartservice.models.Entitie.CartItem;

import java.util.Set;

@Getter@Setter@NoArgsConstructor
@AllArgsConstructor@Builder
public class CartDto {

    private Long id;
    private Set<CartItem> cartItems;
    private Long userId;

}
