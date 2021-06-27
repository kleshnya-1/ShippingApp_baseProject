package ru.laptseu.shippingApp.models;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.utilites.IdCreator;

import java.io.Serializable;

@Data
@Log4j2
public class Client  implements Serializable {
    private String id;
    private String name;
    private String address;



    public Client(String name, String address) {
        IdCreator idCreator = new IdCreator();
        this.id = idCreator.createIdForClient(new Client());
        this.name= name;
        this.address= address;
        log.info("Client "+name+" created with id "+ id);
    }
    public Client(){

    };




}
