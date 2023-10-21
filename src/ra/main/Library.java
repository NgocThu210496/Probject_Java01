package ra.main;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.presentation.BookManagement;
import ra.presentation.CatagoryManagement;
import ra.writeRead_File.WriteReadBook;
import ra.writeRead_File.WriteReadCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static List<Category> categoriesList;
    static {
        categoriesList = WriteReadCategory.readCategoryFromFile();
        categoriesList = (categoriesList != null) ? categoriesList : new ArrayList<>();
    }
    public static List<Book> bookList;
    static{
        bookList = WriteReadBook.readBookFromFile();
        bookList=(bookList!=null)? bookList:new ArrayList<>();
    }

//    static {
//        categoriesList.add(new Category(1,"Sach lop 1",true));
//        categoriesList.add(new Category(2,"Sach lop 2",false));
//        categoriesList.add(new Category(3,"Sach lop 3",true));
//
//    }

//    static {
//        bookList.add(new Book("Bs01","Sách Địa lý","Nguyễn van A", "Hoa sen",1996,"Sách cho học sinh",1));
//        bookList.add(new Book("Bs02","Sách Lịch sử","Nguyễn van B", "Hoa sen",1991,"Sách cho học sinh",2));
//        bookList.add(new Book("Bs02","Sách Công nghệ thông tin","Nguyễn van C", "Hoa sen",1992,"Sách cho học sinh",3));
//        WriteReadBook.writeBookToFile(bookList,categoriesList);
//    }



    public static void main(String[] args) {

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
                    CatagoryManagement.showCategoriesMenu(categoriesList,bookList);
                    break;
                case 2:
                    System.out.println("2. Quản lý Sách");
                    BookManagement.showBooksMenu(categoriesList,bookList);
                    break;
                case 3:
                    System.exit(0);
            }

        }while (true);

    }


}
