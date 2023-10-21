package ra.bussiness.impl;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.writeRead_File.WriteReadBook;

import java.util.List;
import java.util.Scanner;

public class BookImp {
    public static void createBook(Scanner scanner, List<Category> categoryList, List<Book> bookList){
        scanner=new Scanner(System.in);
        System.out.println("Nhập vào số sách cần thêm mới' ");
        int n=Integer.parseInt(scanner.nextLine());
        boolean addedSuccessfuly=false; // kiem tra da them thanh cong hay chua?
        for(int i=0;i<n;i++){
            //khoi tao doi tuong book
            Book book=new Book();
            book.inputDataBook(scanner,categoryList,bookList); //nhap vao inputDataBook
            bookList.add(book);
            addedSuccessfuly=true;
        }
        if(addedSuccessfuly){
            //luu vao file .txt
            boolean result= WriteReadBook.writeBookToFile(bookList, categoryList);
            if(result){
                System.out.println("Đã thêm sách thành công và lưu vào file");
            }else {
                System.err.println("Không thể lưu dữ liệu vào file.");
            }
        }
    }
    public static void displayBooks(List<Category> categoryList,List<Book> bookList) {
        for (Book book : bookList) {
            book.output();
        }
    }
}
