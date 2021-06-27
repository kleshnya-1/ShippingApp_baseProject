package ru.laptseu.shippingApp.DAO;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.Z_HZKUDA.AddressUpdatable;
import ru.laptseu.shippingApp.Z_HZKUDA.DataAccessInterface;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Product;
import ru.laptseu.shippingApp.models.Shop;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

@Log4j2
public class ShopDAO extends DAO implements DataAccessInterface, AddressUpdatable {

    String mainPath = getShopFilePath();

    ArrayList<Shop> shops = reader.getList(mainPath, new Shop());


    @Override
    public ArrayList getAll() {
        return shops;
    }

    @Override
    public Object get(String id) {
        try {
            return shops.stream().filter(s -> s.getId().equals(id)).findAny().get();
        } catch (NoSuchElementException e) {
            log.error("id " + id + " not found ->" + e);
        }
        return null;
    }

    @Override
    public void showAll() {
        System.out.println("\n List of shops:");
        for (Shop p : shops) {
            System.out.println(p.toString());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Shop removingShop = shops.stream().filter(p -> p.getId().equals(id)).findAny().get();
            shops.remove(removingShop);
        } catch (NoSuchElementException e) {
            log.error("id " + id + " not found ->" + e);
        }
    }

    @Override
    public void updateDataBase() {
        writer.updateList(mainPath, shops);
    }

    public void add(Object o) {
        // spaces replaced because of executing error while reading from JSON
        // with other ways for saving data it could be easily returned
        Shop shop = (Shop)o;
        shop.setName(shop.getName().replace(" ", "_"));
        shop.setAddress(shop.getAddress().replace(" ", "_"));
        this.shops.add(shop);
        writer.updateList(mainPath, shops);
    }

    @Override
    public void updateAddress(String id, String address) {
        try {

            Shop updatingShop = shops.stream().filter(c -> c.getId().equals(id)).findAny().get();
            if (updatingShop == null) throw new NullPointerException("id not found");
            else{
                shops.remove(updatingShop);
                updatingShop.setAddress(address);
                shops.add(updatingShop);
                log.info("Address for shop "+updatingShop+" updated");
            }

        } catch (NullPointerException e) {
            log.error(e);
        }

    }

    @Override
    public void reset() {
        writer.resetList(mainPath);
        shops.clear();
    }
}
