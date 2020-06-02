package com.netcracker.lab;

import com.netcracker.lab.exceptions.InjectorException;
import com.netcracker.lab.factory.LabFactory;
import com.netcracker.lab.jaxb.XMLParser;
import com.netcracker.lab.jaxb.XMLResolver;
import ru.vsu.lab.entities.IPerson;

import java.util.Objects;

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

            // From PersonRepository to XML
            XMLParser.parseRepository(loader.getRepository(), XML_PATH);

            // From XML to PersonRepository
            for (IPerson person : Objects.requireNonNull(XMLResolver.resolveXML(XML_PATH)).toList()) {
                System.out.println(person.toString());
            }
        } catch (InjectorException e) {
            e.printStackTrace();
        }
    }
}
