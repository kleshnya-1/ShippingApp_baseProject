package ru.laptseu.shippingApp.utilites;

import lombok.SneakyThrows;
import ru.laptseu.shippingApp.models.Product;

import java.util.Comparator;

public class SorterByPrice implements Comparator<Product> {
    int direction;

    public SorterByPrice(int direction) {
        this.direction = direction;


    }


    @Override
    public int compare(Product o1, Product o2) {

        if (o1.getPrice()>o2.getPrice()) return +1-direction*2;
        if (o1.getPrice()<=o2.getPrice()) return -1+direction*2;
        return 0;


    }
}
