package domain;

import java.util.List;

public class PayingMachine {

    Table table;
    public PayingMachine(Table table) {
        this.table = table;
    }

    public StringBuilder getOrderDetails() {
        System.out.println("## 주문 내역\n" + "메뉴 수량 금액");
        StringBuilder sb = new StringBuilder();
        for (SelectedMenu selectedMenu : table.getSelectedMenus()) {
            sb.append(selectedMenu.getMenuName() + " " +
                    selectedMenu.getMenuCount() + " " +
                    selectedMenu.getPrice() + "\n");
        }
        return sb;
    }

    public int getTableNumber() {
        return table.getNumber();
    }

    private int getTotalPayment() {
        int sumPrice = 0;
        for (SelectedMenu selectedMenu : table.getSelectedMenus()) {
            sumPrice += selectedMenu.getMenuCount() * selectedMenu.getPrice();
        }
        return sumPrice;
    }

    private double chickenDiscount(int totalPrice) {
        int chickenCount = 0;
        for (SelectedMenu selectedMenu : table.getSelectedMenus()) {
            if (selectedMenu.getCategory() == Category.CHICKEN) {
                chickenCount += selectedMenu.getMenuCount();
            }
        }
        double discountAmount = (Math.floor(chickenCount) * 10) * 10000;
        return totalPrice - discountAmount;
    }

    public double giveADiscount(int code) {
        int originalPrice = getTotalPayment();
        double chickenDiscountAmount = chickenDiscount(originalPrice);
        if (code == 2) {
            return chickenDiscountAmount * 0.95;
        }
        return chickenDiscountAmount;
    }
}
