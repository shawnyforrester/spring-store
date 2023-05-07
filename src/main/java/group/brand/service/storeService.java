package group.brand.service;

import group.brand.dao.ProductRepository;
import group.brand.dao.CustomerRepository;
import group.brand.exceptions.InventoryNotFoundException;
import group.brand.exceptions.UserNotFoundException;
import group.brand.exceptions.UserNotRegisteredException;
import group.brand.model.Product;
import group.brand.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class storeService {


    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ProductRepository productRepo;

    public storeService(CustomerRepository customerRepo, ProductRepository productRepo) {
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    public Object addUser(Customer customer) throws UserNotRegisteredException {
        if (customerRepo.save(customer) != null) {
            return customerRepo.save(customer);

        }
        throw new UserNotRegisteredException("user not successfully registered");

    }

    public Object login(Customer customer) throws UserNotFoundException {
        if (customerRepo.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()).isPresent()) {
            return customerRepo.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()).get();
        }
        throw new UserNotFoundException("User not successfully logged in");

    }

    public List<Product> getAllInventory() throws InventoryNotFoundException {
        List<Product> totalInventory = productRepo.findAll();
        if (totalInventory.isEmpty()) {
            throw new InventoryNotFoundException("No products found");
        }
        return totalInventory;

    }

    public List<Product> getInventoryByCategory(String category) throws InventoryNotFoundException {
        List<Product> products = productRepo.findEcommerceProductByCategory(category);
        if (products.isEmpty()) {
            throw new InventoryNotFoundException("No products Found");
        }
        return products;
    }

    public List<Product> getProductByName(String name) throws InventoryNotFoundException {
        List<Product> products = productRepo.findEcommerceProductByName(name);
        if (products.isEmpty()) {
            throw new InventoryNotFoundException("No products Found");
        }
        return products;
    }

    public Optional<Product> addProduct(Product product) throws InventoryNotFoundException {
        Product newProduct = productRepo.save(product);
        Optional<Product> savedProduct = productRepo.findById(newProduct.getId());

        if(savedProduct.isEmpty()) {
            throw new InventoryNotFoundException("Product not added");
        }
        return savedProduct;
    }

    public void deleteProduct(Product product) throws InventoryNotFoundException {
        productRepo.delete(product);
        if (productRepo.existsById(product.getId())) {
            throw new InventoryNotFoundException("Product not removed");
        }
    }

}
