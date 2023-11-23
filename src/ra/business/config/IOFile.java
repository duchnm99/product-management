package ra.business.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile{
    public static final String CATALOG_PATH = "src\\ra\\business\\data\\catalog.txt";
    public static final String PRODUCT_PATH = "src\\ra\\business\\data\\product.txt";

    public static <T> List<T> readFromFile(String path){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<T> list = new ArrayList<>();
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }

    public static <T> void writeToFile(String path, List<T> list){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
