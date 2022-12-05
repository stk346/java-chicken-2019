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
        boolean keepOrder = false;
        OutputView.showMainDisplay();
        int firstUserInput = getInputAndCheckOrderMenu();
        if (firstUserInput == 0) keepOrder = true;
        while (keepOrder) {
            int tableNumber = showTablesAndReturnNumber();
            showMenus();
            order(tableNumber);
            OutputView.showMainDisplay();
            if (InputView.InputMainDisplayNumber() != 0) {
                keepOrder = false;
            }
        }
    }

    private int getInputAndCheckOrderMenu() {
        try {
            int firstUserInput = InputView.InputMainDisplayNumber();
            if (firstUserInput == 1) {
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
        Menu selectedMenu = MenuRepository.getMenu(inputOrderNumber);

        tables.get(tableNumber-1).addMenu(new SelectedMenu(selectedMenu, inputMenuCount));
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
