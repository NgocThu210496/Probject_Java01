package ra.write_ReadObject;

import ra.entity.Category;

import java.io.*;
import java.util.List;

public class Write_ReadCategory {
    public static void writeCategoryToFile(List<Category> categories, String Path){
        File file=null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
            //1. khoi tao doi tuong file
            file=new File(Path);
            //2. khoi tao cac doi tuong outPutStream
            //2.1 khoi tao doi tuong fileOutPutStream
            fos=new FileOutputStream(file);
            //2.2 khoi tao doi tuong objectOutPutStream
            oos=new ObjectOutputStream(fos);
            //3. ghi data ra file
            oos.writeObject(categories);

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            //4. dong cac ket noi
            try {
                if (fos!=null){
                    fos.close();
                }
                if(oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void readObjectFromFile(List<Category> categories,String Path){
        File file=null;
        FileInputStream fis=null; //doc vao
        ObjectInputStream ois=null;
        try {
            //1. khoi tao doi tuong file
            file=new File(Path);
            //2. khoi tao cac doi tuong inputStream
            //2.1 khoi tao doi tuong fileInputStream
            fis=new FileInputStream(file);
            //2.2. khoi tao doi tuong ObjectInputStream
            ois=new ObjectInputStream(fis);
            //3. doc cac doi tuong tu file
            //tao bien de luu
            List<Category>categoryListRead=(List<Category>)ois.readObject(); //vi no dang kieu object nen phai add no sang kieu data category
            // in ra
            System.out.println("Danh sach danh muc doc ra tu file: ");
            for (Category ct:categoryListRead) {
                System.out.printf("Mã thể loại: %d - Tên thể loại: %s - Trạng thái: %s\n",ct.getCategoryId(),ct.getCategoryName(),ct.isCategoryStatus() ? "true" : "false");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
