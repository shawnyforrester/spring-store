package group.brand.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private String description;

    @Column
    private String rating;




}
