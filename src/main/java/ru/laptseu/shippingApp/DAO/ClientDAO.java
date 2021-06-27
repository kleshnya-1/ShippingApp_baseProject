package ru.laptseu.shippingApp.DAO;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.Z_HZKUDA.AddressUpdatable;
import ru.laptseu.shippingApp.Z_HZKUDA.DataAccessInterface;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Product;
import ru.laptseu.shippingApp.utilites.Reader;


import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

@Log4j2
public class ClientDAO extends DAO implements DataAccessInterface, AddressUpdatable {
////add DAO
    //
    //
    //
    //


    String mainPath = getClientFilePath();

    ArrayList<Client> clients = reader.getList(mainPath, new Client());


    public ClientDAO() {
    }


    @Override
    public ArrayList getAll() {

        return clients;
    }

    @Override
    public Object get(String id) {
        try {
            return clients.stream().filter(client -> client.getId().equals(id)).findAny().get();
        }catch (NoSuchElementException e) {
            log.error("id "+id+" not found ->"+e);
        }
        return null;


    }



    @Override
    public void showAll() {
        System.out.println("\n List of clients:");
        for (Client p:clients){
            System.out.println(p.toString());
        }
    }


    public void add(Object o) {
        // spaces replaced because of executing error while reading from JSON
        // with other ways for saving data it could be easily returned
        Client client = (Client)o;
        //if (clients.stream().filter(c -> c.getId()));
        client.setName(client.getName().replace(" ", "_"));
        client.setAddress(client.getAddress().replace(" ", "_"));
        this.clients.add(client);

        writer.updateList(mainPath, clients);
        log.info(client + " added");
    }

    @Override
    public void delete(String id) {

        try {

            Client removingClient = clients.stream().filter(client -> client.getId().equals(id)).findAny().get();
            if (removingClient == null) throw new NullPointerException("id not found");
            else clients.remove(removingClient);

        } catch (NullPointerException e) {
            log.error(e);
        }


    }

    @Override
    public void updateDataBase() {
        writer.updateList(mainPath, clients);
    }

    @Override
    public void reset() {
        writer.resetList(mainPath);
        clients.clear();

    }


    @Override
    public void updateAddress(String id, String address) {
        try {

            Client updatingClient = clients.stream().filter(client -> client.getId().equals(id)).findAny().get();
            if (updatingClient == null) throw new NullPointerException("id not found");
            else{
                clients.remove(updatingClient);
                updatingClient.setAddress(address);
                clients.add(updatingClient);
                log.info("Address for client "+updatingClient+" updated");
            }

        } catch (NullPointerException e) {
            log.error(e);
        }

    }
}
