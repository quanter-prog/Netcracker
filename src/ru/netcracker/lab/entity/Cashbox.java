package ru.netcracker.lab.entity;

import ru.netcracker.lab.exceptions.NotEnoughMoneyException;

/**
 * Касса банка. Общая.
 */
public class Cashbox {

    /** Деньги. */
    private int money;

    public Cashbox(int money) {
        this.money = money;
    }

    /**
     * Добавление денег.
     * @param money количество денег для изъятия.
     */
    public synchronized void addMoney(int money) {
        this.money += money;
    }

    /**
     * Изъятие денег из банка.
     * @param amount количество денег для изъятия.
     * @throws NotEnoughMoneyException не хватает денег для выдачи.
     */
    public synchronized void getMoney(int amount) throws NotEnoughMoneyException {
        if (amount > this.money) {
            throw new NotEnoughMoneyException();
        } else {
            money -= amount;
        }
    }

    /**
     * Получить все деньги.
     * @return количество денег.
     */
    public synchronized int getMoney() {
        return this.money;
    }
}
