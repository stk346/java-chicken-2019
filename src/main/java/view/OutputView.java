package view;

import com.sun.security.jgss.GSSUtil;
import domain.Menu;
import domain.SelectedMenu;
import domain.Table;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printLine(BOTTOM_LINE, size);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void showMainDisplay() {
        System.out.println("## 메인화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 프로그램 종료");
        System.out.println();
    }

    public static void showPayStartingMessage(int tableNumber) {
        System.out.println(tableNumber + "번 테이블의 결제를 진행합니다.");
    }

    public static void showPayingAmount(double giveADiscount) {
        System.out.println("## 최종 결제할 금액");
        System.out.println(giveADiscount + "원");
    }

    public static void showOrderDetails(Table table) {
        System.out.println("## 주문 내역\n" + "메뉴 수량 금액");
        StringBuilder sb = new StringBuilder();
        for (SelectedMenu selectedMenu : table.getSelectedMenus()) {
            sb.append(selectedMenu.getMenuName() + " " +
                    selectedMenu.getMenuCount() + " " +
                    selectedMenu.getPrice() + "\n");
        }
        System.out.println(sb);
    }
}
