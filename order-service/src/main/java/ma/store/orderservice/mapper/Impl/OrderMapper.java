package ma.store.orderservice.mapper.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.orderservice.mapper.Mapper;

import ma.store.orderservice.models.Entitie.Order;
import ma.store.orderservice.models.dto.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<Order, OrderDto> {
    private final ModelMapper mapper;
    @Override
    public Order mapTo(OrderDto cartDto) {
        return mapper.map(cartDto,Order.class);
    }

    @Override
    public OrderDto mapFrom(Order cart) {
        return mapper.map(cart, OrderDto.class);
    }
}
