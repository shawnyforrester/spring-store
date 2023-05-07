package group.brand;



import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import group.brand.controller.StoreController;
import group.brand.dao.ProductRepository;
import group.brand.model.Customer;
import group.brand.service.storeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



import static org.mockito.ArgumentMatchers.any;



@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BrandApplicationTests {


    @Autowired
    ProductRepository pRepo;

    @InjectMocks
    StoreController controller;

    @MockBean
    private storeService sService;


    static Customer newCustomer = new Customer();

    @Test
    void contextLoads() throws Exception {

    }


    @BeforeAll
    static void setUpTestingData(){

        newCustomer.setId(1L);
        newCustomer.setName("test name");
        newCustomer.setUsername("test");
        newCustomer.setPassword("1234");
        newCustomer.setEmail("test@gmail.com");
        newCustomer.setNumber("555-123-6666");
    }
    /**
     * This test uses the MockHttpServlet Request class  to test the signup method in the controller class
     */
    @Test
    public void testSignUpHandler() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(sService.addUser(any(Customer.class))).thenReturn(true);

        ResponseEntity<Object> responseEntity = controller.signUp(newCustomer);

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);

    }

    /**
     * This test uses the MockHttpServlet Request class  to test the login method in the controller class
     */
    @Test
    public void testLoginHandler() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(sService.login(any(Customer.class))).thenReturn(true);
        ResponseEntity<Object> responseEntity = controller.logUser(newCustomer);

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);


    }



}
