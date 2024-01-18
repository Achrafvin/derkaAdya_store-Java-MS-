package ma.store.cartservice.services.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.cartservice.clients.ProductServiceClient;
import ma.store.cartservice.clients.UserServiceClient;
import ma.store.cartservice.mapper.Mapper;
import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.Entitie.CartItem;
import ma.store.cartservice.models.User;
import ma.store.cartservice.models.dto.CartItemDto;
import ma.store.cartservice.repositories.CartItemRepository;
import ma.store.cartservice.services.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final Mapper<CartItem, CartItemDto> mapper;
    private final CartItemRepository cartItemRepository;
    private final ProductServiceClient productServiceClient;
    @Override
    public List<CartItemDto> getAllCartItem() {
        List<CartItem> cartItem=cartItemRepository.findAll();
        cartItem.forEach(prod->prod.setProduct(productServiceClient.getProductById(prod.getProductId())));
        return cartItem.stream().map(mapper::mapFrom).collect(Collectors.toList());
    }


    @Override
    public CartItemDto getCArtItemById(Long id) {
        CartItemDto cartItemDto=mapper.mapFrom(cartItemRepository.findById(id).get());
        cartItemDto.setProduct(productServiceClient.getProductById(cartItemDto.getProductId()));
        return cartItemDto;
    }

    @Override
    public void addCartItem(CartItemDto cartItemDto) {
        CartItem cartItem=mapper.mapTo(cartItemDto);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        if(cartItemRepository.existsById(id))
        {
            cartItemRepository.deleteById(id);
        }
        else throw new RuntimeException("not found!!!");
    }


}
