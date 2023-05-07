package group.brand.controller;

import group.brand.exceptions.InventoryNotFoundException;
import group.brand.exceptions.UserNotFoundException;
import group.brand.exceptions.UserNotRegisteredException;
import group.brand.model.Product;
import group.brand.model.Customer;
import group.brand.service.storeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class StoreController {

    @Autowired
    private storeService sService;

    @Autowired
    public StoreController(storeService sService) {
        this.sService = sService;
    }

    /**This end point will handle the user registration or user sign-up whatever it may refer to in the front-end.
     * a request to register should be from http://localhost:9000/register such that requestbody will contain a user object in JSON format*/
    @PostMapping("register")
    public ResponseEntity signUp(Customer customer) { //should throw an exception
        try {
            if (sService.addUser(customer) != null) {
                return ResponseEntity.ok("User successfully added");
            }
        } catch (UserNotRegisteredException e) {
            return ResponseEntity.badRequest().body("User not added");

        }

        return null;
    }

    /**
     * This endpoint handles the user login requsted from http://localhost:9000/login     *
     * @param  customer object is sent in the request body and passed as an argument in this method.
     * @return ResppnseEntity should display a message in the console.
     */
    @PostMapping("login")
    public ResponseEntity logUser(Customer customer) {
        try {
            sService.login(customer);
            return ResponseEntity.ok().body("User successfully Logged-in");

        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found Unsuccessful login");

        }

    }

    /**
     * This endpoint handles the retrieval of all the products in the database. This is mainly used for testing purposes and will not be
     * needed as part of the functional scope of this project. *
     *
     * @return A List of product objects should be returned.
     */

    @GetMapping("products")
    public List<Product> getAllProducts() {
        try {
            return sService.getAllInventory();
        } catch (InventoryNotFoundException e) {
            return null;
        }
    }

    /**
     * This endpoint handles the retrieval of all the products by category from the database. This request should come from
     * http://localhost:9000/products/category?=shoes (for instance) this is needed as part of the functional scope of this project. *
     *
     * @return A List of product objects under a specific category (for instance shoes) should be returned.
     */

    @GetMapping(value = "products", params = {"category"})
    public List<Product> getProductsInCategory(@RequestParam("category") String category) {
        try {
            List<Product> products = sService.getInventoryByCategory(category);
            return products;
        } catch (InventoryNotFoundException e) {
            return null;
        }
    }

    /**
     * This endpoint handles the retrieval of all the products by name from the database. This request should come from
     * http://localhost:9000/products/name?=nike (for instance) this is needed as part of the functional scope of this project. *
     * @return A List of product objects under a specific name(for instance nike) should be returned.
     */

    @GetMapping(value = "products", params = {"name"})
    public List<Product> getProductByNameHandler(@RequestParam("name") String name) {
        try {
            List<Product> products = sService.getProductByName(name);
            return products;
        } catch (InventoryNotFoundException e) {
            return null;
        }
    }

    /**
     * This endpoint handles the addition of new products to the database. This request should come from
     * http://localhost:9000/products/add this is needed as part of the functional scope of this project.
     * @return A ResponseEntity should be returned to inform the user that a product has been successfully added.
     */

    @PostMapping("products/add")
    public ResponseEntity addProductHandler(Product product) {
        try {
            Optional<Product> newProduct = sService.addProduct(product);
            if (newProduct.isPresent()) {
                return ResponseEntity.ok().body("Product Successfully added");
            }

        } catch (InventoryNotFoundException e) {
            return ResponseEntity.badRequest().body("Product unsuccessfully added");
        } return null;

    }

    /**
     * This endpoint handles the removal of new products to the database. This request should come from
     * http://localhost:9000/products/delete this is needed as part of the functional scope of this project.
     * @return A ResponseEntity should be returned to inform the user that a product has been successfully removed.
     */

    @DeleteMapping("products/delete")
    public ResponseEntity deleteProductHandler(Product product){
        try{
            sService.deleteProduct(product);
            return ResponseEntity.ok().body("Product Successfully removed");
        }catch(InventoryNotFoundException e){
            return ResponseEntity.badRequest().body("Product unsuccessfully removed");
        }

    }


}
