package ru.otus.homework;

import ru.otus.homework.ATM.Departament;
import ru.otus.homework.ATM.DepartamentImpl;
import ru.otus.homework.ATM.SpecificVisitor;


public class Main {
    public static void main(String[] args) {
        //Иницилизация ячеек банкомата
        int[] cells1 = {5000, 1000, 500, 100, 50, 10};
        int[] cells2 = {5000, 1000, 500, 100,};
        int[] cells3 = {1000, 500, 100, 50};
        int[] money1 = {500, 5000, 100, 10, 1000, 50, 1000, 50};
        int[] money2 = {500, 5000 };
        int[] money3 = {500, 100, 1000, 50, 1000, 50};

        Departament departament=new DepartamentImpl();

        //Добавдение банкоматов в депортамент
        departament.addATM(cells1);
        departament.addATM(cells2);
        departament.addATM(cells3);

        //Добавление денег в банкоматы
        departament.getAtm(0).addMoney(money1);
        departament.getAtm(1).addMoney(money2);
        departament.getAtm(2).addMoney(money3);

        //Отображение остатков в группе банкоматов
        System.out.println("Общий баланс банкоматов: "+departament.balance(new SpecificVisitor()));

        //Сохранение состояния депортамента
        departament.createMementoDepartament();

        //Изьятие денег с банкомата
        departament.getAtm(0).getMoney(10);
        departament.getAtm(0).getMoney(500);
        departament.getAtm(0).getMoney(50);

        //Отображение остатков в группе банкоматов
        System.out.println("Общий баланс банкоматов: "+departament.balance(new SpecificVisitor()));

        //Востановление состояния всех АТМ
        departament.undoMementoDepartament();

        //Отображение остатков в группе банкоматов
        System.out.println("Общий баланс банкоматов: "+departament.balance(new SpecificVisitor()));

    }
}
