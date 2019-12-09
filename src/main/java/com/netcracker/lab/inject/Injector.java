package com.netcracker.lab.inject;

import com.netcracker.lab.exceptions.InjectorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс - инъектор зависимостей.
 */
public class Injector {

    /**
     * Инъекция зависимостей.
     * @param o Произвольный объект.
     * @param <T> Произвольный тип.
     * @return Инъектированный класс.
     */
    public <T> T inject(T o) throws InjectorException {
        Class objectClass = o.getClass();
        Field[] objectFields = objectClass.getDeclaredFields();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream
                    ("src/main/resources/configuration.properties"));
        } catch (IOException ex) {
            throw new InjectorException(ex.getMessage());
        }

        for (Field field : objectFields) {
            if (field.isAnnotationPresent(LabInject.class)) {
                try {
                    field.setAccessible(true);
                    String targetClassName =
                            properties.get(field.getType().getName()).toString();
                    Class targetClass = Class.forName(targetClassName);
                    field.set(o, targetClass.newInstance());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                    throw new InjectorException(ex.getMessage());
                }
            }
        }
        return o;
    }
}
