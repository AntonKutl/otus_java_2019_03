package ru.otus.homework;

import ru.otus.homework.ATM.ATM;
import ru.otus.homework.ATM.ATMImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Иницилизация ячеек банкомата
        int [] cells={5000,1000,500,100,50,10};
        ATM atm=new ATMImpl(cells);
        //Внесение купюр в банкомат
        int[] money={500,5000,100,10,1000,50,1000};
        atm.setMoney(money);
        System.out.println("Баланс банкомата: "+atm.printBalance());
        //Изьятие купюр из банкомата
        System.out.println("Запрашиваемая сумма выдана купюрами:"+Arrays.toString(atm.getMoney(6050)));
        System.out.println("Баланс банкомата: "+atm.printBalance());

    }
}
