package com.netcracker.lab.entities;

public class Division implements IDivision {

    private static int id = 0;
    private int divisionId;
    private String name;

    public Division(String name) {
        divisionId = id;
        this.name = name;
        id++;
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
