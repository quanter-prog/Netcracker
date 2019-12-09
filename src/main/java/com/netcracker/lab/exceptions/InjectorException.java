package com.netcracker.lab.exceptions;

public class InjectorException extends Exception{

    public InjectorException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Injector Exception";
    }
}
