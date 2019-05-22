package ru.otus.homework.ATM;

public class CellImpl implements Cell {
    private int value;
    private int account;

    public CellImpl(int par) {
        this.value = par;
    }

    @Override
    public void setBanknote() {
        account++;
    }

    @Override
    public int getBanknote() {
        return account--;
    }


    @Override
    public int valueCell() {
        return value;
    }

    @Override
    public int size() {
        return account;
    }
}
