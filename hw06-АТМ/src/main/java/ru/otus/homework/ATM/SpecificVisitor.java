package ru.otus.homework.ATM;

class SpecificVisitor implements Visit{

    @Override
    public int visit(ATM atm) {
        return atm.printBalance();
    }
}
