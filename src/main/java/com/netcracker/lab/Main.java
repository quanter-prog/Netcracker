package com.netcracker.lab;

import com.netcracker.lab.factory.LabFactory;
import com.netcracker.lab.jaxb.XMLParser;
import com.netcracker.lab.jaxb.XMLResolver;
import com.netcracker.lab.repository.Repository;
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
        LabFactory factory = new LabFactory();
        CSVLoader loader = new CSVLoader(factory.createRepository(IPerson.class), PATH);

        // From PersonRepository to XML
        XMLParser.parseRepository(loader.getRepository(), XML_PATH);

        // From XML to PersonRepository
        Repository repository = XMLResolver.resolveXML(XML_PATH);
        for (Object object : repository.toList()) {
            System.out.println(object.toString());
        }
    }
}
