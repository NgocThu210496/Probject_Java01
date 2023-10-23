package ra.bussiness.entity;

import ra.bussiness.Interface.IEntity;
import ra.bussiness.config.Color;

import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class Book implements IEntity, Serializable {
    private String bookId;
    private String bookTitle;
    private String author; //Tên tác giả
    private String publisher; //Nhà xuất bản
    private int year;//Năm xuất bản
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

        System.out.print("Nhập mã sách: ");//(bắt đầu bằng “B”, độ dài 4 kí tự, duy nhất)
        do {
            this.bookId = scanner.nextLine();
            if (this.bookId != null && !this.bookId.trim().isEmpty()) {
                if (this.bookId.length() == 4) {
                    if (this.bookId.startsWith("B")) {
                        boolean isExist = false;
                        for (int i = 0; i < bookList.size(); i++) {
                            if (bookList.get(i).getBookId().equals(getBookId())) {
                                isExist = true;
                                System.out.println(Color.TEXT_RED + "Mã sách đã tồn tại. Vui lòng nhập lại" + Color.TEXT_RESET);
                                break;
                            }
                        }
                        if (!isExist) {
                            break;
                        }
                    } else {
                        System.out.println(Color.TEXT_RED +"Mã sách phải bắt đầu bằng 'B'. Vui lòng nhập lại!"+ Color.TEXT_RESET);
                    }

                } else {
                    System.out.println(Color.TEXT_RED +"Mã sách phải có độ dài 4 ký tự. Vui lòng nhập lại!"+ Color.TEXT_RESET);
                }
            } else {
                System.out.println(Color.TEXT_RED +"Mã sách không được để trống. Vui lòng nhập lại!"+ Color.TEXT_RESET);
            }
        } while (isExit);

        System.out.print("Nhập tiêu đề sách: "); //Tiêu đề sách (từ 6-50 kí tự, duy nhất)
        do {
                this.bookTitle = scanner.nextLine();
            if (this.bookTitle != null && !this.bookTitle.trim().isEmpty()) {
                if (this.bookTitle.length() >= 6 && this.bookTitle.length() <= 50) {
                    boolean isExist = false;
                    for (int i = 0; i < bookList.size(); i++) {
                        if (bookList.get(i).getBookTitle().equals(getBookTitle())) {
                            isExist = true;
                            System.out.println(Color.TEXT_RED +"Tiêu đề sách đã tồn tại. Vui lòng nhập lại"+ Color.TEXT_RESET);
                            break;
                        }
                    }
                    if (!isExist) {
                        break;
                    }
                } else {
                    System.out.println(Color.TEXT_RED +"Tiêu đề sách phải có độ dài từ 6-50 ký tự. Vui lòng nhập lại!"+ Color.TEXT_RESET);
                }
            } else {
                System.out.println(Color.TEXT_RED +"Tiêu đề sách không được để trống. Vui lòng nhập lại!"+ Color.TEXT_RESET);
            }
        } while (isExit);

        System.out.print("Nhập tên tác giả: "); //Tên tác giả (không bỏ trống)
        do {
            String authorInput = scanner.nextLine();
            if (authorInput != null && !authorInput.trim().isEmpty()) {
                this.author = authorInput;
                break;// Khi đã nhập đúng giá trị
            } else {
                System.err.println("Tên tác giả không được để trống. Vui lòng nhập lại!");
            }
        }
        while (isExit);

        System.out.print("Nhập nhà xuất bản: "); //Nhà xuất bản (không bỏ trống)
        do {
            String publisherInput = scanner.nextLine();
            if (publisherInput != null && !publisherInput.trim().isEmpty()) {
                this.publisher = publisherInput;
                break;
            } else {
                System.err.println("Nhà xuất bản không được để trống. Vui lòng nhập lại!");
            }
        }
        while (isExit);

        System.out.print("Nhập năm xuất bản: "); //(tối thiểu từ năm 1970 và không lớn hơn năm hiện tại)
        do {
            String yearInput = scanner.nextLine();
            if (yearInput != null && !yearInput.trim().isEmpty()) {
                try {
                    this.year = Integer.parseInt(yearInput); //ep kieu String ->int
                    Year publicationYear = Year.of(year); //tạo đối tượng Year với giá trị năm là year để ktra năm XB
                    Year currentYear = Year.now(); //năm hiện
                    if (publicationYear.isAfter(Year.of(1970)) && !publicationYear.isAfter(currentYear)) {
                        break;
                    } else {
                        System.err.println("Năm xuất bản phải từ 1970 trở đi và không lớn hơn năm hiện tại.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Năm xuất bản phải là một số nguyên.");
                }
            } else {
                System.err.println("Năm xuất bản không được để trống. Vui lòng nhập lại!");
            }
        } while (isExit);

        System.out.print("Nhập mô tả sách: "); //(không bỏ trống)
        do {
            String descriptionInput = scanner.nextLine();
            if (descriptionInput != null && !descriptionInput.trim().isEmpty()) {
                this.description = descriptionInput;
                break;
            } else {
                System.err.println("Mô tả sách không được để trống. Vui lòng nhập lại!");
            }
        } while (isExit);

        System.out.print("Chọn danh mục của sản phẩm: "); //hiển thị ra các danh mục
        do {
            for (int i = 0; i < categoryList.size(); i++) {
                System.out.println(i + 1 + "." + categoryList.get(i).getCategoryName());
            }
            System.out.println("Lựa chọn của bạn");
            String choiceInput = scanner.nextLine();
            if (!choiceInput.isEmpty()) { // Kiểm tra lựa chọn không trống
                try {
                    int choice = Integer.parseInt(choiceInput); //người dùng nhập để chọn một danh mục từ danh sách
                    if (choice >= 1 && choice <= categoryList.size()) {
                        this.categoryId = categoryList.get(choice - 1).getCategoryId(); //truy cập phần tử thứ (choice - 1)
                        break;
                    } else {
                        System.err.println("Không tồn tại mã danh mục, vui lòng nhập lại!");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Lựa chọn phải là một số nguyên. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("Lựa chọn không được để trống. Vui lòng nhập lại!");
            }

        } while (isExit);

    }

    @Override
    public void output() {
//        System.out.printf("Mã sách: %s - Tiêu đề: %s - Tác giả: %s\n", this.bookId, this.bookTitle, this.author);
//        System.out.printf("NXB: %s - Năm xuất bản: %d - Mô tả sách: %s - Danh mục của sản phẩm: %d\n", this.publisher, this.year, this.description, this.categoryId);
//        System.out.println("-------------------------*-----------------------------");

        System.out.printf("%30s       | %10s    | %10s    | %10s     | %10d    |%10s    |%10d   |\n",
                this.bookId, this.bookTitle, this.author, this.publisher, this.year, this.description, this.categoryId );
        System.out.println("                        |-------------------------------------------------------------------------------------------------------------------|");



    }



}
