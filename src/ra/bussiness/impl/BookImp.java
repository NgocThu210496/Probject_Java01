package ra.bussiness.impl;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;

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
           // boolean result=
        }
    }
}
