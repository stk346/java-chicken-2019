package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public static void InputMainDisplayNumber() {
        System.out.println("## 원하는 기능을 선택하세요");
        String userInput = scanner.nextLine();
        try {
            Integer.parseInt(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("올바른 숫자를 입력해주세요.");
            InputMainDisplayNumber();
        }
    }
}
