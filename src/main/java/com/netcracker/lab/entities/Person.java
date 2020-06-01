package com.netcracker.lab.entities;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

/**
 * Данный класс описывает человека.
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements IPerson {

    /** id - идентификационный номер. */
    private static int id = 0;

    /** personid - идентификационный номер человека (экземпляра класса).*/
    private int personId;

    /** firstName - имя человека. */
    private String firstName;

    /** lastName - фамилия человека. */
    private String lastName;

    /** Отдел, в котором работает человек. */
    @XmlElement
    @XmlJavaTypeAdapter(Division.Adapter.class)
    private IDivision division;

    /** Дата рождения. */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate birthDate;

    /** Пол человека. */
    private Gender gender;

    /** Заработная плата человека. */
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

    /** Конструктор по умолчанию. */
    public Person() {
    }

    public static class Adapter extends XmlAdapter<Person,IPerson> {
        public IPerson unmarshal(Person v) { return v; }
        public Person marshal(IPerson v) { return (Person) v; }
    }

    public static class DateAdapter extends XmlAdapter<String,LocalDate> {
        public LocalDate unmarshal(String v) { return LocalDate.parse(v); }
        public String marshal(LocalDate v) { return v.toString(); }
    }

    @Override
    public Integer getAge() {
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
    public String toString() {
        return this.firstName + ", "
                + this.getGender().toString().toLowerCase() + ", "
                + "was born in " + this.birthDate.getYear() + ", "
                + this.getAge() + " y.o.";
    }

    @Override
    public Integer getId() {
        return personId;
    }

    @Override
    public void setId(Integer id) {
        this.personId = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
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
    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getBirthdate() {
        return this.birthDate;
    }

    @Override
    public void setBirthdate(LocalDate localDate) {
        this.birthDate = localDate;
    }
}
