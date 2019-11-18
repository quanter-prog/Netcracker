package com.netcracker.lab.entities;

import java.math.BigDecimal;

import com.netcracker.lab.entities.enums.Gender;


public interface IPerson {

    Integer getId();

    void setId(Integer id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String firstName);

    org.joda.time.LocalDate getBirthdate();

    void setBirthdate(org.joda.time.LocalDate birthdate);

    Integer getAge();

    Gender getGender();

    void setGender(Gender gender);

    IDivision getDivision();

    void setDivision(IDivision division);

    BigDecimal getSalary();

    void setSalary(BigDecimal salary);

}
