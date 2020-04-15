package ru.netcracker.lab.exceptions;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
        System.out.println("В кассе недостаточно денег!");
    }
}
