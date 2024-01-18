package ma.store.catalogservice.web;


import lombok.RequiredArgsConstructor;
import ma.store.catalogservice.mappers.Mapper;
import ma.store.catalogservice.models.dto.ProductDto;
import ma.store.catalogservice.models.entities.Product;
import ma.store.catalogservice.services.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final Mapper<Product, ProductDto> mapper;


    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id)
    {
        return productService.getProductByID(id);
    }
    @GetMapping("/products/nbr")
    public long nbrProducts()
    {
        return productService.nbrProducts();
    }

    @GetMapping("/products/{id}")
    public List<Product> getProductByCategoryId(@PathVariable Long id)
    {
        return productService.getProductsByCategoryId(id);
    }


    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto){
        Product product = mapper.mapTo(productDto);
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/product/update")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto){
        Product product = mapper.mapTo(productDto);
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }



}
