package ru.netcracker.lab.entity;

import ru.netcracker.lab.exceptions.NotEnoughMoneyException;

public class Cashbox {
    private int money;

    public Cashbox(int money) {
        this.money = money;
    }

    public synchronized void addMoney(int money) {
        this.money += money;
    }

    public synchronized int getMoney(int amount) throws NotEnoughMoneyException {
        if (amount > this.money) {
            throw new NotEnoughMoneyException();
        } else {
            money -= amount;
            return amount;
        }
    }

    public int getMoney() {
        return this.money;
    }
}
