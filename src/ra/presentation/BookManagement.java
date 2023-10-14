package ra.presentation;

import java.util.Scanner;

public class BookManagement {

    public static void showBooksMenu(){
        Scanner scanner= new Scanner(System.in);
        boolean exitMenuBook=true;
        do {
            System.out.println("==============QUẢN LÝ SÁCH==============");
            System.out.println("1. Thêm mới sách");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Hiển thị danh sách theo nhóm thểloại");
            System.out.println("6. Quay lại");
            System.out.println("-----------------------------------------------");
            System.out.println("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.next());

            switch (choice){
                case 1:
                    System.out.println("1. Thêm mới thể loại");
                    break;
                case 2:
                    System.out.println("2. Hiển thị danh sách theo tên A–Z");
                    break;
                case 3:
                    System.out.println("3. Thống kê thể loại và số sách có trong mỗi thể loại");
                    break;
                case 4:
                    System.out.println("4. Cập nhật thể loại");
                    break;
                case 5:
                    System.out.println("5. Xóa thể loại");
                    break;
                case 6:
                    exitMenuBook=false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }

        }while (exitMenuBook);
    }
}
