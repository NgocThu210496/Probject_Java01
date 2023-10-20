package ra.presentation;

import ra.bussiness.entity.Category;
import ra.bussiness.impl.CatalogImp;
import ra.writeRead_File.WriteReadCategory;

import java.util.List;
import java.util.Scanner;

public class CatagoryManagement {
    public static void showCategoriesMenu(List<Category> categoryList){
        Scanner scanner= new Scanner(System.in);
        categoryList = WriteReadCategory.readCategoryFromFile();
        boolean exitMenuCatagory=true;
        do {
            System.out.println("==============QUẢN LÝ THỂ LOẠI ==============");
            System.out.println("1. Thêm mới thể loại");
            System.out.println("2. Hiển thị danh sách theo tên A–Z");
            System.out.println("3. Thống kê thể loại và số sách có trong mỗi thể loại");
            System.out.println("4. Cập nhật thể loại");
            System.out.println("5. Xóa thể loại");
            System.out.println("6. Quay lại");
            System.out.println("-----------------------------------------------");
            System.out.println("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.next());

            switch (choice){
                case 1:
                    System.out.println("1. Thêm mới thể loại");
                    CatalogImp.createCatalog(scanner,categoryList);
                    break;
                case 2:
                    System.out.println("2. Hiển thị danh sách theo tên A–Z");
                    CatalogImp.sortCatalogName(categoryList);
                    break;
                case 3:
                    System.out.println("3. Thống kê thể loại và số sách có trong mỗi thể loại");
                    break;
                case 4:
                    System.out.println("4. Cập nhật thể loại");

                    break;
                case 5:
                    System.out.println("5. Xóa thể loại");
                    break;
                case 6:
                    exitMenuCatagory=false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }

        }while (exitMenuCatagory);
    }
}
