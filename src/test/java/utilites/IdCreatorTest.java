package utilites;

//import org.junit.Assert;
import org.junit.jupiter.api.*;
import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.utilites.IdCreator;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * IdCreator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���. 27, 2021</pre>
 */
@DisplayName("Id Creator Test")
class IdCreatorTest {

    @BeforeEach
    void before() throws Exception {
    }

    @AfterEach
    void after() throws Exception {
    }

    /**
     * Method: createId(int length)
     */
    @Test
    @DisplayName("Test Create Id")
    void testCreateId() throws Exception {
        IdCreator idCreatorTest = new IdCreator();
        for (int i = 0; i <= 10; i++) {
            assertTrue(idCreatorTest.createId(i).length() == i);
        }
    }

    /**
     * Method: checkId(String id, Object tClass)
     */
    @Test
    @DisplayName("Test Check Id")
    void testCheckId() throws Exception {
        IdCreator idCreatorTest = new IdCreator();
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.reset();
        Client clientTestMethod = new Client("clientTestMethod", "Test street method");
        clientTestMethod.setId("someTestId69");
        Assertions.assertFalse(idCreatorTest.checkId("someTestId69", new Client()));
        clientDAO.add(clientTestMethod);
        assertTrue(idCreatorTest.checkId("someTestId69", new Client()));
    }

}
