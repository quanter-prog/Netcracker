package com.netcracker.lab.soap;

import com.netcracker.lab.service.IPersonRepositoryService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/wss/persons?wsdl");

        QName qname = new QName("http://impl.service.lab.netcracker.com/","PersonRepositoryServiceImplService");

        Service service = Service.create(url,qname);

        IPersonRepositoryService webserv = service.getPort(IPersonRepositoryService.class);

        System.out.println(webserv.getUserNameById(1));
        System.out.println(webserv.getCountUsersByAge(25));
    }
}
