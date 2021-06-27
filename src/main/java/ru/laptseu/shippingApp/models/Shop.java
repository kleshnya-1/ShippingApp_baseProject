package ru.laptseu.shippingApp.models;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.utilites.IdCreator;

import java.io.Serializable;

@Data
@Log4j2
public class Shop implements Serializable {
    private String id;
    private String name;
    private String address;

    public Shop(String name, String address) {
        IdCreator idCreator = new IdCreator();
        this.id = idCreator.createIdForShop(new Shop());
        this.name = name;
        this.address = address;
        log.info("Shop "+name+" created with id "+ id);
    }
    public Shop() {

    }
}
