package ru.laptseu.shippingApp.core;

import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.models.Client;

public class Core {


    ClientDAO clientDAO = new ClientDAO();


    public void addClient(Client client){

        clientDAO.add(client);
    }
}
