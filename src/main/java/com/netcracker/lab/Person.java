package com.netcracker.lab;

import org.joda.time.DateTime;

/**
 * Данный класс описывает человека.
 */
public class Person {

    /**
     * id - идентификационный номер.
     */
    private static int id = 0;

    /**
     * personid - идентификационный номер человека (экземпляра класса).
     */
    private int personId;

    /**
     * firstName - имя человека.
     */
    private String firstName;

    /**
     * secondName - фамилия человека.
     */
    private String secondName;

    /**
     * birthYear - год рождения.
     */
    private int birthYear;

    /**
     * birthMonth - месяц рождения.
     */
    private int birthMonth;

    /**
     * Конструктор класса.
     * В теле конструктора происходит присваивание соответствующим
     * полям входных значений.
     * Экземпляр класса DateTime - birth является точной датой
     * рождения человека, но используются толька значения года и месяца.
     * @param personFirstName Имя.
     * @param personSecondName Фамилия.
     * @param birth Дата рождения (DateTime из библиотеки joda).
     */
    public Person(final String personFirstName, final String personSecondName,
                  final DateTime birth) {
        this.firstName = personFirstName;
        this.secondName = personSecondName;
        this.birthYear = birth.getYear();
        this.birthMonth = birth.getMonthOfYear();
        personId = id;
        id++;
    }

    /**
     * Данный метод возвращает число, отражающее возраст человека.
     * (с точностью до месяца)
     * @return Возраст.
     */
    public final int getAge() {
        if (birthMonth < DateTime.now().getMonthOfYear()) {
            return DateTime.now().getYear() - this.birthYear;
        } else {
            return DateTime.now().getYear() - this.birthYear - 1;
        }
    }

    /**
     * Перегрузка метода toString.
     * @return Имя и фамилия человека через пробел.
     */
    @Override
    public final String toString() {
        return this.firstName + " " + this.secondName;
    }

    /**
     * Метод доступа к полю firstName.
     * @return Имя.
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Метод доступа для смены имени.
     * @param personFirstName новое имя.
     */
    public final void setFirstName(final String personFirstName) {
        this.firstName = personFirstName;
    }

    /**
     * Метод доступа к полю secondName.
     * @return Фамилия.
     */
    public final String getSecondName() {
        return secondName;
    }

    /**
     * Метод доступа для смены фамилии.
     * @param personSecondName новая фамилия.
     */
    public final void setSecondName(final String personSecondName) {
        this.secondName = personSecondName;
    }

    /**
     * Метод доступа к полю birthYear.
     * @return Год рождения.
     */
    public final int getBirthYear() {
        return birthYear;
    }

    /**
     * Метод доступа для смены года рождения.
     * @param personBirthYear новый год рождения.
     */
    public final void setBirthYear(final int personBirthYear) {
        this.birthYear = personBirthYear;
    }

    /**
     * Метод доступа к полю personId.
     * @return идентификационный номер.
     */
    public final int getPersonId() {
        return personId;
    }
}
