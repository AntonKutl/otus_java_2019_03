package ru.otus.homework.ATM;

public class CellImpl implements Cell,Comparable <CellImpl> {
    private int nominal;
    private int account;

    public CellImpl(int par) {
        this.nominal = par;
    }

    @Override
    public void addBanknote() {
        account++;
    }

    @Override
    public int getBanknote() {
        return account--;
    }

    @Override
    public int nominalCell() {
        return nominal;
    }

    @Override
    public int size() {
        return account;
    }

    @Override
    public int compareTo(CellImpl o) {
        return o.nominal-nominal;
    }

}
