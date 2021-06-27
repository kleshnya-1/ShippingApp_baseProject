package ru.laptseu.shippingApp.core;


import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.DAO.ProductDAO;
import ru.laptseu.shippingApp.DAO.ShopDAO;
import ru.laptseu.shippingApp.models.Categories;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Product;
import ru.laptseu.shippingApp.models.Shop;

import java.util.Scanner;

@Log4j2
public class ControllerTerminal {
    //for demonstrating work with terminal

    Scanner scanner = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);


    public void createNewShopFromCommandLine(){
        ShopDAO shopDAO = new ShopDAO();

       try {
           System.out.println("Shop name?");
           String name = scanner.nextLine();
           if (name.isEmpty()) throw new NullPointerException("empty name");

           System.out.println("Shop address?");
           String address = scanner.nextLine();
           if (address.isEmpty()) throw new NullPointerException("empty address");


           Shop newShop = new Shop(name, address);
           shopDAO.add(newShop);
           System.out.println("Shop "+ newShop + " created with id" + newShop.getId());
           log.info("Shop "+ newShop + " created with id" + newShop.getId());
       } catch (NullPointerException e){
           log.error(e.getMessage());
        }


    }

    public void createNewClientFromCommandLine(){
        ClientDAO clientDAO = new ClientDAO();

        try {
            System.out.println("Client name?");
            String name = scanner.nextLine();
            if (name.isEmpty()) throw new NullPointerException("empty name");

            System.out.println("Client address?");
            String address = scanner.nextLine();
            if (address.isEmpty()) throw new NullPointerException("empty address");


            Client newClient = new Client(name, address);
            clientDAO.add(newClient);
            System.out.println("Client "+ newClient + " created with id" + newClient.getId());
            log.info("Client "+ newClient + " created with id" + newClient.getId());
        } catch (NullPointerException e){
            log.error(e.getMessage());
        }


    }

    public void createNewProductFromCommandLine(){
        ProductDAO productDAO = new ProductDAO();

        try {
            System.out.println("Product name?");
            String name = scanner.nextLine();
            if (name.isEmpty()) throw new NullPointerException("empty name");

            System.out.println("Product quantity?");
            int quantity = scanner.nextInt();


            System.out.println("Product price?");
            double price = scanner.nextDouble();


            System.out.println("Product shop Id?");
            String shopId = scanner2.nextLine();
            if (shopId.isEmpty()) throw new NullPointerException("empty Id");

       System.out.println("Product categories (in line as \"0, 1, 2...\")");
            Categories[] categories = Categories.values();
            for (Categories c:categories){
                System.out.println(c.ordinal()+" for "+c);
            }
            String strinlOfInts = scanner2.nextLine();
            String[] intMassive = strinlOfInts.split(", ");

            int [] ints = new int[intMassive.length];
            Categories [] categories1 = new Categories[intMassive.length];

            for(int i =0; i<intMassive.length;i++){
                ints [i]= Integer.parseInt(intMassive[i]);
                categories1 [i] = Categories.values()[Integer.parseInt(intMassive[i])];
            }






            Product newProduct = new Product(name, quantity, price, shopId, categories1 );
            productDAO.add(newProduct);
            System.out.println(newProduct + " created with id" + newProduct.getId()+" and shopID " +newProduct.getShopId());
            log.info( newProduct + " created with id" + newProduct.getId()+" and shopID " +newProduct.getShopId());
        } catch (NullPointerException e){
            log.error(e.getMessage());
        }catch (NumberFormatException e){
            log.error(e.getMessage());
        }catch (ArrayIndexOutOfBoundsException e){
            log.error(e.getMessage()+" wrong number for category");
        }



    }


}
