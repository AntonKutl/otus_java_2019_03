package ru.otus.homework.ATM;

public class CellImpl implements Cell,Comparable <CellImpl> {
    private int nominal;
    private int account;
    private Memento memento;

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

    private class Memento {
        int accountMemento;
        private Memento() {
            accountMemento=account;
        }

        public int getAccountMemento() {
            return accountMemento;
        }
    }

    public void createMementoCell(){
        memento=new Memento();
    }

    public void undoMementoCell(){
        account=memento.getAccountMemento();
    }

}
