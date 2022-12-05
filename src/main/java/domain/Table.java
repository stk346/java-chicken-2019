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

    public boolean isMenuExists(int menuNumber) {
        boolean flag = false;
        for (int idx = 0; idx < selectedMenus.size(); idx++) {
            flag =  selectedMenus.get(idx).getMenuNumber() == menuNumber + 1;
        }
        return flag;
    }

    public void addCount(int menuNumber, int count) {
        for (SelectedMenu selectedMenu : selectedMenus) {
            if (selectedMenu.getMenuNumber() == menuNumber + 1) {
                selectedMenu.addCount(count);
            }
        }
    }

    public List<SelectedMenu> getSelectedMenus() {
        return selectedMenus;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public int getNumber() {
        return number;
    }
}
