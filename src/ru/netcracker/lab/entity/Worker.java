package ru.netcracker.lab.entity;

import ru.netcracker.lab.exceptions.NotEnoughMoneyException;

import java.util.LinkedList;

/**
 * Класс, описывающий работника банка.
 */
public class Worker extends Thread {

    /** Очередь клиентов к работнику. */
    private final LinkedList<Client> queue;

    /** Касса. */
    private Cashbox cashbox;

    /**
     * Конструктор класса.
     * @param cashbox касса.
     */
    public Worker(Cashbox cashbox) {
        this.queue = new LinkedList<>();
        this.cashbox = cashbox;
    }

    /**
     * Метод доюавления клиента в очередь к работнику.
     * @param client клиент.
     */
    public void addToQueue(Client client) {
        synchronized(queue) {
            queue.addLast(client);
            queue.notify();
        }
    }

    /**
     * Рабочая функция потока.
     * Работник банка ждёт до тех пор, пока в очереди не появляется клиент.
     * Обслужив одного, работник переходит к следующему или ждёт пока клиент
     * не появится в очереди.
     */
    @Override
    public void run() {
        Client client;

        while (true) {
            synchronized(queue) {
                while (queue.isEmpty()) {
                    try
                    {
                        queue.wait();
                    }
                    catch (InterruptedException ignored)
                    {
                    }
                }

                client = queue.removeFirst();
            }

            try {
                if (client.getStatus()) {
                    this.getCashbox().addMoney(client.getMoney());
                    System.out.println("Клиент внес " + client.getMoney() + " денег.");
                } else {
                    this.getCashbox().getMoney(client.getMoney());
                    System.out.println("Клиент взял " + client.getMoney() + " денег.");
                }
                System.out.println("Общее количество денег в банке: " + this.getCashbox().getMoney());
                Thread.sleep(client.getServiceTime());
            }
            catch (NotEnoughMoneyException e) {
                addToQueue(client);
            }
            catch (RuntimeException | InterruptedException e) {
                //???
            }
        }
    }

    /* ----- Методы доступа ----- */
    public Cashbox getCashbox() {
        return this.cashbox;
    }

    public LinkedList<Client> getQueue() {
        return this.queue;
    }
}