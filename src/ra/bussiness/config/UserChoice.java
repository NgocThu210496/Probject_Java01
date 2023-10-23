package ra.bussiness.config;

import java.util.Scanner;

public class UserChoice {
    public static int getUserChoice(Scanner scanner) {
        int choice = -1; // Giá trị không hợp lệ
        while (choice < 1 || choice > 6) {
            try {
                String input = "";
                while (input.trim().isEmpty()) {
                    System.out.print("Nhập lựa chọn của bạn: ");
                    input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.err.println("Lựa chọn không được để trống. Vui lòng chọn lại!");
                    } else {
                        choice = Integer.parseInt(input);
                    }
                }
                if (choice < 1 || choice > 6) {
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là một số nguyên. Vui lòng chọn lại!");
            }
        }

        return choice;
    }

    public static int getUserChoiceMain(Scanner scanner) {
        int choice = -1; // Giá trị không hợp lệ

        while (choice < 1 || choice > 3) {
            try {
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > 3) {
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là một số nguyên. Vui lòng chọn lại!");
            }
        }

        return choice;
    }


}
