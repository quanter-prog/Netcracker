package ru.netcracker.lab.entity;

import java.util.Random;

/**
 * Класс, описывающий клиента банка.
 */
public class Client extends Thread {

    /** Кладет ли деньги на счет в банке? */
    private final boolean doesDeposit;
    /** Время обслуживания. */
    private final long serviceTime;
    /** Деньги. */
    private final int money;
    /** Банк, в который пришел клиент. */
    private final Bank bank;

    public Client(Bank bank) {
        Random random = new Random();

        this.serviceTime = random.nextInt(20000);
        this.money = random.nextInt(10000);
        this.doesDeposit = random.nextBoolean();
        this.bank = bank;
    }

    /**
     * Рабочая функция потока.
     * Клиент становится в очередь на обслуживание в банке.
     */
    @Override
    public void run() {
        bank.getService(this);
    }

    /* ----- Методы доступа ----- */
    public boolean getStatus() {
        return this.doesDeposit;
    }

    public long getServiceTime() {
        return this.serviceTime;
    }

    public int getMoney() {
        return this.money;
    }
}
