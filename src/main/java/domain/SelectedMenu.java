package domain;

public class SelectedMenu {
    private final Menu menu;
    private int menuCount;

    public SelectedMenu(Menu menu, int menuCount) {
        this.menu = menu;
        this.menuCount = menuCount;
        validateCount();
    }


    public void addCount(int count) throws IllegalArgumentException{
        validateCount(count);
        menuCount += count;
    }

    private void validateCount(int count) {
        if (menuCount + count > 99) {
            throw new IllegalArgumentException("한 메뉴의 최대 수량은 99개입니다.");
        }
    }

    public void validateCount() {
        if (menuCount > 99) {
            throw new IllegalArgumentException("한 메뉴의 최대 수량은 99개입니다.");
        }
    }

    public int getMenuNumber() {
        return menu.getNumber();
    }

    public int getMenuCount() {
        return menuCount;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getPrice() {
        return menu.getPrice();
    }

    public Category getCategory() {
        return menu.getCategory();
    }
}
