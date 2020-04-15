package ru.netcracker.lab.entity;

public class Worker {
    private Cashbox cashbox;

    public Worker(Cashbox cashbox) {
        this.cashbox = cashbox;
    }

    public Cashbox getCashbox() {
        return this.cashbox;
    }
}
