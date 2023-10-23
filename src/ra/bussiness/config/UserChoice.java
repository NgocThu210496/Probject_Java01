package ra.bussiness.config;

import java.util.Scanner;

public class UserChoice {
    public static int getUserChoice(Scanner scanner) {
        int choice = -1; // Giá trị không hợp lệ
        System.out.print("                                                                      Nhập lựa chọn của bạn: ");
        while (choice < 1 || choice > 6) {
            try {
                String input = "";
                while (input.trim().isEmpty()) {
                    input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println(Color.TEXT_RED +"Lựa chọn không được để trống. Vui lòng chọn lại!"+ Color.TEXT_RESET);
                    } else {
                        choice = Integer.parseInt(input);
                    }
                }
                if (choice < 1 || choice > 6) {
                    System.out.println(Color.TEXT_RED + "Lựa chọn không hợp lệ. Vui lòng chọn lại!"+ Color.TEXT_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(Color.TEXT_RED +"Lựa chọn phải là một số nguyên. Vui lòng chọn lại!"+ Color.TEXT_RESET);
            }
        }

        return choice;
    }

    public static int getUserChoiceMain(Scanner scanner) {
        int choice = -1; // Giá trị không hợp lệ
        System.out.print("                                                                      Nhập lựa chọn của bạn: ");
        while (choice < 1 || choice > 3) {
            try {

                choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > 3) {
                    System.out.println(Color.TEXT_RED +"Lựa chọn không hợp lệ. Vui lòng chọn lại!"+ Color.TEXT_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(Color.TEXT_RED +"Lựa chọn phải là một số nguyên. Vui lòng chọn lại!"+ Color.TEXT_RESET);
            }
        }

        return choice;
    }


}
