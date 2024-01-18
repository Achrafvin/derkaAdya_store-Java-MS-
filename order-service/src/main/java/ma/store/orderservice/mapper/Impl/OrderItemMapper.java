package ma.store.orderservice.mapper.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.orderservice.mapper.Mapper;

import ma.store.orderservice.models.Entitie.OrderItem;
import ma.store.orderservice.models.dto.OrderItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper implements Mapper<OrderItem, OrderItemDto> {
    private final ModelMapper mapper;
    @Override
    public OrderItem mapTo(OrderItemDto cartItemDto) {
        return mapper.map(cartItemDto,OrderItem.class);
    }

    @Override
    public OrderItemDto mapFrom(OrderItem cartItem) {
        return mapper.map(cartItem, OrderItemDto.class);
    }
}
