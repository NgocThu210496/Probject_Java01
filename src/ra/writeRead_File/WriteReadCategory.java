package ra.writeRead_File;

import ra.bussiness.data.DataURL;
import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;

import java.io.*;
import java.util.List;

public class WriteReadCategory {
    public static boolean writeCategoryToFile(List<Category> categoriesList, List<Book> bookList) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
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

        } catch (Exception ex1) {
            returnData = false;
            System.err.println("Lỗi khi ghi dữ liêu ra tệp" + ex1.getMessage());
        } finally {
            //4. dong cac ket noi
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex2) {
                System.err.println("Lỗi khi đóng tài nguyên" + ex2.getMessage());
            }
        }
        return returnData;
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
            } else {
                System.err.println("File không tồn tại!");
            }
            //3. doc cac doi tuong tu file
            //tao bien de luu
            categoriesList = (List<Category>) ois.readObject(); //vi no dang kieu object nen phai add  sang kieu data category

        } catch (IOException ex) {
            System.err.println("Có lỗi khi đọc hoặc ghi dữ liệu từ tệp: " + ex.getMessage());
        }
        catch (ClassNotFoundException ex2){
            System.err.println("Lỗi xảy ra khi tìm kiếm lớp không tồn tại: " + ex2.getMessage());
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            }
            catch (IOException ex3) {
                System.err.println("Lỗi khi đóng tài nguyên: " + ex3.getMessage());
            }
        }
        return categoriesList;
    }
}
