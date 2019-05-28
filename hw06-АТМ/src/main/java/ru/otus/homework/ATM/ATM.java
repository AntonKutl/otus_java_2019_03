package ru.otus.homework.ATM;

public interface ATM {

    int[] getMoney(int money);

    void addMoney(int[] money);

    int printBalance();
}
