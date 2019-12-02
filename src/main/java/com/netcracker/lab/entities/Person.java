package com.netcracker.lab.entities;

import org.joda.time.DateTime;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

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
    private LocalDate birthDate;

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
        this.birthDate = LocalDate.parse(birth);
        this.firstName = firstName;
        this.division = division;
        this.lastName = lastName;
        this.gender = gender;
        this.salary = salary;
        personId = id;
        id++;
    }

    /**
     * Конструктор.
     * @param firstName имя
     * @param lastName фамилия
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
    public Person(String firstName, int personId, LocalDate birthDate,
                  Gender gender, IDivision division, BigDecimal salary) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.personId = personId;
        this.division = division;
        this.gender = gender;
        this.salary = salary;
    }

    /**
     * Пустой конструктор для фабрики.
     */
    public Person() {
    }

    /**
     * Данный метод возвращает число, отражающее возраст человека.
     * (с точностью до месяца)
     * @return Возраст.
     */
    public final Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public IDivision getDivision() {
        return this.division;
    }

    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public final String toString() {
        return this.firstName + " " + this.getGender() + ", " + this.getAge()
                + " лет, " + this.birthDate.getYear() + " года рождения.";
    }

    @Override
    public Integer getId() {
        return personId;
    }

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
    public LocalDate getBirthdate() {
        return this.birthDate;
    }

    @Override
    public void setBirthdate(LocalDate localDate) {
        this.birthDate = localDate;
    }
}
