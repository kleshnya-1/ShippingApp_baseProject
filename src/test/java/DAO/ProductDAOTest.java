package DAO;

//import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.DAO.ProductDAO;
import ru.laptseu.shippingApp.DAO.ShopDAO;
import ru.laptseu.shippingApp.Z_HZKUDA.Categories;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Product;
import ru.laptseu.shippingApp.models.Shop;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ProductDAO Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���. 24, 2021</pre>
 */
@DisplayName("Product DAO Test")
class ProductDAOTest {
    ArrayList<Product> testProducts = new ArrayList<>();
    ArrayList <Shop> testShops = new ArrayList<>();

    @BeforeEach
    void before() throws Exception {

        ProductDAO productDAO = new ProductDAO();
        ShopDAO shopDAO = new ShopDAO();

        productDAO.reset();
        shopDAO.reset();

        Shop testShop0 = new Shop("testShop0", "Boulevard of testing building 0");
        Shop testShop1 = new Shop("testShop1", "Boulevard of testing building 1");
        shopDAO.add(testShop0);
        shopDAO.add(testShop1);
        testShops.add(testShop0);
        testShops.add(testShop1);

        Product testProduct0 = new Product("testProduct0", 5, 0.63, testShop0.getId(), Categories.FOOD, Categories.DRINKS);
        Product testProduct1 = new Product("testProduct1", 4, 0.93, testShop0.getId(), Categories.FOOD, Categories.PETS);
        Product testProduct2 = new Product("testProduct2", 4, 0.93, testShop0.getId(), Categories.CLOTHES);
        Product testProduct3 = new Product("testProduct3", 30, 10, testShop1.getId(), Categories.CLOTHES);
        Product testProduct4 = new Product("testProduct4", 20, 1, testShop1.getId(), Categories.ELECTRONICS);
        Product testProduct5 = new Product("testProduct5", 10, 5, testShop1.getId(), Categories.MATERIALS);
        productDAO.add(testProduct0);
        productDAO.add(testProduct1);
        productDAO.add(testProduct2);
        productDAO.add(testProduct3);
        productDAO.add(testProduct4);
        productDAO.add(testProduct5);
        testProducts.add(testProduct0);
        testProducts.add(testProduct1);
        testProducts.add(testProduct2);
        testProducts.add(testProduct3);
        testProducts.add(testProduct4);
        testProducts.add(testProduct5);


    }

    @AfterEach
    void after() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        ShopDAO shopDAO = new ShopDAO();

        productDAO.reset();
        shopDAO.reset();

    }

    /**
     * Method: getAll()
     */
    @Test
    @DisplayName("Test Get All")
    void testGetAll() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> testProductsMethod = productDAO.getAll();
        assertEquals(testProductsMethod, testProducts);
    }

    /**
     * Method: get(String id)
     */
    @Test
    @DisplayName("Test Get")
    void testGet() throws Exception {

        ProductDAO productDAO = new ProductDAO();
        for (Product c : testProducts) {
            assertEquals(c, productDAO.get(c.getId()));
        }

    }



    /**
     * Method: delete(String id)
     */
    @Test
    @DisplayName("Test Delete")
    void testDelete() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product testProduct0 = new Product("testProduct0", 5, 0.63, "testId1", Categories.FOOD, Categories.DRINKS);
        productDAO.add(testProduct0);
        assertEquals(productDAO.get(testProduct0.getId()), testProduct0);
        productDAO.delete(testProduct0.getId());
        Product productFromDAO = (Product) productDAO.get(testProduct0.getId());
        assertNull(productFromDAO);

    }


    /**
     * Method: reset()
     */
    @Test
    @DisplayName("Test Reset")
    void testReset() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product testProduct0 = new Product("testProduct0", 5, 0.63, "testId1", Categories.FOOD, Categories.DRINKS);
        productDAO.reset();

        productDAO.add(testProduct0);
        assertEquals(productDAO.get(testProduct0.getId()), testProduct0);

        assertTrue(productDAO.getAll().size()==1);
        productDAO.reset();
        assertTrue(productDAO.getAll().size()==0);

    }

    /**
     * Method: add(Product product)
     */
    @Test
    @DisplayName("Test Add")
    void testAdd() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product testProduct0 = new Product("testProduct0", 5, 0.63, "testId1", Categories.FOOD, Categories.DRINKS);
        productDAO.reset();
        productDAO.add(testProduct0);
        assertEquals(productDAO.get(testProduct0.getId()), testProduct0);
    }

    /**
     * Method: updateProduct(String id, String name, int quantity, double price, String shopId, Categories... categories)
     */
    @Test
    @DisplayName("Test Update Product")
    void testUpdateProduct() throws Exception {
        ProductDAO productDAO = new ProductDAO();

        Product testProduct6 = new Product("testProduct6", 30, 10, "testShopId0", Categories.CLOTHES);
        Product testProduct7 = new Product("testProduct7", 30, 10, "testShopId1", Categories.CLOTHES);
        Product testProduct8 = new Product("testProduct8", 30, 10, "testShopId2", Categories.CLOTHES);


        productDAO.add(testProduct6);
        productDAO.add(testProduct7);
        productDAO.add(testProduct8);

        productDAO.updateProduct(testProduct6.getId(),"updatedName",-1,-1,null);
        Product newName = (Product) productDAO.get(testProduct6.getId());
        assertEquals(newName.getName(),"updatedName" );

        productDAO.updateProduct(testProduct6.getId(),"updatedName",10,-1,null);
        Product newQuantity = (Product) productDAO.get(testProduct6.getId());
        assertEquals(newQuantity.getQuantity(),10 );

        productDAO.updateProduct(testProduct6.getId(),"updatedName",10,10,null);
        Product newPrice = (Product) productDAO.get(testProduct6.getId());
        assertTrue(newPrice.getPrice()==10 );

        productDAO.updateProduct(testProduct6.getId(),"updatedName",10,10,"newShopId");
        Product newShopId = (Product) productDAO.get(testProduct6.getId());
        assertEquals(newShopId.getShopId(),"newShopId" );


    }
}
