package ma.store.cartservice.models.dto;

import lombok.*;
import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.Product;

@Getter@Setter@Builder@NoArgsConstructor@AllArgsConstructor
public class CartItemDto {
    private Long id;
    ///
   // private Cart cart;
    private int qte;
    private Double price;
    private Product product;
    private Long productId;
}
