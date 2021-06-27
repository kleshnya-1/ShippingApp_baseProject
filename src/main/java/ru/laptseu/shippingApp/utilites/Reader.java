package ru.laptseu.shippingApp.utilites;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.ArrayList;

@Log4j2
public class Reader {
    Gson gson = new Gson();
    public ArrayList getList(String filePath, Object o) {

        ArrayList l = new ArrayList<>();
        ArrayList l2 = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            l = gson.fromJson((String) ois.readObject(), ArrayList.class);
            for (int i = 0; i < l.size(); i++) {
                l2.add(gson.fromJson(l.get(i).toString().trim(), o.getClass()));
            }
            log.info("List with " + l2.size() + " elements received from " + filePath + " class " + o.getClass());
            ois.close();
        } catch (ClassNotFoundException e) {
            log.error("Class not found");
        } catch (IOException e) {
            log.error(e + " in reading " + filePath);
        }
        return l2;
    }
}


