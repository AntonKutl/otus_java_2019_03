package ru.otus.homework.ATM;


public interface Departament {
    void addATM(int[] cells);
    int balance();
    ATM getAtm(int i);
    void createMemento();
    void undoMemento();


}
