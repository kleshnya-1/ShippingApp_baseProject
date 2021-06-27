package ru.laptseu.shippingApp.utilites;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.DAO.OrderDAO;
import ru.laptseu.shippingApp.DAO.ProductDAO;
import ru.laptseu.shippingApp.DAO.ShopDAO;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Order;
import ru.laptseu.shippingApp.models.Product;
import ru.laptseu.shippingApp.models.Shop;

import java.security.SecureRandom;

@Log4j2
public class IdCreator {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    private final int idLengthProduct = 8;
    private final int idLengthShop = 5;
    private final int idLengthClient = 3;
    private final int idLengthOrder = 5;
    private final String idStartProduct = "pr_";
    private final String idStartShop = "sh_";
    private final String idStartClient = "cl_";
    private final String idStartOrder = "or_";


    public String createId(int length) {

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();

    }

    public boolean checkId(String id, Object tClass) {
        //return TRUE if ID exists in base
// i dont like how this method works. it could be a bottleneck. but i dont
// think that i should create MODEL and extend my models from if only for one method.
//am i right?

        boolean decision = true;

        if (tClass.getClass().equals(Client.class)) {
            ClientDAO clientDAO = new ClientDAO();
            log.info("client");
            if (clientDAO.get(id) == null) {
                log.info("null");
                decision = false;
            }
        }

        if (tClass.getClass().equals(Order.class)) {
            OrderDAO dao = new OrderDAO();
            log.info("order");
            if (dao.get(id) == null) decision = false;
        }

        if (tClass.getClass().equals(Product.class)) {
            ProductDAO dao = new ProductDAO();
            log.info("prod");
            if (dao.get(id) == null) decision = false;
        }

        if (tClass.getClass().equals(Shop.class)) {
            ShopDAO dao = new ShopDAO();
            log.info("shop");
            if (dao.get(id) == null) decision = false;
        }


        log.info("id \"" + id + "\" checked in " + tClass.getClass() + ". Is exisit: "+decision);
        return decision;

    }

    public String createIdForProduct(Object classObject) {
        String createdId = idStartProduct + createId(idLengthProduct);

        while (checkId(createdId, classObject) == true) {
            createdId = idStartProduct + createId(idLengthProduct);
        }
        return createdId;
    }

    public String createIdForShop(Object classObject) {
        String createdId = idStartShop + createId(idLengthShop);
        while (checkId(createdId, classObject) == true) {
            createdId = idStartShop + createId(idLengthShop);
        }
        return createdId;
    }

    public String createIdForClient(Object classObject) {
        String createdId = idStartClient + createId(idLengthClient);
        while (checkId(createdId, classObject) == true) {
            createdId = idStartClient + createId(idLengthClient);
        }
        return createdId;
    }

    public String createIdForOrder(Object classObject) {
        String createdId = idStartOrder + createId(idLengthOrder);
        while (checkId(createdId, classObject) == true) {
            createdId = idStartOrder + createId(idLengthOrder);
        }
        return createdId;
    }


}
