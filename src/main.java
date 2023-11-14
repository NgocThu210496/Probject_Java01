import bussiness.BookBussiness;
import entity2.Book;

import java.awt.datatransfer.FlavorEvent;
import java.util.List;
import java.util.Scanner;

import static bussiness.BookBussiness.searchNameBook;
import static bussiness.BookBussiness.sortPriceByPrice;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("*****************MENU******************");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm");
            System.out.println("3. Sửa");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp theo giá sách");
            System.out.println("6. Tìm sách theo tên sách");
            System.out.println("7. Tìm kiếm theo giá sách (gia tu a den b)");
            System.out.println("8. Tính tổng giá sách");
            System.out.println("9. Thoát");
            System.out.println("****************************************");
            System.out.print("  Mời lựa chọn (1/2/3/4/5/6/7/8/9): ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showListBook();
                    break;
                case 2:
                    createBook(scanner);
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    deleteBook(scanner);
                    break;
                case 5:
                    sortByPriceDESC();
                    break;
                case 6:
                    searchName(scanner);
                    break;
                case 7:
                    getCntBookByPrice(scanner);
                    break;
                case 8:
                    sumBookByPrice();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }

    private static void showListBook() {
        List<Book> bookList = BookBussiness.getAllBook();
        System.out.println("DANH SACH");
        for (Book book:bookList) {
            book.displayData();
        }
    }
    public static void createBook(Scanner scanner){
        // nhap data cho 1 san pham them moi
        Book bookNew = new Book();
        bookNew.inputData(scanner);
        // goi createbook cua bookbussiness de thuc hien them du lieu vao data
        boolean result = BookBussiness.createBook(bookNew);
        if(result){
            System.out.println("Them moi thanh cong !");
        }else {
            System.out.println("Co loi xay ra, vui long thu lai!");
        }
    }

    public static void updateBook(Scanner scanner){
        Book book = new Book();
        System.out.println("Nhập mã Id cần cập nhật: ");
        String bookIdUpdate = scanner.nextLine();
        // ktra ma sp nay co ton tai hay khong
        Book bookUpdate = BookBussiness.getBookById(bookIdUpdate);
        if(bookUpdate !=null){
            System.out.println(" Nhap vao ten sach can cap nhat: ");
            bookUpdate.setBookName(scanner.nextLine());
            System.out.println("Nhap vao gia sach can cap nhat: ");
            bookUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
            System.out.println("nhap vao tac gia can cap nhat: ");
            bookUpdate.setAuthor(scanner.nextLine());
            System.out.println("Nhap vao trang thai can cap nhat: ");
            bookUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
            //thuc hien cap nhat
            boolean result = BookBussiness.updateBook(bookUpdate);
            if (result){
                System.out.println("cap nhat thanh cong");
            }else {
                System.out.println("Co loi xay ra, vui long thu lai!");
            }
        }else {
            System.out.println("ma sp k ton tai ");
        }

    }

    public static void deleteBook(Scanner scanner){
        System.out.println("Nhap vao ma Id can xoa: ");
        String bookIdDelete = scanner.nextLine();
        // ktra id co ton tai trong csdl hay khong
         Book bookDelete = BookBussiness.getBookById(bookIdDelete);
         if(bookDelete !=null){
             boolean result = BookBussiness.deleteBook(bookIdDelete);
             if(result){
                 System.out.println("xoa thanh cong");
             }else {
                 System.out.println("Co loi xay ra, vui long thu lai!");
             }
         }else {
             System.out.println("ma Id khong ton tai");
         }
    }

    public static void getCntBookByPrice(Scanner scanner){
        System.out.println("Nhap vao gia sach tu: ");
        Float fromPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhap vao gia sach den: ");
        Float toPrice= Float.parseFloat(scanner.nextLine());
        // thuc hien dem
        int cntBook= BookBussiness.getBookByPrice(fromPrice,toPrice);
        System.out.printf("So luong sach co gia tu %f den %f la : %d\n", fromPrice,toPrice,cntBook);
    }

    public static void sortByPriceDESC(){
        List<Book> bookList = sortPriceByPrice();
        System.out.println("Sắp xếp giá sách giảm dần: ");
        for (Book book:bookList) {
            System.out.println(book.getPrice());
        }
    }

    public static void sumBookByPrice(){
        float sum = BookBussiness.sumPrice();
        System.out.println("Tổng giá của sách: " + sum);
    }

    public static void searchName(Scanner scanner){
        System.out.println("Nhap vao ten sach can tim: ");
        String search = scanner.nextLine();
        BookBussiness.searchNameBook(search);
    }

}
