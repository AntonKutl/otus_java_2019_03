package ru.otus.homework.ATM;

public interface Cell {

    int getBanknote();
    void addBanknote();
    int nominalCell();
    int size();
    void createMementoCell();
    void undoMementoCell();

}
