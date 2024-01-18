package ma.store.cartservice.services;

import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CartService {
    List<CartDto> getAllCarts();

    CartDto getCartById(Long id);

    void addCart(CartDto cartDto);

    void deleteCart(Long id);
}
