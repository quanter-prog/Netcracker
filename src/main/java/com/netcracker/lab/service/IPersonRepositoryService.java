package com.netcracker.lab.service;

public interface IPersonRepositoryService {

    String getUserNameById(int id);

    long getCountUsersByAge(int age);
}
