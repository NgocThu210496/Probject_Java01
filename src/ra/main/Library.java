package ra.main;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.presentation.BookManagement;
import ra.presentation.CatagoryManagement;
import ra.write_ReadObject.WriteReadCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static List<Category> categoriesList=new ArrayList<>();
    static {
        categoriesList.add(new Category(1,"Sach lop 1",true));
        categoriesList.add(new Category(2,"Sach lop 2",false));
        categoriesList.add(new Category(3,"Sach lop 3",true));

    }
    public static List<Book> books= new ArrayList<>();
    public static void main(String[] args) {
       // WriteReadCategory.writeCategoryToFile(categoriesList);
       categoriesList= WriteReadCategory.readCategoryFromFile();
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("=========== QUẢN LÝ THƯ VIỆN ============");
            System.out.println("1. Quản lý Thể loại");
            System.out.println("2. Quản lý Sách");
            System.out.println("3. Thoát");
            System.out.println("--------------------------------------");
            System.out.println("Lựa chọn của bạn: ");

            int choice=Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("1. Quản lý Thể loại");
                    CatagoryManagement.showCategoriesMenu(categoriesList,"category.txt");
                    break;
                case 2:
                    System.out.println("2. Quản lý Sách");
                    BookManagement.showBooksMenu();
                    break;
                case 3:
                    System.exit(0);
            }

        }while (true);

    }


}
