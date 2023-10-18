package ra.entity;

import ra.bussiness.IEntity;

import java.util.List;
import java.util.Scanner;

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

    public void inputDataBook(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        scanner = new Scanner(System.in);
        System.out.println("------------------Nhập thông tin sách: -----------------");
        boolean isExit = true;
        System.out.println("Nhập mã sách: ");//(bắt đầu bằng “B”, độ dài 4 kí tự, duy nhất)
        do {
            String bookIdInput = scanner.nextLine();
            if (bookIdInput != null || bookIdInput.trim().length() != 0) {
                this.bookId = scanner.nextLine();
                if (this.bookId.length() == 4) {
                    if (this.bookId.startsWith("B")) {
                        boolean isExist = false;
                        for (int i = 0; i < bookList.size(); i++) {
                            if (bookList.get(i).equals(getBookId())) {
                                isExist = true;
                                System.out.println("Mã sách đã tồn tại. Vui lòng nhập lại");
                                break;
                            }
                        }
                        if (!isExist) {
                            break;
                        }
                    } else {
                        System.out.println("Mã sách phải bắt đầu bằng 'B'. Vui lòng nhập lại!");
                    }

                } else {
                    System.err.println("Mã sách phải có độ dài 4 ký tự. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("Mã sách không được để trống. Vui lòng nhập lại!");
            }
        } while (isExit);

        System.out.println("Nhập tiêu đề sách: "); //Tiêu đề sách (từ 6-50 kí tự, duy nhất)
        do {
            String bookTitleInput = scanner.nextLine();
            if (bookTitleInput != null || bookTitleInput.trim().length() != 0) {
                this.bookTitle = scanner.nextLine();
                if (this.bookTitle.length() >= 6 && this.bookTitle.length() <= 50) {
                    boolean isExist = false;
                    for (int i = 0; i < bookList.size(); i++) {
                        if (bookList.get(i).equals(getBookTitle())) {
                            isExist = true;
                            System.out.println("Tiêu đề sách đã tồn tại. Vui lòng nhập lại");
                            break;
                        }
                    }
                    if (!isExist) {
                        break;
                    }
                } else {
                    System.err.println("Tiêu đề sách phải có độ dài từ 6-50 ký tự. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("Tiêu đề sách không được để trống. Vui lòng nhập lại!");
            }
        } while (isExit);

        System.out.println("Nhập tên tác giả: "); //Tên tác giả (không bỏ trống)
        do {
            this.author = scanner.nextLine();
        }
        while (this.author.isEmpty());  //kiểm tra chuỗi rỗng hoặc không rỗng

        //Nhà xuất bản (không bỏ trống)

    }

    @Override
    public void output() {
        System.out.printf("Mã sách: %s - Tiêu đề: %s - Tác giả: %s\n", this.bookId, this.bookTitle, this.author);
        System.out.printf("NXB: %s - Năm xuất bản: %d - Thể loại: %", this.publisher, this.year, this.categoryId);
    }
}
