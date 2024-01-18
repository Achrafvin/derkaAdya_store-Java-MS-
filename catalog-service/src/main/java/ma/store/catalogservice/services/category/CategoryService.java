package ma.store.catalogservice.services.category;


import ma.store.catalogservice.models.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryByID(Long id);

    void addCategory(Category category);

    void deleteCategory(Long id);

}
