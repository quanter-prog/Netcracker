package com.netcracker.lab.entities;

import ru.vsu.lab.entities.IDivision;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Данный класс описывает отдел (подразделение).
 */
@XmlRootElement(name = "division")
@XmlAccessorType(XmlAccessType.FIELD)
public class Division implements IDivision {

    /**
     * Уникальный идентификатор отдела.
     * Статическое поле, которое увеличивается при создании нового
     * экземпляра класса, а затем присваивается к полю divisionId
     */
    private static int id = 0;

    /** Уникальный идентификатор отдела. */
    private int divisionId;

    /** Название отдела. */
    private String name;

    /** Конструктор класса по умолчанию. */
    public Division() {
    }

    /**
     * Конструктор класса.
     * @param name название отдела.
     */
    public Division(String name) {
        divisionId = id;
        this.name = name;
        id++;
    }

    public static class Adapter extends XmlAdapter<Division, IDivision> {
        public IDivision unmarshal(Division v) { return v; }
        public Division marshal(IDivision v) { return (Division) v; }
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Integer getId() {
        return divisionId;
    }

    @Override
    public void setId(Integer id) {
        divisionId = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
