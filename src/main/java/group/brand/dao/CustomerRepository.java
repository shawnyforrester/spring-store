package group.brand.dao;

import group.brand.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**The User repository extends the JpaRespository and its predfined save(), findById() and delete() methods
 * Another method has been defined here called findByUsernameAndPassword to retrieve data by username and password */
@Repository
public interface CustomerRepository extends JpaRepository< Customer, Long> {
    Optional<Customer> findByUsernameAndPassword (String username, String password);
}
