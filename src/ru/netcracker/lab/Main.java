package ru.netcracker.lab;

import ru.netcracker.lab.entity.Cashbox;
import ru.netcracker.lab.entity.Client;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Cashbox cashbox = new Cashbox(0);
        WorkQueue workQueue = new WorkQueue(3, cashbox);
        Random random = new Random();
        while (true) {
            if (random.nextInt(10000) == 1) {
                workQueue.addToQueue(new Client());
            }
        }
    }
}
