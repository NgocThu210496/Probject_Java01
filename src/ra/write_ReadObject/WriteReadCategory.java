package ra.write_ReadObject;

import ra.bussiness.data.DataURL;
import ra.bussiness.entity.Category;

import java.io.*;
import java.util.List;

public class WriteReadCategory {
    public static void writeCategoryToFile(List<Category> categoriesList) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //1. khoi tao doi tuong file
            file = new File(DataURL.URL_CATALOG_FILE);
            //2. khoi tao cac doi tuong outPutStream
            //2.1 khoi tao doi tuong fileOutPutStream
            fos = new FileOutputStream(file);
            //2.2 khoi tao doi tuong objectOutPutStream
            oos = new ObjectOutputStream(fos);
            //3. ghi data ra file
            oos.writeObject(categoriesList);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //4. dong cac ket noi
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Category> readCategoryFromFile() {
        List<Category> categoriesList = null; //chua co data
        File file = new File(DataURL.URL_CATALOG_FILE); //1. khoi tao doi tuong file
        FileInputStream fis = null; //doc vao
        ObjectInputStream ois = null;
        try {


            if (file.exists()) {
                //2. khoi tao cac doi tuong inputStream
                //2.1 khoi tao doi tuong fileInputStream
                fis = new FileInputStream(file);
                //2.2. khoi tao doi tuong ObjectInputStream
                ois = new ObjectInputStream(fis);
            }
            //3. doc cac doi tuong tu file
            //tao bien de luu
            categoriesList = (List<Category>)ois.readObject(); //vi no dang kieu object nen phai add no sang kieu data category
            // in ra
            System.out.println("Danh sách danh mục đọc ra từ file:  ");
            for (Category ct : categoriesList) {
              ct.output();
            }
        } catch (Exception ex) {
            System.err.println("File không tồn tại!");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return categoriesList;
        }

    }
}
