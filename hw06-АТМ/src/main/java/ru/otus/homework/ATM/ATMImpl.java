package ru.otus.homework.ATM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ATMImpl implements ATM {

    private List<Cell> list = new ArrayList<>();
    private int[] cells;

    public ATMImpl(int[] cells) {
        Arrays.sort(cells);
        this.cells = cells;
        for (int temp : cells) {
            list.add(new CellImpl(temp));
        }
    }


    @Override
    public void setMoney(int[] money) {
        for (int tempInt : money) {
            for (Cell tempCell : list) {
                if (tempInt == tempCell.valueCell())
                    tempCell.setBanknote();
            }
        }
    }

    @Override
    public int[] getMoney(int money) {
        List<Integer> tempSum = new ArrayList<>();
        int sum = 0;
        int sumMoney = money;
        for (int i = list.size() - 1; i >= 0; i--) {

            while (money >= list.get(i).valueCell() && list.get(i).size() > 0) {
                money = money - list.get(i).valueCell();
                list.get(i).getBanknote();
                tempSum.add(list.get(i).valueCell());
            }
        }
        for (Integer temp : tempSum) {
            sum = sum + temp;
        }
        System.out.println(sum);
        if (sumMoney != sum) {
            throw new RuntimeException("Не хватает банкнот в ячейках");
        }
        return tempSum.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public int printBalance() {
        int balance = 0;
        for (Cell temp : list) {
            //System.out.println(temp.valueCell() + "->" + temp.size());
            balance = balance + temp.size() * temp.valueCell();
        }
        return balance;
    }
}
