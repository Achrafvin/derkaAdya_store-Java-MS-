package ma.store.cartservice.services.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.cartservice.clients.ProductServiceClient;
import ma.store.cartservice.mapper.Mapper;
import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.Entitie.CartItem;
import ma.store.cartservice.models.Product;
import ma.store.cartservice.models.dto.CartDto;
import ma.store.cartservice.repositories.CartItemRepository;
import ma.store.cartservice.repositories.CartRepository;
import ma.store.cartservice.services.CartItemService;
import ma.store.cartservice.services.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductServiceClient productServiceClient;
    private final Mapper<Cart,CartDto> mapper;


    @Override
    public List<CartDto> getAllCarts() {
       List<Cart> carts=cartRepository.findAll();
        carts.forEach(cart->cart.getCartItems().forEach(cartItem -> {
            Product product = productServiceClient.getProductById(cartItem.getProductId());
            cartItem.setProduct(product);
        }));
      return  carts.stream().map(mapper::mapFrom).collect(Collectors.toList());
    }

    @Override
    public CartDto getCartById(Long id) {
        Cart cart1 = cartRepository.findById(id).get();
        cart1.getCartItems().forEach(cartItem->cartItem.setProduct(
                productServiceClient.getProductById(cartItem.getProductId())
        ));

        CartDto cartDto=mapper.mapFrom(cart1);
      return cartDto;
    }

    @Override
    public void addCart(CartDto cartDto) {
        Cart cart=mapper.mapTo(cartDto);
        cartRepository.save(cart);

    }

    @Override
    public void deleteCart(Long id) {
        if(cartRepository.existsById(id))
        {
            cartRepository.deleteById(id);
        }
        else throw new RuntimeException("not found!!!");

    }

//    @Override
//    public List<CartDto> getCartsByUserId(Long id) {
//        if(cartRepository.existsByUserId(id))
//        {
//            List<Cart> carts =cartRepository.getCartsByUserId(id);
//            carts.forEach(cart -> cart.getCartItems().forEach(cartItem -> {
//                Product product=productServiceClient.getProductById(cartItem.getProductId());
//                cartItem.setProduct(product);
//            }));
//            return carts.stream().map(mapper::mapFrom).collect(Collectors.toList());
//        }else throw new RuntimeException();
//
//    }
    /*
    *    @Override
    public List<CartDto> getAllCarts() {
       List<Cart> carts=cartRepository.findAll();
        carts.forEach(cart->cart.getCartItems().forEach(cartItem -> {
            Product product = productServiceClient.getProductById(cartItem.getProductId());
            cartItem.setProduct(product);
        }));
      return  carts.stream().map(mapper::mapFrom).collect(Collectors.toList());
    }*/
}
