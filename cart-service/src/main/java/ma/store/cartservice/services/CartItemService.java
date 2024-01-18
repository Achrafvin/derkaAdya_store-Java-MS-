package ma.store.cartservice.services;

import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.Entitie.CartItem;
import ma.store.cartservice.models.dto.CartItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {
    List<CartItemDto> getAllCartItem();

    CartItemDto getCArtItemById(Long id);

    void addCartItem(CartItemDto cartItem);

    void deleteCartItem(Long id);

}
