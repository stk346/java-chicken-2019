import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class ChickenPosController {

    public void run() {
        showMainDisplay();
        showTables();
        showMenus();
    }

    public void showMainDisplay() {
        OutputView.showMainDisplay();
        InputView.InputMainDisplayNumber();
    }

    private void showMenus() {
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }

    private void showTables() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();
    }

}
