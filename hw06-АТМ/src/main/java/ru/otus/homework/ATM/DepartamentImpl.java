package ru.otus.homework.ATM;

import java.util.*;

public class DepartamentImpl implements Departament {
    List<ATM> listAtm = new ArrayList<>();
    MementoDepartament mementoDepartament;

    @Override
    public void addATM(int[] cells) {
        listAtm.add(new ATMImpl(cells));
    }

    @Override
    public int balance(Visit visit) {
        return visit.sum(this);
    }

    public ATM getAtm(int i) {
        return listAtm.get(i);
    }

    public List<ATM> getListAtm() {
        return listAtm;
    }

    private class MementoDepartament {
        List<ATM> listAtmMemento=new ArrayList<>();

        private MementoDepartament() {

        }

        private List<ATM> getAccountMemento() {
            return listAtmMemento;
        }

    }

    public void createMementoDepartament() {
        mementoDepartament = new MementoDepartament();
    }

    public void undoMementoDepartament(){

    }

}
