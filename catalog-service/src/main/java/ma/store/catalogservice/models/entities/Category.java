package ma.store.catalogservice.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    private String name_cat;
    private  String img_name;
    @OneToMany(mappedBy="category" , cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;
}
