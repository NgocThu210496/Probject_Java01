package ra.entity;

import ra.bussiness.IEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category implements IEntity, Serializable {
   private int categoryId;
   private String categoryName;
   private boolean categoryStatus;

    public Category() {
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

    @Override
    public void input() {
        Scanner scanner =new Scanner(System.in);
        List<Integer> existingIds = new ArrayList<>(); // Biến để lưu trữ danh sách các id đã tồn tại
        System.out.println("------------------Nhập thông tin thể loại sách: -----------------");
        boolean isExit=true;
        do {
            try {
                System.out.println("Nhập vào mã thể loại sách: "); // (số nguyên lớn hơn 0, duy nhất)
                this.categoryId=Integer.parseInt(scanner.nextLine());
                if (categoryId>0){
//                    boolean isExist = false;//Không trùng lặp
//                    for (Categories catalog : listCategories) {
//                        if (catalog.getName().equals(this.name)) {
//                            isExist = true;//trùng lặp
//                            break;
//                        }
//                    }
//                    if (isExist) {
//                        System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
//                    } else {
//                        break;
//                    }
                }else {
                    System.out.println("Mã thể loại phải là số nguyên lớn hơn 0.");
                }
            }catch (NumberFormatException e){
                System.out.println("Mã thể loại phải là số nguyên lớn hơn 0.");
            }
        }while (isExit);

        //Tên thể loại (không trùng nhau, từ 6-30 kí tự)
        System.out.println("Nhập tên thể loại: ");
        do {


        }while (isExit);

        System.out.println("Nhập vào trạng thái thể loại:");
        this.categoryStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void output() {
        System.out.printf("Mã thể loại sách: %d - Tên thể loại: %s - Trạng thái thể loại: %b", this.categoryId, this.categoryName, this.categoryStatus ? "Hoạt động" : "Không hoạt động");
        System.out.println("-------------------------*-----------------------------");
    }
}
