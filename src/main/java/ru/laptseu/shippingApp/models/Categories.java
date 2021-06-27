package ru.laptseu.shippingApp.models;

public enum Categories {
        ELECTRONICS(0),
    CLOTHES(1),
    FOOD(2),
    DRINKS(3),
    PETS(4),
    MATERIALS(5);


    public final int value;

    Categories(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
