package ru.otus.homework.ATM;

import java.util.*;

public class ATMImpl implements ATM{

    private Set<Cell> set = new TreeSet<>();
    private Memento memento;

    public ATMImpl(int[] cells) {
        for (int temp : cells) {
            set.add(new CellImpl(temp));
        }
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
        createMementoATM();
        for (Cell tempCell : set) {
            while (money >= tempCell.nominalCell() && tempCell.size() > 0) {
                money = money - tempCell.nominalCell();
                tempCell.getBanknote();
                tempSum.add(tempCell.nominalCell());
                sum = sum + tempCell.nominalCell();
            }
        }
        if (sumMoney != sum) {
            undoMementoATM();
            System.out.println("Не хватает банкнот. Отмена операции");
            int[] temp = {0};
            return temp;
        } else return tempSum.stream().mapToInt(i -> i).toArray();

    }

    @Override
    public int printBalance() {
        int balance = 0;
        for (Cell temp : set) {
            balance = balance + temp.size() * temp.nominalCell();
        }
        return balance;
    }

    @Override
    public int accept(Visit visitor) {
        return visitor.visit(this);
    }

    private class Memento {
        private Set<Cell> setMemento = new TreeSet<>();

        private Memento() {
            for (Cell temp : set) {
                Cell tempCell = new CellImpl(temp.nominalCell());
                for (int i = 0; i < temp.size(); i++) {
                    tempCell.addBanknote();
                }
                setMemento.add(tempCell);
            }
        }

        public Set<Cell> getAccountMemento() {
            return setMemento;
        }
    }

    public void createMementoATM() {
        memento = new Memento();
    }

    public void undoMementoATM() {
        set.clear();
        for (Cell temp : memento.getAccountMemento()) {
            Cell tempCell = new CellImpl(temp.nominalCell());
            for (int i = 0; i < temp.size(); i++) {
                tempCell.addBanknote();
            }
            set.add(tempCell);
        }
    }


}
