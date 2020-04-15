package ru.netcracker.lab.entity;

import java.util.Random;

public class Client {
    private boolean doesDeposit;
    private long serviceTime;
    private int money;

    public Client() {
        Random random = new Random();
        this.money = random.nextInt(10000);
        this.doesDeposit = random.nextBoolean();
        this.serviceTime = random.nextInt(20000);
    }

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
