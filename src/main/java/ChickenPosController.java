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
        while (mainDisplaySelector == 1) {
            int tableNumber = showTablesAndReturnNumber();
            showMenus();
            order(tableNumber);
            OutputView.showMainDisplay();
            mainDisplaySelector = InputView.InputMainDisplayNumber();
            if (mainDisplaySelector == 2) {
                int tableNumberForPay = showTablesAndReturnNumber();
                PayingMachine payingMachine = new PayingMachine(tables.get(tableNumberForPay));
                OutputView.showOrderDetails(payingMachine.getOrderDetails());
                OutputView.showPayStartingMessage(payingMachine.getTableNumber());
                int inputToPayCashOrCard = InputView.inputToPayCashOrCard();
                OutputView.showPayingAmount(payingMachine.giveADiscount(inputToPayCashOrCard));
            }
        }
    }

    private int getInputAndCheckOrderMenu() {
        try {
            int firstUserInput = InputView.InputMainDisplayNumber();
            if (firstUserInput == 2) {
                throw new IllegalArgumentException("먼저 메뉴를 등록해주세요.");
            }
            return firstUserInput;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getInputAndCheckOrderMenu();
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

    private void addMenu(int tableNumber, int inputOrderNumber, int inputMenuCount) {
        Menu selectedMenu = MenuRepository.getMenu(inputOrderNumber);

        if (tables.get(tableNumber).isMenuExists(inputOrderNumber)) {
            tables.get(tableNumber).addCount(inputOrderNumber, inputMenuCount);
        }
        if (!tables.get(tableNumber).isMenuExists(inputOrderNumber)) {
            tables.get(tableNumber).addMenu(new SelectedMenu(selectedMenu, inputMenuCount));
        }
    }

    private void showMenus() {
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }

    private int showTablesAndReturnNumber() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();
        return tableNumber;
    }

}
