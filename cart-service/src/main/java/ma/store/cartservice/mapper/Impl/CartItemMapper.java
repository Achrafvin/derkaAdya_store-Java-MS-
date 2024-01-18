package ma.store.cartservice.mapper.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.cartservice.mapper.Mapper;
import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.Entitie.CartItem;
import ma.store.cartservice.models.dto.CartDto;
import ma.store.cartservice.models.dto.CartItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper implements Mapper<CartItem, CartItemDto> {
    private final ModelMapper mapper;
    @Override
    public CartItem mapTo(CartItemDto cartItemDto) {
        return mapper.map(cartItemDto,CartItem.class);
    }

    @Override
    public CartItemDto mapFrom(CartItem cartItem) {
        return mapper.map(cartItem, CartItemDto.class);
    }
}
