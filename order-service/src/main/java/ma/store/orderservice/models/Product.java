package ma.store.orderservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String name_prod;
    private  String desc_prod;
    private  String image_prod;
    private  Double price;
    private  int qte_prod;
    private Long idCat;

}
