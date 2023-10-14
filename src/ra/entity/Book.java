package ra.entity;

import ra.bussiness.IEntity;

public class Book implements IEntity {
    private String bookId;
    private String bookTitle;
    private String author; //Tên tác giả
    private String publisher; //Nhà xuất bản
    private int year;//Năm xuất bản (tối thiểu từ năm 1970 và không lớn hơn năm hiện tại)
    private String description;
    private int categoryId; //Mã thể loại sách (phải lấy từ danh sách Thể loại sách đã lưu trước đó)

    public Book() {
    }

    public Book(String bookId, String bookTitle, String author, String publisher, int year, String description, int categoryId) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void input() {


    }

    @Override
    public void output() {
        System.out.printf("Mã sách: %s - Tiêu đề: %s - Tác giả: %s\n",this.bookId,this.bookTitle, this.author);
        System.out.printf("NXB: %s - Năm xuất bản: %d - Thể loại: %",this.publisher, this.year, this.categoryId);
    }
}
