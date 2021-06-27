package ru.laptseu.shippingApp.core;

import org.junit.Test;
import ru.laptseu.shippingApp.DAO.ClientDAO;
import ru.laptseu.shippingApp.DAO.OrderDAO;
import ru.laptseu.shippingApp.DAO.ProductDAO;
import ru.laptseu.shippingApp.DAO.ShopDAO;
import ru.laptseu.shippingApp.models.*;
import ru.laptseu.shippingApp.services.ProductService;

import java.util.Scanner;

public class Demo {


    ClientDAO clientDAO = new ClientDAO();
    ShopDAO shopDAO = new ShopDAO();
    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();
    ProductService productService = new ProductService();

    //creating models
    Client clientTEST0 = new Client("test name 0", "Test street0");
    Client clientTEST1 = new Client("test name 1", "Test street1");
    Client clientTEST2 = new Client("test name 2", "Test street2");
    Client clientTEST3 = new Client("test name 3", "Test street3");
    Client clientTEST4 = new Client("test name 4", "Test street4");
    Client clientTEST5 = new Client("test name 5", "Test street5");
    Shop testShop0 = new Shop("testShop0", "Boulevard of testing building 0");
    Shop testShop1 = new Shop("testShop1", "Boulevard of testing building 1");
    Product testProduct0 = new Product("beer", 5, 0.63, testShop0.getId(), Categories.FOOD, Categories.DRINKS);
    Product testProduct1 = new Product("dogs Food", 4, 0.93, testShop0.getId(), Categories.FOOD, Categories.PETS);
    Product testProduct2 = new Product("jean", 4, 0.93, testShop0.getId(), Categories.CLOTHES);
    Product testProduct3 = new Product("t-shirt", 30, 10, testShop1.getId(), Categories.CLOTHES);
    Product testProduct4 = new Product("airpods", 20, 1, testShop1.getId(), Categories.ELECTRONICS);
    Product testProduct5 = new Product("cement", 10, 5, testShop1.getId(), Categories.MATERIALS);

    Order testOrder0;
    Order testOrder1;
    Order testOrder2;

    @Test
    public void addToJSON() {


        //adding to base (JSON file)
        clientDAO.add(clientTEST0);
        clientDAO.add(clientTEST1);
        clientDAO.add(clientTEST2);
        clientDAO.add(clientTEST3);
        clientDAO.add(clientTEST4);
        clientDAO.add(clientTEST5);
        shopDAO.add(testShop0);
        shopDAO.add(testShop1);
        productDAO.add(testProduct0);
        productDAO.add(testProduct1);
        productDAO.add(testProduct2);
        productDAO.add(testProduct3);
        productDAO.add(testProduct4);
        productDAO.add(testProduct5);

        //creating models
        testOrder0 = new Order(clientTEST0.getId(),
                "clientAddressTest", "note for test",
                new String[]{testProduct5.getId(), testProduct4.getId()},
                new int[]{1, 1});
        testOrder1 = new Order(clientTEST1.getId(),
                "clientAddressTest", "note for test",
                new String[]{testProduct3.getId(), testProduct2.getId()},
                new int[]{2, 2});
        testOrder2 = new Order(clientTEST1.getId(),
                "clientAddressTest", "note for test",
                new String[]{testProduct1.getId()},
                new int[]{1});

//adding to base (JSON file)
        orderDAO.add(testOrder2);
        orderDAO.add(testOrder1);
        orderDAO.add(testOrder0);

    }

    @Test
    public void showProducts() {
        productDAO.showAll();
    }

    @Test
    //·       сортировка товаров по цене
    public void sortProducts() {
        productService.printSortedByPrice(0);
        System.out.println();
        productService.printSortedByPrice(1);
    }

    @Test
    //·       категоризация товаров. Один товар может относиться к нескольким категориям
    //·       просмотр товаров в заданных категориях
    public void showInCategories() {
        productService.printProductsInCategories(Categories.FOOD);
        System.out.println();
        productService.printProductsInCategories(Categories.FOOD, Categories.CLOTHES);
    }


    @Test
//оформление заказа, с указанием данных клиента и данных,
// связанных с доставкой.
    public void orderProductById() {
        orderDAO.add(new Order(clientTEST5.getId(), "newadress",
                null, new String[]{"typeIdHere"}, new int[]{2}));
    }

    @Test
    //·       поиск товаров по одному или нескольким атрибутам
    public void sortByAttribute() {
        productService.printSortedByAttribute(null, 0.63);
    }

    @Test
    //·       добавление/удаление/редактирование информации о товарах
    public void updateProduct() {
        Product p = (Product) productDAO.getAll().get(0);
        productDAO.updateProduct(p.getId(), "updatedName", -1, -1, null);
        Product newName = (Product) productDAO.get(testProduct1.getId());
    }


    @Test
    public void resetBases() {
        clientDAO.reset();
        shopDAO.reset();
        productDAO.reset();
        orderDAO.reset();
    }

    //·       регистрация клиентов, с возможностью удаления и изменения регистрационных данных
//·       регистрация магазинов, с возможностью удаления и изменения регистрационных данных
    //DAO classes supports


}
