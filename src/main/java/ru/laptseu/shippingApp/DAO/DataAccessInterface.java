package ru.laptseu.shippingApp.DAO;

import ru.laptseu.shippingApp.models.Client;

import java.util.ArrayList;
import java.util.Map;

public interface DataAccessInterface {


    public void add(Object o) ;
    public ArrayList getAll();
    public Object get(String id);
    public void showAll();
    public void delete(String id);
    public void updateDataBase();
    public void reset();



}
