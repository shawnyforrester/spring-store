package group.brand.model;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "customer")
public class Customer {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String number;
    @Column
    private String email;



}
