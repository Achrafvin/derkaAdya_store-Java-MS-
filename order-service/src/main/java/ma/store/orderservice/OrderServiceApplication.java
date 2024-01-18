package ma.store.orderservice;

import ma.store.orderservice.clients.ProductServiceClient;
import ma.store.orderservice.config.KeysConfig;
import ma.store.orderservice.models.Entitie.Order;
import ma.store.orderservice.models.Entitie.OrderItem;
import ma.store.orderservice.models.Product;
import ma.store.orderservice.repositories.OrderItemRepository;
import ma.store.orderservice.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(KeysConfig.class)
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductServiceClient productServiceClient) {
        return args -> {
            Product product=productServiceClient.getProductById(1L);
            // Creating an order
            Order order = Order.builder()
                    .userId(1L) // Example user ID
                    .totalPrice(300.0) // Total price
                    .orderDate(new Date()) // Current date
                    .build();

            // Save the order first
            Order savedOrder = orderRepository.save(order);

            // Creating and saving order items
            OrderItem item1 = OrderItem.builder()
                    .order(savedOrder)
                    .productId(1L) // Assuming Product with ID 1
                    .qte(3)
                    .price(product.getPrice()*3) // Example price
                    .build();
            orderItemRepository.save(item1);

            OrderItem item2 = OrderItem.builder()
                    .order(savedOrder)
                    .productId(2L) // Assuming Product with ID 2
                    .qte(2)
                    .price(200.0) // Example price
                    .build();
            orderItemRepository.save(item2);
        };
        }
    }

