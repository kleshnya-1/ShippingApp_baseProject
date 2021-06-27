package ru.laptseu.shippingApp.services;

import ru.laptseu.shippingApp.DAO.ProductDAO;
import ru.laptseu.shippingApp.models.Categories;
import ru.laptseu.shippingApp.models.Product;
import ru.laptseu.shippingApp.utilites.SorterByPrice;

import java.util.ArrayList;
import java.util.Collections;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();

    public ArrayList<Product> getSortedByPrice(int direction) {
        // direction case 0 -> from LOW to HIGH
        // direction case 1 -> from HIGH to LOW
        ArrayList<Product> listForReturning = (ArrayList<Product>) productDAO.getAll().clone();
        Collections.sort(listForReturning, new SorterByPrice(direction));
        return listForReturning;
    }

    public void printSortedByPrice(int direction){
        System.out.println("\n List of products sorted by price:");
        for (Product p:getSortedByPrice(direction)){
            System.out.println(p);
        }
    }

    public ArrayList findByAttribute(String name, double price){
        //short attribute list
        ArrayList<Product> list = (ArrayList<Product>) productDAO.getAll().clone();
        ArrayList<Product> listForReturning =new ArrayList<>();

       for (Product o:list){
           Product p = (Product)o;
           if (name!=null&& p.getName().equals(name)) listForReturning.add(p);
           if (price!=-1&& p.getPrice()==price) listForReturning.add(p);
       }
       return listForReturning;
    }

    public void printSortedByAttribute(String name, double price){
        ArrayList list = findByAttribute(name,price);
        System.out.println("With attributes found:");
        for (Object p:list){
            System.out.println(p);
        }
    }

    public ArrayList<Product> getProductsInCategories(Categories... categories){
        ArrayList<Product> listForReturning = new ArrayList<>();
        ArrayList<Product> list = (ArrayList<Product>) productDAO.getAll().clone();

        for (Categories c: categories){
            for (Product p:list){
                if (!listForReturning.contains(p)&&p.getCategories().contains(c))
                    listForReturning.add(p);
            }
        }
       return listForReturning;
    }

    public void printProductsInCategories(Categories... categories){
        ArrayList <Product> l = getProductsInCategories(categories);
        System.out.println("Product(s) found in "+ categories.length+" category(-es): "+ l.size());
        for (Product p:l){
            System.out.println(p);
        }
        //System.out.println(getProductsInCategories(categories));
    }
}
