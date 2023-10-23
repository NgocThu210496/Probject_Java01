package ra.bussiness.impl;

import ra.bussiness.config.DisplayHeader;
import ra.bussiness.entity.Book;
import ra.bussiness.entity.Category;
import ra.writeRead_File.WriteReadBook;
import ra.writeRead_File.WriteReadCategory;

import java.util.*;

public class CatalogImp {
    static boolean found = false;//kiểm tra  mã thể loại tồn tại hay không
    static boolean addedSuccessfully = false; //kiểm tra đã update/delete thành công chưa

    public static boolean createCatalog(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        scanner = new Scanner(System.in);
        System.out.print("Nhập vào số thể loai cần thêm mới: ");
        int n = Integer.parseInt(scanner.nextLine());
        boolean addedSuccessfully = false; //kiểm tra đã thêm thành công chưa
        for (int i = 0; i < n; i++) {
            Category category = new Category();
            category.inputDataCategory(scanner, categoryList);
            categoryList.add(category);
            addedSuccessfully = true;
        }
        if (addedSuccessfully) {
            //ghi danh sach danh muc vao file .txt
            boolean result = WriteReadCategory.writeCategoryToFile(categoryList, bookList);
            if (result) {
                System.out.println("Đã thêm danh mục thành công và lưu vào file");
            } else {
                System.err.println("Không thể lưu dữ liệu vào file.");
            }
        }
        return addedSuccessfully;
    }

    public static void displayCatalog(List<Category> categoryList) {
        // In tiêu đề
        DisplayHeader.displayHeaderCatalog();
        for (Category ct : categoryList) {
            ct.output();
        }
    }

