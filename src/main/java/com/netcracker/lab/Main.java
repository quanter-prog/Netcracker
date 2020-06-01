package com.netcracker.lab;

import com.netcracker.lab.exceptions.InjectorException;
import com.netcracker.lab.factory.LabFactory;
import com.netcracker.lab.jaxb.XMLParser;

/**
 * Класс - точка входа в программу.
 */
public class Main {

    private static final String PATH = "src/main/resources/persons.csv";
    private static final String XML_PATH = "src/main/resources/repository.xml";

    /**
     * Точка входа в программу.
     * @param args возможные (но необязательные входные значения)
     */
    public static void main(String[] args) {
        try {
            LabFactory factory = new LabFactory();
            CSVLoader loader = new CSVLoader(factory.createPersonRepository(), PATH);
            XMLParser.parseRepository(loader.getRepository(), XML_PATH);
        } catch (InjectorException e) {
            e.printStackTrace();
        }
    }
}
