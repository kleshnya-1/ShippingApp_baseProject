package DAO;

//import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.DAO.OrderDAO;
import ru.laptseu.shippingApp.DAO.ProductDAO;
import ru.laptseu.shippingApp.Z_HZKUDA.Categories;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Order;
import ru.laptseu.shippingApp.models.Product;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderDAO Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���. 24, 2021</pre>
 */
@DisplayName("Order DAO Test")
class OrderDAOTest {


    private Order testOrder00;

    @BeforeEach
    void before() throws Exception {
        Client clientTEST0 = new Client("test name 0", "Test street0");
        Client clientTEST1 = new Client("test name 1", "Test street1");

        Product testProduct0 = new Product("testProduct0", 5, 0.63, "shopIdTest", Categories.FOOD, Categories.DRINKS);
        Product testProduct1 = new Product("testProduct1", 4, 0.93, "shopIdTest", Categories.FOOD, Categories.PETS);
        Product testProduct2 = new Product("testProduct2", 4, 0.93, "shopIdTest", Categories.CLOTHES);
        Product testProduct3 = new Product("testProduct3", 30, 10, "shopIdTest", Categories.CLOTHES);
        Product testProduct4 = new Product("testProduct4", 20, 1, "shopIdTest", Categories.ELECTRONICS);
        Product testProduct5 = new Product("testProduct5", 10, 5, "shopIdTest", Categories.MATERIALS);


        ProductDAO productDAO = new ProductDAO();
        productDAO.add(testProduct0);
        productDAO.add(testProduct1);
        productDAO.add(testProduct2);
        productDAO.add(testProduct3);
        productDAO.add(testProduct4);
        productDAO.add(testProduct5);

        Order testOrder0 = new Order(clientTEST0.getId(),
                "clientAdressTest", "note for test",
                new String[]{testProduct5.getId(), testProduct4.getId()},
                new int[]{1, 1});
        Order testOrder1 = new Order(clientTEST1.getId(),
                "clientAdressTest", "note for test",
                new String[]{testProduct3.getId(), testProduct2.getId()},
                new int[]{2, 2});
        Order testOrder2 = new Order(clientTEST1.getId(),
                "clientAdressTest", "note for test",
                new String[]{testProduct1.getId()},
                new int[]{1});

        testOrder0.setId("testIdForOrder");
        testOrder00=testOrder0;
        OrderDAO orderDAO = new OrderDAO();
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.reset();
        orderDAO.reset();
        productDAO.reset();
        clientDAO.add(clientTEST0);
        clientDAO.add(clientTEST1);
        orderDAO.add(testOrder2);
        orderDAO.add(testOrder1);
        orderDAO.add(testOrder0);


    }

    @AfterEach
    void after() throws Exception {
        OrderDAO orderDAO = new OrderDAO();
               ClientDAO clientDAO = new ClientDAO();
        clientDAO.reset();
        orderDAO.reset();
        ProductDAO productDAO = new ProductDAO();
        productDAO.reset();
    }

    /**
     * Method: getAll()
     */
    @Test
    @DisplayName("Test Get All")
    void testGetAll() throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        assertTrue(orderDAO.getAll().size()==3);
    }

    /**
     * Method: get(String id)
     */
    @Test
    @DisplayName("Test Get")
    void testGet() throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        assertEquals(orderDAO.get("testIdForOrder"), testOrder00);
    }



    /**
     * Method: delete(String id)
     */
    @Test
    @DisplayName("Test Delete")
    void testDelete() throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        assertEquals(orderDAO.get("testIdForOrder"), testOrder00);
        orderDAO.delete("testIdForOrder");
        assertNull(orderDAO.get("testIdForOrder"));


    }

}
