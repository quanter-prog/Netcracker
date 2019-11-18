package com.netcracker.lab.entities;

import com.netcracker.lab.entities.enums.Gender;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Данный класс описывает человека.
 */
public class Person implements IPerson {

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
    private String lastName;

    /**
     * Отдел, в котором работает человек.
     */
    private IDivision division;

    /**
     * Дата рождения.
     */
    private org.joda.time.LocalDate birthDate;

    /**
     * Пол человека.
     */
    private Gender gender;

    /**
     * Заработная плата человека.
     */
    private BigDecimal salary;

    /**
     * Конструктор класса.
     * В теле конструктора происходит присваивание соответствующим
     * полям входных значений.
     * Экземпляр класса DateTime - birth является точной датой
     * рождения человека, но используются толька значения года и месяца.
     * @param firstName Имя.
     * @param lastName Фамилия.
     * @param birth Дата рождения. Шаблон: "YYYY-MM-DD".
     * @param gender пол.
     * @param salary заработная плата.
     * @param division отдел.
     */
    public Person(String firstName, String lastName,
                  String birth, Gender gender,
                  BigDecimal salary, IDivision division) {
        this.birthDate = org.joda.time.LocalDate.parse(birth);
        this.firstName = firstName;
        this.division = division;
        this.lastName = lastName;
        this.gender = gender;
        this.salary = salary;
        personId = id;
        id++;
    }

    /**
     * Второй вариант конструктора для более удобного считывания из файла.
     * @param firstName Имя.
     * @param personId Идентификационный номер.
     * @param birthDate Дата рождения.
     * @param gender Пол.
     * @param division Отдел.
     * @param salary Заработная плата.
     */
    public Person(String firstName, int personId, org.joda.time.LocalDate birthDate,
                  Gender gender, IDivision division, BigDecimal salary) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.personId = personId;
        this.division = division;
        this.gender = gender;
        this.salary = salary;
    }

    /**
     * Данный метод возвращает число, отражающее возраст человека.
     * (с точностью до месяца)
     * @return Возраст.
     */
    public final Integer getAge() {
        if (birthDate.getMonthOfYear() < DateTime.now().getMonthOfYear()) {
            return DateTime.now().getYear() - this.birthDate.getYear();
        } else {
            return DateTime.now().getYear() - this.birthDate.getYear() - 1;
        }
    }

    /**
     * Метод доступа к полю, описывающему пол человека.
     * @return Пол человека.
     */
    @Override
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Метод доступа к полю, описывающему пол человека.
     * Устанавливает пол.
     * @param gender Пол.
     */
    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Метод доступа к отделу, в котором работает человек.
     * Возвращает отдел, в котором работает человек.
     * @return Отдел.
     */
    @Override
    public IDivision getDivision() {
        return this.division;
    }

    /**
     * Метод доступа к отделу, в котором работает человек.
     * Позволяет установить отдел.
     * @param division Отдел.
     */
    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    /**
     * Метод доступа к заработной плате человека.
     * @return значение заработной платы человека.
     */
    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Метод доступа к заработной плате человека.
     * @param salary заработная плата.
     */
    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Перегрузка метода toString.
     * @return Имя и фамилия человека через пробел.
     */
    @Override
    public final String toString() {
        return this.firstName + " " + this.getGender() + ", " + this.getAge()
                + " лет, " + this.birthDate.getYear() + " года рождения.";
    }

    /**
     * Метод доступа к id человека.
     * @return id человека.
     */
    @Override
    public Integer getId() {
        return personId;
    }

    /**
     * Метод доступа к id человека.
     * @param id id человека.
     */
    @Override
    public void setId(Integer id) {
        this.personId = id;
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
     * @param firstName новое имя.
     */
    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public org.joda.time.LocalDate getBirthdate() {
        return this.birthDate;
    }

    @Override
    public void setBirthdate(org.joda.time.LocalDate birthdate) {
        this.birthDate = birthdate;
    }
}
