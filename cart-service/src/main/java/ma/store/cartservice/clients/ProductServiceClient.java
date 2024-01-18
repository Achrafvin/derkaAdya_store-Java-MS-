package ma.store.cartservice.clients;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.store.cartservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "catalog-service")
public interface ProductServiceClient {

    @GetMapping("/product/{id}")
    @CircuitBreaker(name = "inventoryService",fallbackMethod = "getDefaultProduct")
    Product getProductById(@PathVariable("id") Long id);

    @GetMapping("/products")
    List<Product> getAllProducts();

    default Product getDefaultProduct(Long id, Exception e){
        Product product = new Product();
        product.setId(null);
        product.setName_prod("Not Availbale");
        product.setDesc_prod("Not Availbale");
        product.setImage_prod("Not Availbale");
        product.setPrice(null);
        product.setQte_prod(0);
        product.setIdCat(null);
        return product;
    }
}
