package ru.laptseu.shippingApp.DAO;

import lombok.Data;
import ru.laptseu.shippingApp.utilites.Reader;
import ru.laptseu.shippingApp.utilites.Writer;

@Data
public class DAO {

    private String clientFilePath = "src/main/resources/client.json";
    private String productFilePath = "src/main/resources/product.json";
    private String shopFilePath = "src/main/resources/shop.json";
    private String orderFilePath = "src/main/resources/order.json";


    Writer writer= new Writer();
    Reader reader = new Reader();
    //MapGetter mapGetter = new MapGetter();
}
