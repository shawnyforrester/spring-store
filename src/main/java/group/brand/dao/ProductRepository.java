package group.brand.dao;

import group.brand.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**The User repository extends the JpaRepository and it's predefined save(), findById() and delete() methods
 * Additional methods has been defined here to find objects by their category and name values */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findEcommerceProductByCategory(String category);


    List<Product> findEcommerceProductByName(String name);




}
