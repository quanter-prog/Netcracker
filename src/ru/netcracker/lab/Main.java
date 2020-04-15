package ru.netcracker.lab;

import ru.netcracker.lab.entity.Bank;
import ru.netcracker.lab.entity.Client;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        Bank bank = new Bank();

        /* Функция, генерирующая клиентов с определенной периодичностью. */
        while (true) {
            if (random.nextInt(100000) == 1) {
                Client client = new Client(bank);
                client.start();
            }
        }
    }
}
