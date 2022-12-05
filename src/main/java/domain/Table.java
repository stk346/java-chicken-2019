package domain;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final int number;
    private final List<SelectedMenu> selectedMenus = new ArrayList<>();

    public Table(final int number) {
        this.number = number;
    }

    public void addMenu(SelectedMenu selectedMenu) {
        selectedMenus.add(selectedMenu);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
