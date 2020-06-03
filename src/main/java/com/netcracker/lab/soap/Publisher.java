package com.netcracker.lab.soap;

import com.netcracker.lab.service.impl.PersonRepositoryServiceImpl;

import javax.xml.ws.Endpoint;

public class Publisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/wss/persons", new PersonRepositoryServiceImpl());
    }
}
