package ra.presentation;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.bussiness.impl.BookImp;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void showBooksMenu(List<Category> categoryList, List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        boolean exitMenuBook = true;
        do {
            for (Book b: bookList) {
                b.output();
            }
            System.out.println("==============QUẢN LÝ SÁCH==============");
            System.out.println("1. Thêm mới sách");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Hiển thị danh sách theo nhóm thể loại");
            System.out.println("6. Quay lại");
            System.out.println("-----------------------------------------------");
            System.out.println("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("1. Thêm mới thể loại");
                    BookImp.createBook(scanner, categoryList, bookList);
                    break;
                case 2:
                    System.out.println("2. Cập nhật thông tin sách");
                    BookImp.updateBook(scanner, categoryList, bookList);
                    break;
                case 3:
                    System.out.println("3.Xóa sách");
                    BookImp.deleteProduct(scanner,bookList,categoryList);
                    break;
                case 4:
                    System.out.println("4. Tìm kiếm sách");
                    BookImp.searchBooks(scanner,bookList);
                    break;
                case 5:
                    System.out.println("5. Hiển thị danh sách sách theo nhóm thể loại");
                    BookImp.displayBookByCatalory(categoryList,bookList);
                    break;
                case 6:
                    exitMenuBook = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }

        } while (exitMenuBook);
    }
}
