package ma.store.cartservice;

import ma.store.cartservice.config.keysConfig;
import ma.store.cartservice.models.Entitie.Cart;
import ma.store.cartservice.models.Entitie.CartItem;
import ma.store.cartservice.repositories.CartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import java.util.Set;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(keysConfig.class)
public class CartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CartRepository cartRepository) {
        return args -> {

            Cart cart1 = Cart.builder()
                    .userId(1L)
                    .build();

            CartItem item1 = CartItem.builder().qte(1).price(100.0).productId(1L).cart(cart1).build();
            CartItem item2 = CartItem.builder().qte(2).price(200.0).productId(2L).cart(cart1).build();
            cart1.setCartItems(Set.of(item1, item2));

            // Create Cart 2 and CartItems
            Cart cart2 = Cart.builder()
                    .userId(2L)
                    .build();

            CartItem item4 = CartItem.builder().qte(1).price(1500.0).productId(1L).cart(cart2).build();
            CartItem item5 = CartItem.builder().qte(2).price(300.0).productId(2L).cart(cart2).build();
            cart2.setCartItems(Set.of(item4, item5));

            // Persist carts
            cartRepository.save(cart1);
            cartRepository.save(cart2);

        };
    }
}
