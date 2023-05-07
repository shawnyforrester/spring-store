package group.brand;

import group.brand.dao.ProductRepository;
import group.brand.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class RepositoryTests {

    @Autowired
    ProductRepository pRepo;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testFindEcommerceProductByCategory(){
        Product newProduct = new Product();
        newProduct.setCategory("shoes");

        newProduct = entityManager.persistAndFlush(newProduct);
        assertThat(pRepo.findEcommerceProductByCategory("shoes").stream()
                .filter(product -> "shoes".equals(product.getCategory())).findAny()
                .get().getCategory()).isEqualTo("shoes");


    }

    @Test
    public void testFindEcommerceProductByName(){
        Product searchProduct = new Product();
        searchProduct.setName("Nike");

        searchProduct = entityManager.persistAndFlush(searchProduct);

        assertThat(pRepo.findEcommerceProductByName("Nike").stream()
                .filter(product -> "Nike".equals(product.getName()))
                .findAny().get().getName()).isEqualTo("Nike");




    }
}
