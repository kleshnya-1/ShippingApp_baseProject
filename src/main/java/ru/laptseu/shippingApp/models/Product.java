package ru.laptseu.shippingApp.models;

import lombok.Data;
import ru.laptseu.shippingApp.Z_HZKUDA.Categories;
import ru.laptseu.shippingApp.utilites.IdCreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product   implements Serializable  {
    private String id;
    private String shopId;
    private String name;
    private int quantity;
    private double price;
    private List<Categories> categories = new ArrayList<>();


    public Product(String name, int quantity, double price, String shopId, Categories ... categories) {
        IdCreator idCreator = new IdCreator();
        this.id = idCreator.createIdForProduct(new Product());
        this.shopId = shopId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

        for (int i = 0; i<categories.length; i++){
            this.categories.add(categories[i]);
        }

    }
    public Product(Product choosedProduct){

        this.id = choosedProduct.getId();
        this.shopId = choosedProduct.getShopId();
        this.name = choosedProduct.getName();
        this.quantity = choosedProduct.quantity;
        this.price = choosedProduct.getPrice();
        this.categories = choosedProduct.getCategories();


    };

    public Product( ){

    };

    public String toStringRepresentation(){
        return String.format("Product: %5s      Price:%5s      Quantity in the shop: %3s",name,price, quantity);
    }


}
