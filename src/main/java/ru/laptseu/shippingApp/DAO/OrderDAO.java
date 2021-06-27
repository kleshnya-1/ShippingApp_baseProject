package ru.laptseu.shippingApp.DAO;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.Z_HZKUDA.DataAccessInterface;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Order;
import ru.laptseu.shippingApp.models.Shop;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Log4j2
public class OrderDAO extends DAO implements DataAccessInterface {

    String mainPath = getOrderFilePath();

    ArrayList <Order> orders = reader.getList(mainPath, new Order());
@Override
    public void add(Object o) {
        // spaces replaced because of executing error while reading from JSON
        // with other ways for saving data it could be easily returned
    Order order = (Order)o;
if (order.getNote() != null)        order.setNote(order.getNote().replace(" ", "_"));
        order.setAddress(order.getAddress().replace(" ", "_"));
        this.orders.add(order);

        writer.updateList(mainPath, orders);
        log.info(order + " added");
    }
    @Override
    public ArrayList getAll() {
        return orders;
    }

    @Override
    public Object get(String id) {
        try {
            return orders.stream().filter(client -> client.getId().equals(id)).findAny().get();
        }catch (NoSuchElementException e) {
            log.error("id "+id+" not found ->"+e);
        }
        return null;
    }

    @Override
    public void showAll() {

        System.out.println("\n List of orders:");
        for (Order p:orders){
            System.out.println(p.toString());
        }

    }

    @Override
    public void delete(String id) {
        try {
            Order removingOrder = orders.stream().filter(p -> p.getId().equals(id)).findAny().get();
            orders.remove(removingOrder);
        } catch (NoSuchElementException e) {
            log.error("id " + id + " not found ->" + e);
        }

    }

    @Override
    public void updateDataBase() {
        writer.updateList(mainPath, orders);
    }

    @Override
    public void reset() {
        writer.resetList(mainPath);
        orders.clear();
    }


}
