package entity2;

import java.sql.ResultSet;
import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private Float price;
    private String author;
    private boolean status;

    public Book() {
    }

    public Book(String bookId, String bookName, Float price, String author, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.author = author;
        this.status = status;
    }


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner){
        System.out.println("nhap Id: ");
        this.bookId= scanner.nextLine();
        System.out.println("nhap ten sach: ");
        this.bookName= scanner.nextLine();
        System.out.println("nhap gia sach: ");
        this.price= Float.parseFloat(scanner.nextLine());
        System.out.println("nhap ten tac gia: ");
        this.author= scanner.nextLine();
        System.out.println("nhap trang thai: ");
        this.status= Boolean.parseBoolean(scanner.nextLine());

    }

    public void displayData() {
        System.out.println(
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", status=" + (status?"Dang ban":"Ngung ban" ));
    }
}
