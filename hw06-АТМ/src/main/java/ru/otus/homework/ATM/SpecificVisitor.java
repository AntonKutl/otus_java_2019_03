package ru.otus.homework.ATM;

public class SpecificVisitor implements Visit{
    int sum=0;

    @Override
    public int sum(Departament departament) {
        for (ATM tempAtm:departament.getListAtm()) {
            sum=sum+tempAtm.printBalance();
        }
        return sum;
    }
}
