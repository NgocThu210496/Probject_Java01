package ra.bussiness.entity;

import ra.bussiness.design.IEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements IEntity, Serializable {
    //private static int newCatalogId = 1;
    private int categoryId;
    private String categoryName;
    private boolean categoryStatus;

    public Category() {
       // this.categoryId = newCatalogId++;
    }

    public Category(int categoryId, String categoryName, boolean categoryStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public void inputDataCategory(Scanner scanner, List<Category> categoryList) {
        scanner = new Scanner(System.in);
        System.out.println("------------------Nhập thông tin thể loại sách: -----------------");
        boolean isExit = true;
        System.out.println("Nhập vào mã thể loại: "); //(số nguyên lớn hơn 0, duy nhất)
        do {
            String categoryIdInput=scanner.nextLine();
            if(categoryIdInput!=null || categoryIdInput.trim().length()!=0){
                try{
                    this.categoryId = Integer.parseInt(categoryIdInput); //chuyển đổi chuỗi thành một số nguyên.
                    if (this.categoryId > 0) {
                        boolean isExist = false; //id đã tồn tại hay chưa
                        for (Category ct : categoryList) {
                            if (ct.getCategoryId() == this.categoryId) {
                                isExist = true; //tìm thấy id trùng lặp
                                System.err.println("Mã thể loại đã tồn tại. Vui lòng nhập lại!");
                                break;
                            }
                        }
                        if (!isExist) {
                            break; // Nếu không tìm thấy id trùng lặp, thoát khỏi vòng lặp
                        }
                    } else {
                        System.err.println("Mã thể loại phải lớn hơn 0. Vui lòng nhập lại!");
                    }
                }catch (NumberFormatException e){
                    System.err.println("Mã thể loại phải là số nguyên. Vui lòng nhập lại!");
                }
            }else {
                System.err.println("Mã thể loại không được để trống. Vui lòng nhập lại!");
            }

        } while (isExit);

        //Tên thể loại (không trùng nhau, từ 6-30 kí tự)
        System.out.println("Nhập tên thể loại: ");
        do {
            String categoryNameInput=scanner.nextLine();
            if(categoryNameInput!=null || categoryNameInput.trim().length()!=0){
                this.categoryName = scanner.nextLine();
                if (categoryName.length() >= 6 && categoryName.length() <= 30) {
                    boolean isExist = false;
                    for (int i = 0; i < categoryList.size(); i++) {
                        if (categoryList.get(i).categoryName.equals(getCategoryName())) {
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
            }else {
                System.err.println("Tên danh mục không được để trống. Vui lòng nhập lại!");
            }
        } while (isExit);

        System.out.println("Nhập vào trạng thái thể loại (true/false):");
        do {
            String statusInput=scanner.nextLine().toLowerCase(); // Chuyển thành chữ thường
            if(statusInput!=null || statusInput.trim().length()!=0){
                if(statusInput.equals("true") || statusInput.equals("false")){
                    this.categoryStatus=Boolean.parseBoolean(statusInput);
                    break;
                }else {
                    System.err.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false. Vui lòng nhập lại!");
                }
            }else {
                System.err.println("Trạng thái thể loại không được để trống. Vui lòng nhập lại!");
            }

        }while (isExit);
    }

    @Override
    public void output() {
        System.out.printf("Mã thể loại sách: %d - Tên thể loại: %s - Trạng thái thể loại: %b", this.categoryId, this.categoryName, this.isCategoryStatus() ? "Hoạt động" : "Không hoạt động");
        System.out.println("-------------------------*-----------------------------");
    }
}
