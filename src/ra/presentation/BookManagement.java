package ra.presentation;

import ra.bussiness.config.Color;
import ra.bussiness.config.UserChoice;
import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.bussiness.impl.BookImp;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void showBooksMenu(List<Category> categoryList, List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        boolean exitMenuBook = true;
        BookImp.displayBook(bookList);
        do {
//            for (Book b : bookList) {
//                b.output();
//            }
          //  BookImp.displayBook(bookList);
            System.out.println(Color.TEXT_BLUE+".===========================================================."+Color.TEXT_RESET);
            System.out.println(Color.TEXT_BLUE+"|                        QUẢN LÝ SÁCH                       |"+Color.TEXT_RESET);
            System.out.println(Color.TEXT_BLUE+"|===========================================================|"+Color.TEXT_RESET);
            System.out.println("|               1. Thêm mới sách                            |");
            System.out.println("|               2. Cập nhật thông tin sách                  |");
            System.out.println("|               3. Xóa sách                                 |");
            System.out.println("|               4. Tìm kiếm sách                            |");
            System.out.println("|               5. Hiển thị danh sách theo nhóm thể loại    |");
            System.out.println("|               6. Quay lại                                 |");
            System.out.println(Color.TEXT_BLUE+"|===========================================================|"+Color.TEXT_RESET);
            int choice;
                choice = UserChoice.getUserChoice(scanner);

                switch (choice) {
                    case 1:
                        System.out.println("1. Thêm mới thể loại");
                        BookImp.createBook(scanner, categoryList, bookList);
                        break;
                    case 2:
                        System.out.println("2. Cập nhật thông tin sách");
                        BookImp.updateBook(scanner, categoryList, bookList);
                       // BookImp.displayBook(bookList);
                        break;
                    case 3:
                        System.out.println("3.Xóa sách");
                        BookImp.deleteProduct(scanner, bookList, categoryList);
                        BookImp.displayBook(bookList);
                        break;
                    case 4:
                        System.out.println("4. Tìm kiếm sách");
                        BookImp.searchBooks(scanner, bookList);
                        break;
                    case 5:
                        System.out.println("5. Hiển thị danh sách sách theo nhóm thể loại");
                        BookImp.displayBookByCatalory(categoryList, bookList);
                        break;
                    case 6:
                        exitMenuBook = false;
                        break;
                    default:
                        break;
                }
        } while (exitMenuBook);
    }
}
