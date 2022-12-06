import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ChickenPosController {

    private final List<Table> tables = new ArrayList<>();

    public ChickenPosController() {
        tables.addAll(TableRepository.tables());
    }

    public void run() {
        int mainDisplaySelector = 0;
        OutputView.showMainDisplay();
        mainDisplaySelector = getInputAndCheckOrderMenu();
        orderAndCalculatePrice(mainDisplaySelector);
        }

    private int getInputAndCheckOrderMenu() {
        try {
            int firstUserInput = InputView.InputMainDisplayNumber();
            throwDisplayException(firstUserInput);
            return firstUserInput;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getInputAndCheckOrderMenu();
        }
    }

    private void throwDisplayException(int firstUserInput) {
        validate(firstUserInput == 2, "먼저 메뉴를 등록해주세요.");
        validate(firstUserInput < 1 || firstUserInput > 3, "1부터 3까지만 입력할 수 있습니다.");
    }

    private void orderAndCalculatePrice(int mainDisplaySelector) {
        while (mainDisplaySelector == 1) {
            int tableNumber = showTablesAndReturnNumber();
            showMenus();
            order(tableNumber);
            OutputView.showMainDisplay();
            mainDisplaySelector = InputView.InputMainDisplayNumber();
            calculatePrice(mainDisplaySelector);
            if (mainDisplaySelector == 3) break;
        }
    }

    private void calculatePrice(int mainDisplaySelector) {
        if (mainDisplaySelector == 2) {
            int tableNumberForPay = showTablesAndReturnNumber();
            PayingMachine payingMachine = new PayingMachine(tables.get(tableNumberForPay));
            payingMachine.getOrderDetails();
            OutputView.showPayStartingMessage(payingMachine.getTableNumber());
            int inputToPayCashOrCard = InputView.inputToPayCashOrCard();
            OutputView.showPayingAmount(payingMachine.giveADiscount(inputToPayCashOrCard));
        }
    }

    private int showTablesAndReturnNumber() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);
        return getTableNumber(tables);
    }

    private int getTableNumber(List<Table> tables) {
        try {
            final int tableNumber = InputView.inputTableNumber();
            validate(tableNumber < 0 || tableNumber > tables.size(), "올바른 테이블 넘버를 입력해주세요.");
            return tableNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return showTablesAndReturnNumber();
        }
    }

    private void validate(boolean tableNumber, String s) {
        if (tableNumber) {
            throw new IllegalArgumentException(s);
        }
    }

    private void order(int tableNumber) {
        int inputOrderNumber = InputView.InputOrderNumber();
        int inputMenuCount = InputView.InputMenuCount();
        try {
            addMenu(tableNumber, inputOrderNumber, inputMenuCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            order(tableNumber);
        }
    }

    private void addMenu(int tableNumber, int inputOrderNumber, int inputMenuCount) throws IllegalArgumentException {
        Menu selectedMenu = MenuRepository.getMenu(inputOrderNumber);
        Table targetTable = tables.get(tableNumber);

        if (targetTable.isMenuExists(inputOrderNumber)) {
            targetTable.addCount(inputOrderNumber, inputMenuCount);
            return;
        }
        targetTable.addMenu(new SelectedMenu(selectedMenu, inputMenuCount));
    }

    private void showMenus() {
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }
}
