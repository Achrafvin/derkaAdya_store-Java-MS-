package ma.store.cartservice.web;


import lombok.RequiredArgsConstructor;
import ma.store.cartservice.clients.ProductServiceClient;
import ma.store.cartservice.models.dto.CartItemDto;
import ma.store.cartservice.services.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/cartitems")
    public List<CartItemDto> getAllCartItem()
    {
        List<CartItemDto> cartItemDtos = cartItemService.getAllCartItem();
        return cartItemDtos;
    }

    @GetMapping("/cartitem/{id}")
    public CartItemDto getCartItemById(@PathVariable Long id)
    {
        CartItemDto cartItemDto = cartItemService.getCArtItemById(id);
        return cartItemDto;
    }

    @PostMapping("/cartitem")
    public void addCartItem(@RequestBody CartItemDto cartItemDto){
        cartItemService.addCartItem(cartItemDto);
    }

    @DeleteMapping("/cartitem/{id}")
    public void deleteCartById(@PathVariable("id") Long id){
        cartItemService.deleteCartItem(id);
    }
}
