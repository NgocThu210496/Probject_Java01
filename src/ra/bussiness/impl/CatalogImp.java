package ra.bussiness.impl;

import ra.bussiness.entity.Category;
import ra.writeRead_File.WriteReadCategory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CatalogImp {
    public static boolean createCatalog(Scanner scanner, List<Category> categoryList) {
        scanner = new Scanner(System.in);
        System.out.println("Nhập vào số thể loai cần thêm mới");
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
            boolean result = WriteReadCategory.writeCategoryToFile(categoryList);
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

    public static void statisticCatalog() {

    }

    public static void updateCatalog() {

    }
}
