package ra.writeRead_File;

import ra.bussiness.config.DataURL;
import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;

import java.io.*;
import java.util.List;

public class WriteReadBook {
    public static boolean writeBookToFile(List<Book> bookList, List<Category>categoryList){
        File file=null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        boolean returnData=true;
        try {
        //1. khoi tao doi tuong
            file=new File(DataURL.URL_BOOK_FILE);
            fos=new FileOutputStream(file);
            oos=new ObjectOutputStream(fos);
            //ghi data ra file
            oos.writeObject(bookList);
        }catch (Exception ex){
            System.err.println("Lỗi khi ghi dữ liêu ra tệp" + ex.getMessage());
        }finally {
            //dong cac khoi
            try {
                if(fos!=null){
                    fos.close();
                }
                if (oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                System.err.println("Lỗi khi đóng tài nguyên" + e.getMessage());
            }
        }
        return returnData;
    }
    public static List<Book> readBookFromFile(){
        List<Book>bookList=null;
        File file=new File(DataURL.URL_BOOK_FILE);
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try {
            if(file.exists()){
                fis= new FileInputStream(file);
                ois= new ObjectInputStream(fis);
            }else {
                System.err.println("File không tồn tại!");
            }
            //doc cac doi tuon gtu file va luu vao list
            bookList=(List<Book>)ois.readObject();

        }catch (IOException ex){
            System.err.println("Có lỗi khi đọc hoặc ghi dữ liệu từ tệp: " + ex.getMessage());
        } catch (ClassNotFoundException ex2) {
            System.err.println("Lỗi xảy ra khi tìm kiếm lớp không tồn tại: " + ex2.getMessage());
        } finally {
            try {
                if (fis!=null){
                    fis.close();
                }
                if(ois!=null){
                    ois.close();
                }
            } catch (IOException ex3) {
                System.err.println("Lỗi khi đóng tài nguyên: " + ex3.getMessage());
            }
        }
        return bookList;
    }
}
