package ra.bussiness.impl;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.writeRead_File.WriteReadCategory;

import java.util.*;

public class CatalogImp {
    public static boolean createCatalog(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        scanner = new Scanner(System.in);
        System.out.print("Nhập vào số thể loai cần thêm mới");
        int n = Integer.parseInt(scanner.nextLine());
        boolean addedSuccessfully = false; //kiểm tra đã thêm thành công chưa
        for (int i = 0; i < n; i++) {
            Category category = new Category();
            category.inputDataCategory(scanner, categoryList);
            categoryList.add(category);
            addedSuccessfully = true;
        }
        if (addedSuccessfully) {
            //ghi danh sach danh muc vao file .txt
            boolean result = WriteReadCategory.writeCategoryToFile(categoryList,bookList);
            if (result) {
                System.out.println("Đã thêm danh mục thành công và lưu vào file");
            } else {
                System.err.println("Không thể lưu dữ liệu vào file.");
            }
        }
        return addedSuccessfully;
    }

    public static void displayCatalog(List<Category> categoryList) {
        for (Category ct : categoryList) {
            ct.output();
        }
    }

    public static void sortCatalogName(List<Category> categoryList) {
        Collections.sort(categoryList, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }
        });
        displayCatalog(categoryList);
    }

    public static void statsCatalog(List<Category> categoryList,List<Book> bookList) {
        //tao doi tuong map (lưu trữ dữ liệu dưới dạng cặp key-value)
        //Map để lưu trữ số lượng sách trong từng thể loại.
        Map<String,Integer> mapStatsCatalog = new HashMap<>();

        for (Category ct:categoryList) { //lấy ra từng thể loại
            int count=0;
            for (Book b:bookList) { //duyệt qua từng cuốn sách trong danh sách sách
                if(b.getCategoryId()== ct.getCategoryId()){ //so sánh categoryId
                    count ++;
                    break;
                }
            }
            mapStatsCatalog.put(ct.getCategoryName(),count);
        }
        System.out.println("Thống kê thể loại và số sách có trong mỗi thể loại:");
         for (int i=0; i< categoryList.size();i++){
             int categoryId = categoryList.get(i).getCategoryId();
             String catalogName= categoryList.get(i).getCategoryName();
             int countBook=mapStatsCatalog.get(catalogName);

             System.out.println("Mã: " + categoryId +  " - " + catalogName + " : " + countBook + " cuốn");

         }
    }

    public static void updateCatalog(Scanner scanner,List<Category> categoryList) {
        int updateId = 0;
        boolean validId = false;
        boolean found = false;

        System.out.println("Nhập vào mã id cần cập nhật: ");
        scanner.nextLine();
        do {
            try {
                updateId = Integer.parseInt(scanner.nextLine());
                validId = true;
            } catch (NumberFormatException e) {
                System.err.println("Mã thể loại phải là một số nguyên. Vui lòng nhập lại!");
            }
        } while (!validId);

        for (Category category : categoryList) {
            // lấy ra một đối tượng sinh viên từ danh sách studentList tại vị trí i
            category = categoryList.get(updateId);
            if (category.getCategoryId() == updateId) {
                found = true;

                System.out.println("------------------Cập nhập thông tin thể loại sách: -----------------");
                System.out.println("Tên thể loại: ");



                System.out.println("Cập nhật thông tin thể loại thành công.");
                break;
            }
        }
        if (!found) {
            System.err.println("Không tìm thấy mã thể loại để cập nhật.");
        }


    }

}
