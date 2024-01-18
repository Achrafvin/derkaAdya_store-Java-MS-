package ma.store.catalogservice.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name_prod;
    private  String desc_prod;
    private  Double price;
    private  int qte_prod;
    private String image_prod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCat")
    @JsonIgnore
    private Category category;
    @Transient
    private Long idCat;
}