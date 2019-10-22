package com.netcracker.lab;

import org.joda.time.DateTime;

/**
 * Класс - точка входа в программу.
 */
public class Main {

    /**
     * Точка входа в программу.
     * @param args возможные (но необязательные входные значения)
     */
    public static void main(final String[] args) {
        Repository rep = new Repository();
        /*
        Person human1 = new Person("Илья", "Марков",
                DateTime.now().minusYears(27).minusMonths(3));
        Person human2 = new Person("Петр", "Краснов",
                DateTime.now().minusYears(20).minusMonths(2));
                */
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("2018-05-05T10:11:12.123"));
        //rep.add(human1);
        //rep.add(human2);
        rep.add(human3);
        System.out.println(rep.toString());
    }
}
