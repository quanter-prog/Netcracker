package com.netcracker.lab.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IPersonRepositoryService {

    @WebMethod
    String getUserNameById(int id);

    @WebMethod
    long getCountUsersByAge(int age);
}
