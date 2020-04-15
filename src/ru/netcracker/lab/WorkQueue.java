package ru.netcracker.lab;

import ru.netcracker.lab.entity.Cashbox;
import ru.netcracker.lab.entity.Client;
import ru.netcracker.lab.entity.Worker;
import ru.netcracker.lab.exceptions.NotEnoughMoneyException;

import java.util.LinkedList;

public class WorkQueue
{
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedList<Client> queue;

    public WorkQueue(int nThreads, Cashbox cashbox)
    {
        this.nThreads = nThreads;
        queue = new LinkedList<>();
        threads = new PoolWorker[nThreads];

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker(new Worker(cashbox));
            threads[i].start();
        }
    }

    public void addToQueue(Client client) {
        synchronized(queue) {
            queue.addLast(client);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {

        private Worker worker;

        public PoolWorker(Worker worker) {
            this.worker = worker;
        }

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

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    if (client.getStatus()) {
                        worker.getCashbox().addMoney(client.getMoney());
                        System.out.println("Клиент внес " + client.getMoney() + " денег.");
                    } else {
                        worker.getCashbox().getMoney(client.getMoney());
                        System.out.println("Клиент взял " + client.getMoney() + " денег.");
                    }
                    System.out.println("Общее количество денег в банке: " + worker.getCashbox().getMoney());
                    Thread.sleep(client.getServiceTime());
                }
                catch (NotEnoughMoneyException e) {
                    addToQueue(client);
                }
                catch (RuntimeException | InterruptedException e) {
                    // You might want to log something here
                }
            }
        }
    }
}
