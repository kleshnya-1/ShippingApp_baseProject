package ru.laptseu.shippingApp.DAO;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.shippingApp.Z_HZKUDA.AddressUpdatable;
import ru.laptseu.shippingApp.Z_HZKUDA.Categories;
import ru.laptseu.shippingApp.Z_HZKUDA.DataAccessInterface;
import ru.laptseu.shippingApp.models.Client;
import ru.laptseu.shippingApp.models.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

@Log4j2
public class ProductDAO extends  DAO implements DataAccessInterface {

    String mainPath = getProductFilePath();


    ArrayList<Product> products =reader.getList(mainPath, new Product() );


    @Override
    public ArrayList getAll() {
        return products;
    }

    @Override
    public Object get(String id) {
        try {
            return products.stream().filter(client -> client.getId().equals(id)).findAny().get();
        }catch (NoSuchElementException e) {
            log.error("id "+id+" not found ->"+e);
        }
        return null;
    }

    @Override
    public void showAll() {
        System.out.println("\n List of products:");
        for (Product p:products){
            System.out.println(p.toString());
        }
    }

    @Override
    public void delete(String id) {
        try {

            Product removingProduct = products.stream().filter(p -> p.getId().equals(id)).findAny().get();
            if (removingProduct == null) throw new NullPointerException("id not found");
            else products.remove(removingProduct);

        } catch (NullPointerException e) {
            log.error(e);
        }
    }

    @Override
    public void updateDataBase() {
        writer.updateList(mainPath,products);
    }
    @Override
    public void reset() {
        writer.resetList(mainPath);
        products =reader.getList(mainPath, new Product() );
        //or resetting ArrayList is more correct way?
    }


    public void add(Object o)  {
        // spaces replaced because of executing error while reading from JSON
        // with other ways for saving data it could be easily returned
        Product product = (Product)o;
        product.setName(product.getName().replace(" ","_"));

        this.products.add(product);
        writer.updateList(mainPath,products);
    }

    public void updateProduct(String id, String name, int quantity, double price, String shopId, Categories... categories){
        try {

            Product removingProduct = products.stream().filter(p -> p.getId().equals(id)).findAny().get();
            if (removingProduct == null) throw new NullPointerException("id not found");

            if (name!=null)removingProduct.setName(name);
            if (quantity!=-0)removingProduct.setQuantity(quantity);
            if (price!=-0)removingProduct.setPrice(price);
            if (shopId!=null)removingProduct.setShopId(shopId);
            if (categories.length!=0) {
                ArrayList categoriesList = new ArrayList();
                for (int i = 0; i<categories.length; i++){
                    categoriesList.add(categories[i]);
                }
                removingProduct.setCategories(categoriesList);
            }

        } catch (NullPointerException e) {
            log.error(e);
        }



    }



}
