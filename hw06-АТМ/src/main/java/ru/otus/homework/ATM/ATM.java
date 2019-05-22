package ru.otus.homework.ATM;

public interface ATM {

    int[] getMoney(int money);

    void setMoney(int[] money);

    int printBalance();
}
