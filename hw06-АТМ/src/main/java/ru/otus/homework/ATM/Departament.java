package ru.otus.homework.ATM;

import java.util.List;

public interface Departament {
    void addATM(int[] cells);
    int balance(Visit visit);
    ATM getAtm(int i);
    List<ATM> getListAtm();
    void createMementoDepartament();
    void undoMementoDepartament();


}
