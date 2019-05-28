package ru.otus.homework.ATM;

import java.util.Set;
import java.util.TreeSet;

public class DepartamentImpl implements Departament {
    Set <ATM> setAtm=new TreeSet<>();

    @Override
    public void addATM(int[] cells) {
        setAtm.add(new ATMImpl(cells));
    }

    @Override
    public int collectSum() {
        return 0;
    }
}
