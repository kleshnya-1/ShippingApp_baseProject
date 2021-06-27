//import org.junit.Assert;
import org.junit.jupiter.api.*;
import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.models.Client;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * ClientDAO Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���. 24, 2021</pre>
 */

@DisplayName("Client DAO Test")
class ClientDAOTest {

    ArrayList<Client> testClients = new ArrayList<>();


    @BeforeEach
    void beforeEach() throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.reset();

        Client userTEST0 = new Client("test name 0", "Test street0");
        Client userTEST1 = new Client("test name 1", "Test street1");
        Client userTEST2 = new Client("test name 2", "Test street2");
        Client userTEST3 = new Client("test name 3", "Test street3");
        Client userTEST4 = new Client("test name 4", "Test street4");
        Client userTEST5 = new Client("test name 5", "Test street5");
        clientDAO.add(userTEST0);
        clientDAO.add(userTEST1);
        clientDAO.add(userTEST2);
        clientDAO.add(userTEST3);
        clientDAO.add(userTEST4);
        clientDAO.add(userTEST5);
        testClients.add(userTEST0);
        testClients.add(userTEST1);
        testClients.add(userTEST2);
        testClients.add(userTEST3);
        testClients.add(userTEST4);
        testClients.add(userTEST5);
    }


    @AfterEach
    void afterEach() throws IOException {
        ClientDAO clientDAO = new ClientDAO();
               clientDAO.reset();

    }


    /**
     * Method: getAll()
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test Get All")
    void testGetAll() throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> testClientsMethod = clientDAO.getAll();
        assertEquals(testClientsMethod, testClients);
    }

    /**
     * Method: get(String id)
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test Get")
    void testGet() throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        for (Client c : testClients) {
            assertEquals(c, clientDAO.get(c.getId()));
        }
    }


    /**
     * Method: add(Client client)
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test Add")
    void testAdd() throws Exception {

        ClientDAO clientDAO = new ClientDAO();

        Client userTestMethod = new Client("userTestMethod", "Test street method");
        clientDAO.add(userTestMethod);

        Client clientFromDAO = (Client) clientDAO.get(userTestMethod.getId());

        assertEquals(clientFromDAO, userTestMethod);

    }

    /**
     * Method: delete(String id)
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test Delete")
    void testDelete() throws Exception {

        ClientDAO clientDAO = new ClientDAO();
        Client clientTestMethod = new Client("clientTestMethod", "Test street method");
        clientDAO.add(clientTestMethod);
        clientDAO.delete(clientTestMethod.getId());
        Client clientFromDAO = (Client) clientDAO.get(clientTestMethod.getId());
        assertNull(clientFromDAO);

    }

    /**
     * Method: update()
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test UpdateDataBase")
    void testUpdate() throws Exception {
        // Check in WriterTest
    }

    /**
     * Method: updateAddress(String id, String address)
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test Update Address")
    void testUpdateAddress() throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        Client clientTestMethod = new Client("clientTestMethod", "Test street method");
        clientDAO.add(clientTestMethod);
        String newAddress = "newAddress";
        clientDAO.updateAddress(clientTestMethod.getId(), newAddress);
        Client updatedClient = (Client) clientDAO.get(clientTestMethod.getId());
        assertEquals(updatedClient.getAddress(), newAddress);

    }
    @org.junit.jupiter.api.Test
    @DisplayName("Test Reset")
    void testReset() throws Exception {
        ClientDAO clientDAO = new ClientDAO();

        Client userTestMethod = new Client("userTestMethod", "Test street method");
        clientDAO.add(userTestMethod);

        Client clientFromDAO = (Client) clientDAO.get(userTestMethod.getId());

        assertEquals(clientFromDAO, userTestMethod);

        clientDAO.delete(userTestMethod.getId());
        assertNull(clientDAO.get(userTestMethod.getId()));

    }
}
