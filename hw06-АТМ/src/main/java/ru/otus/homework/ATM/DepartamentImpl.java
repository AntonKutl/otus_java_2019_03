package ru.otus.homework.ATM;

import java.util.*;

public class DepartamentImpl implements Departament {
    List<ATM> listAtm = new ArrayList<>();

    @Override
    public void addATM(int[] cells) {
        listAtm.add(new ATMImpl(cells));
    }

    @Override
    public int balance() {
        int sum=0;
        for (ATM atm:listAtm) {
          sum=sum+atm.accept(new SpecificVisitor());
        }
        return sum;
    }

    public ATM getAtm(int i) {
        return listAtm.get(i);
    }

    public void undoMemento(){
        for (ATM atm:listAtm) {
            atm.undoMementoATM();
        }
    }

    public void createMemento(){
        for (ATM atm:listAtm) {
            atm.createMementoATM();
        }
    }

}


