package ra.bussiness.impl;

import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.writeRead_File.WriteReadBook;
import ra.writeRead_File.WriteReadCategory;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class BookImp {
    //static boolean found = false;//kiểm tra  mã thể loại tồn tại hay không


    public static boolean createBook(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        scanner = new Scanner(System.in);
        System.out.print("Nhập vào số sách cần thêm mới: ");
        int n = Integer.parseInt(scanner.nextLine());
        boolean addedSuccessfuly = false; // kiem tra da them thanh cong hay chua?
        for (int i = 0; i < n; i++) {
            //khoi tao doi tuong book
            Book book = new Book();
            book.inputDataBook(scanner, categoryList, bookList); //nhap vao inputDataBook
            bookList.add(book);
            addedSuccessfuly = true;
        }
        if (addedSuccessfuly) {
            //luu vao file .txt
            boolean result = WriteReadBook.writeBookToFile(bookList, categoryList);
            if (result) {
                System.out.println("Đã thêm sách thành công và lưu vào file");
            } else {
                System.err.println("Không thể lưu dữ liệu vào file.");
            }
        }
        return addedSuccessfuly;
    }

    public static boolean updateBook(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        boolean addedSuccessfully = false; //kiểm tra đã update/delete thành công chưa
        System.out.print("Nhập vào mã sách cần cập nhật: ");
        do {
            String idInput = scanner.nextLine();
            if (!idInput.isEmpty()) {
                boolean found = false; //kiểm tra sự tồn tại của mã sách

                // Kiểm tra xem có tồn tại mã ID
                for (int i = 0; i < bookList.size(); i++) {
                    Book book = bookList.get(i);
                    if (book.getBookId().equals(idInput)) {
                        found = true;
                        // Thực hiện cập nhật thông tin sách ở đây
                        System.out.print("Nhập tiêu đề sách mới (từ 6-50 ký tự): ");
                        do {
                            String newBookTitle = scanner.nextLine();
                            if (newBookTitle != null && !newBookTitle.trim().isEmpty()) {
                                book.setBookTitle(newBookTitle);
                                if (book.getBookTitle().length() >= 6 && book.getBookTitle().length() <= 50) {
                                    boolean isExist = false;
                                    for (int j = 0; j < bookList.size(); j++) {
                                        if (i != j && bookList.get(j).getBookTitle().equals(book.getBookTitle())) {
                                            isExist = true;
                                            System.err.println("Tiêu đề sách đã tồn tại. Vui lòng nhập lại");
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
                        } while (true);

                        System.out.print("Nhập tên tác giả mới: ");

                        do {
                            String newAuthor = scanner.nextLine();
                            if (newAuthor != null && !newAuthor.trim().isEmpty()) {
                                book.setAuthor(newAuthor);
                                ;
                                break;
                            } else {
                                System.err.println("Tên tác giả không được để trống. Vui lòng nhập lại!");
                            }
                        }
                        while (true);

                        System.out.print("Nhập nhà xuất bản mới: ");
                        do {
                            String newPublisher = scanner.nextLine();
                            if (newPublisher != null && !newPublisher.trim().isEmpty()) {
                                book.setPublisher(newPublisher);
                                break;
                            } else {
                                System.err.println("Nhà xuất bản không được để trống. Vui lòng nhập lại!");
                            }
                        }
                        while (true);

                        System.out.print("Nhập năm xuất bản mới (từ 1970 đến hiện tại): ");
                        do {
                            String newYearInput = scanner.nextLine();
                            if (newYearInput != null && !newYearInput.trim().isEmpty()) {
                                try {
                                    int newYear = Integer.parseInt(newYearInput); // Ép kiểu String thành int
                                    Year publicationYear = Year.of(newYear); // Tạo đối tượng Year với giá trị năm mới
                                    Year currentYear = Year.now(); // Năm hiện tại
                                    if (publicationYear.isAfter(Year.of(1970)) && !publicationYear.isAfter(currentYear)) {
                                        book.setYear(newYear); // Gán năm mới cho sách
                                        break; // năm hợp lệ
                                    } else {
                                        System.err.println("Năm xuất bản phải từ 1970 trở đi và không lớn hơn năm hiện tại.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.err.println("Năm xuất bản phải là một số nguyên.");
                                }
                            } else {
                                System.err.println("Năm xuất bản không được để trống. Vui lòng nhập lại!");
                            }
                        }
                        while (true);


                        System.out.print("Nhập mô tả sách mới: ");
                        do {
                            String newDescription = scanner.nextLine();
                            if (newDescription != null && !newDescription.trim().isEmpty()) {
                                book.setDescription(newDescription);
                                break;
                            } else {
                                System.err.println("Mô tả sách không được để trống. Vui lòng nhập lại!");
                            }
                        }
                        while (true);

                        System.out.print("Chọn danh mục mới của sản phẩm: ");
                        for (int j = 0; j < categoryList.size(); j++) {
                            System.out.println(j + 1 + ". " + categoryList.get(j).getCategoryName());
                        }
                        // Nhập lựa chọn danh mục mới từ người dùng
                        int newCategoryIndex = getNewCategoryIndex(scanner, categoryList);
                        // Kiểm tra lựa chọn danh mục mới
                        while (newCategoryIndex == -1) {
                            System.err.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                            newCategoryIndex = getNewCategoryIndex(scanner, categoryList);
                        }
                        int newCategoryId = categoryList.get(newCategoryIndex).getCategoryId();
                        book.setCategoryId(newCategoryId);

                        System.out.println("Thông tin sách sau khi cập nhật:");
                        //  book.output();

                    }
                }
                if (!found) {
                    System.err.println("Không tìm thấy mã sách. Vui lòng nhập lại!");
                }
                if (addedSuccessfully) {
                    //ghi danh sach danh muc vao file .txt
                    boolean result = WriteReadCategory.writeCategoryToFile(categoryList, bookList);
                    if (result) {
                        System.out.println("Đã cập nhật danh mục thành công và lưu vào file");
                    } else {
                        System.err.println("Không thể lưu dữ liệu vào file.");
                    }
                }
                return addedSuccessfully;
            } else {
                System.err.println("Mã sách không được để trống. Vui lòng nhập lại!");
            }
            addedSuccessfully = true;
        } while (true);
    }

    private static int getNewCategoryIndex(Scanner scanner, List<Category> categoryList) {
        int newCategoryIndex = -1;
        do {
            System.out.print("Lựa chọn của bạn: ");
            String choiceInput = scanner.nextLine();
            if (!choiceInput.isEmpty()) { // Kiểm tra lựa chọn không trống
                try {
                    int choice = Integer.parseInt(choiceInput);
                    if (choice >= 1 && choice <= categoryList.size()) {
                        newCategoryIndex = choice - 1;
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
        } while (newCategoryIndex == -1);

        return newCategoryIndex;
    }


}
