package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputTableNumber() {
        System.out.println("## 테이블을 선택하세요.");
        String uerInput = scanner.nextLine();
        try {
            return Integer.parseInt(uerInput)-1;
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력해주세요.");
            return inputTableNumber();
        }
    }

    public static int InputMainDisplayNumber() {
        System.out.println("## 원하는 기능을 선택하세요");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력해주세요.");
            return InputMainDisplayNumber();
        }
    }

    public static int InputOrderNumber() {
        System.out.println("## 등록할 메뉴를 선택하세요.");
        String uerInput = scanner.nextLine();
        try {
            return Integer.parseInt(uerInput)-1;
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력해주세요.");
            return InputOrderNumber();
        }
    }

    public static int InputMenuCount() {
        System.out.println("## 메뉴의 수량을 입력하세요.");
        String uerInput = scanner.nextLine();
        try {
            return Integer.parseInt(uerInput);
        } catch (IllegalArgumentException e) {
            System.out.println("숫자만 입력해주세요.");
            return InputOrderNumber();
        }
    }

    public static int inputToPayCashOrCard() {
        System.out.println("신용카드는 1번, 현금은 2번");
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("올바른 숫자를 입력해주세요");
            return inputToPayCashOrCard();
        }
    }
}
