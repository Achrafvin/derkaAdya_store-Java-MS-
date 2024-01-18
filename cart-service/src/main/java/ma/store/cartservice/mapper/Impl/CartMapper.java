package ma.store.cartservice.mapper.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.cartservice.mapper.Mapper;
import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.dto.CartDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper implements Mapper<Cart,CartDto> {
    private final ModelMapper mapper;
    @Override
    public Cart mapTo(CartDto cartDto) {
        return mapper.map(cartDto,Cart.class);
    }

    @Override
    public CartDto mapFrom(Cart cart) {
        return mapper.map(cart, CartDto.class);
    }
}
