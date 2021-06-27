package DAO;


//import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.laptseu.shippingApp.DAO.ShopDAO;
import ru.laptseu.shippingApp.models.Shop;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * ShopDAO Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���. 24, 2021</pre>
 */
@DisplayName("Shop DAO Test")
class ShopDAOTest {


    ArrayList <Shop> testShops = new ArrayList<>();

    @BeforeEach
    void beforeEach() throws Exception {

        ShopDAO shopDAO = new ShopDAO();
        shopDAO.reset();

        Shop testShop0 = new Shop("testShop0", "Boulevard of testing building 0");
        Shop testShop1 = new Shop("testShop1", "Boulevard of testing building 1");
        shopDAO.add(testShop0);
        shopDAO.add(testShop1);
        testShops.add(testShop0);
        testShops.add(testShop1);

    }

    @AfterEach
    void after() throws Exception {
             ShopDAO shopDAO = new ShopDAO();
               shopDAO.reset();
    }

    /**
     * Method: getAll()
     */
    @Test
    @DisplayName("Test Get All")
    void testGetAll() throws Exception {
        ShopDAO shopDAO = new ShopDAO();
        ArrayList<Shop> testShopMethod = shopDAO.getAll();
        assertEquals(testShopMethod, testShops);
    }

    /**
     * Method: get(String id)
     */
    @Test
    @DisplayName("Test Get")
    void testGet() throws Exception {
        ShopDAO shopDAO = new ShopDAO();
        for (Shop c : testShops) {
            assertEquals(c, shopDAO.get(c.getId()));
        }
    }

    /**
     * Method: delete(String id)
     */
    @Test
    @DisplayName("Test Delete")
    void testDelete() throws Exception {
        ShopDAO shopDAO = new ShopDAO();
        Shop shopTestMethod = new Shop("shopTestMethod", "Test street method");
        shopDAO.add(shopTestMethod);
        shopDAO.delete(shopTestMethod.getId());
        Shop shopFromDAO = (Shop) shopDAO.get(shopTestMethod.getId());
        assertNull(shopFromDAO);
    }
     /**
     * Method: add(Shop shop)
     */
    @Test
    @DisplayName("Test Add")
    void testAdd() throws Exception {
        ShopDAO shopDAO = new ShopDAO();
        Shop shopTestMethod = new Shop("shopTestMethod", "Test street method");
        shopDAO.add(shopTestMethod);
        Shop shopFromDAO = (Shop) shopDAO.get(shopTestMethod.getId());
        assertEquals(shopFromDAO, shopTestMethod);
    }

    /**
     * Method: updateAddress(String id, String address)
     */
    @Test
    @DisplayName("Test Update Address")
    void testUpdateAddress() throws Exception {
        ShopDAO shopDAO = new ShopDAO();
       Shop shopTestMethod = new Shop("shopTestMethod", "Test street method");
        shopDAO.add(shopTestMethod);
        String newAddress = "newAddress";
        shopDAO.updateAddress(shopTestMethod.getId(), newAddress);
        Shop updatedShop = (Shop) shopDAO.get(shopTestMethod.getId());
        assertEquals(updatedShop.getAddress(), newAddress);
    }
}
