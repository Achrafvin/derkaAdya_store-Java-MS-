package ma.store.catalogservice.web;

import lombok.RequiredArgsConstructor;

import ma.store.catalogservice.mappers.Mapper;
import ma.store.catalogservice.models.dto.CategoryDto;
import ma.store.catalogservice.models.entities.Category;
import ma.store.catalogservice.services.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Mapper<Category, CategoryDto> mapper;


    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category> categoryList = categoryService.getAllCategories();
        return new ResponseEntity(categoryList, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public Category getCetegoryById(@PathVariable Long id)
    {
        return categoryService.getCategoryByID(id);
    }
   @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/category")
    public void addProduct(@RequestBody CategoryDto categoryDto){
        Category category = mapper.mapTo(categoryDto);
        categoryService.addCategory(category);
       // return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }

}