    public static void sortCatalogName(List<Category> categoryList) {
        Collections.sort(categoryList, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }
        });
        displayCatalog(categoryList);
    }

    public static void statsCatalog(List<Category> categoryList, List<Book> bookList) {
        Map<String, Integer> mapStatsCatalog = new HashMap<>();

        for (Category category : categoryList) {
            int count = 0;
            for (Book book : bookList) {
                if (book.getCategoryId() == category.getCategoryId()) {
                    count++;
                }
            }
            mapStatsCatalog.put(category.getCategoryName(), count);
        }

        System.out.println("Thống kê thể loại và số sách có trong mỗi thể loại:");
        for (Category category : categoryList) {
            int categoryId = category.getCategoryId();
            String catalogName = category.getCategoryName();
            int countBook = mapStatsCatalog.get(catalogName);
            System.out.println("Mã: " + categoryId + " - Tên thể loại: " + catalogName + " - Số sách: " + countBook);
        }
    }

    public static boolean updateCatalog(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        int updateId = 0;
        System.out.print("Nhập vào mã id cần cập nhật: ");
        do {
            String idInput = scanner.nextLine();
            if (!idInput.isEmpty()) {
                try {
                    updateId = Integer.parseInt(idInput);
                    // Kiểm tra xem có tồn tại mã ID này trong danh sách không
                    for (Category category : categoryList) {
                        if (category.getCategoryId() == updateId) {
                            found = true;
                            break;  // Mã ID tồn tại, thoát khỏi vòng lặp
                        }
                    }
                    if (!found) {
                        System.err.println("Không tìm thấy mã thể loại. Vui lòng nhập lại!");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Mã thể loại phải là một số nguyên. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("Mã thể loại không được để trống. Vui lòng nhập lại!");
            }
        } while (!found);

        for (int i = 0; i < categoryList.size(); i++) {
            // lấy ra một đối tượng sinh viên từ danh sách studentList tại vị trí i
            Category category = categoryList.get(i);
            if (category.getCategoryId() == updateId) {
                found = true;

                System.out.println("------------------Cập nhập thông tin thể loại sách: -----------------");

                System.out.print("Tên thể loại: ");
                do {
                    String categoryNameInput = scanner.nextLine();
                    if (categoryNameInput != null && !categoryNameInput.trim().isEmpty()) {
                        category.setCategoryName(categoryNameInput);
                        if (category.getCategoryName().length() >= 6 && category.getCategoryName().length() <= 30) {
                            boolean isExist = false;
                            for (int j = 0; j < categoryList.size(); j++) {
                                if (i != j && categoryList.get(j).getCategoryName().equals(category.getCategoryName())) {
                                    isExist = true;
                                    System.err.println("Tên thể loại đã tồn tại. Vui lòng nhập lại!");
                                    break;
                                }
                            }
                            if (!isExist) {
                                break;
                            }
                        } else {
                            System.err.println("Tên danh mục có độ dài từ 6-30 ký tự. Vui lòng nhập lại!");
                        }
                    } else {
                        System.err.println("Tên danh mục không được để trống. Vui lòng nhập lại!");
                    }
                } while (true);

                System.out.print("Cập nhật trạng thái thể loại: ");
                do {
                    String statusInput = scanner.nextLine().trim(); // Chuyển thành chữ thường và bỏ khoảng trắng
                    if (!statusInput.isEmpty()) {
                        if (statusInput.equals("true") || statusInput.equals("false")) {
                            category.setCategoryStatus(Boolean.parseBoolean(statusInput));
                            break;
                        } else {
                            System.err.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false. Vui lòng nhập lại!");
                        }
                    } else {
                        System.err.println("Trạng thái thể loại không được để trống. Vui lòng nhập lại!");
                    }
                } while (true);
            }
            addedSuccessfully = true;
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
    }

    public static void deleteCatalog(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        // Nhập vào mã danh mục cần xóa
        System.out.println("Nhập vào mã danh mục cần xóa:");
        // Biến kiểm tra tính hợp lệ của mã danh mục
        boolean validId = false;

        do {
            int catalogId;
            try {
                // Đọc và chuyển đổi mã danh mục từ chuỗi người dùng nhập vào số nguyên
                catalogId = Integer.parseInt(scanner.nextLine());

                // Tìm vị trí (index) của danh mục trong danh sách dựa trên mã danh mục
                int indexDelete = getIndexCatalogOfList(catalogId, categoryList);

                if (indexDelete >= 0) {
                    // Lấy thông tin danh mục cần xóa
                    Category categoryToDelete = categoryList.get(indexDelete);
                    // Kiểm tra xem danh mục có chứa sách hay không
                    boolean isContains = false;
                    for (Book b : bookList) {
                        if (b.getCategoryId() == catalogId) {
                            isContains = true;
                            break;
                        }
                    }
                    // Nếu danh mục chứa sách, thông báo và không thực hiện xóa
                    if (isContains) {
                        System.err.println("Danh mục này đang chứa sách và không thể xóa.");
                    } else {
                        // Hiển thị thông tin danh mục trước khi xóa
                        System.out.println("Thông tin danh mục trước khi xóa:");
                        categoryToDelete.output();
                        // Yêu cầu xác nhận từ người dùng
                        System.out.print("Xác nhận xóa danh mục (yes/no): ");
                        String confirm = scanner.nextLine();

                        if (confirm.equalsIgnoreCase("yes")) {
                            categoryList.remove(indexDelete);
                            System.out.println("Đã xoá danh mục thành công.");
                            validId = true;
                        } else if (confirm.equalsIgnoreCase("no")) {
                            System.out.println("Hủy xóa danh mục.");
                            validId = true;
                        } else {
                            System.err.println("Lựa chọn không hợp lệ. Vui lòng nhập 'yes' hoặc 'no'.");
                        }
                    }
                } else {
                    System.err.println("Mã danh mục không tồn tại. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Mã danh mục phải là một số nguyên. Vui lòng nhập lại.");
            }
        } while (!validId);

        // Ghi danh sách danh mục đã cập nhật vào tệp tin
        WriteReadCategory.writeCategoryToFile(categoryList, bookList);
    }


    public static int getIndexCatalogOfList(int catalogId, List<Category> listCategories) {
        for (int i = 0; i < listCategories.size(); i++) {
            if (listCategories.get(i).getCategoryId() == catalogId) {
                return i;
            }
        }
        return -1;
    }
}
