package ru.otus.homework.ATM;

import java.util.*;

public class ATMImpl implements ATM {

    private Set<Cell> set = new TreeSet<>();

    public ATMImpl(int[] cells) {
        for (int temp : cells) {
            set.add(new CellImpl(temp));
        }
        System.out.println();
    }


    @Override
    public void addMoney(int[] money) {
        for (int tempInt : money) {
            for (Cell tempCell : set) {
                if (tempInt == tempCell.nominalCell())
                    tempCell.addBanknote();
            }
        }
    }

    @Override
    public int[] getMoney(int money) {
        List<Integer> tempSum = new ArrayList<>();
        int sum = 0;
        int sumMoney = money;
        for (Cell tempCell : set) {
            tempCell.createMementoCell();
            while (money >= tempCell.nominalCell() && tempCell.size() > 0) {
                money = money - tempCell.nominalCell();
                tempCell.getBanknote();
                tempSum.add(tempCell.nominalCell());
                sum = sum + tempCell.nominalCell();
            }
        }

        System.out.println(sum);
        if (sumMoney != sum) {
            for (Cell tempCell : set) {
                tempCell.undoMementoCell();
            }
            System.out.println("Не хватает банкнот. Отмена операции");
            int[] temp = {0};
            return temp;
        } else return tempSum.stream().mapToInt(i -> i).toArray();

    }

    @Override
    public int printBalance() {
        /*for (Cell temp:set) {
            System.out.println(temp.nominalCell()+"->"+temp.size());
        }*/

        int balance = 0;
        for (Cell temp : set) {
            balance = balance + temp.size() * temp.nominalCell();
        }
        return balance;
    }
}
