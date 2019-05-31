package ru.otus.homework.ATM;

import java.util.Set;

public interface ATM {

    int[] getMoney(int money);

    void addMoney(int[] money);

    int printBalance();

}
