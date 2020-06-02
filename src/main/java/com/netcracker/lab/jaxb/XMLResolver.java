package com.netcracker.lab.jaxb;

import com.netcracker.lab.entities.Person;
import com.netcracker.lab.repository.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Класс, экспортирующий xml в PersonRepository.
 */
public class XMLResolver {

    /**
     * Основной метод для экспортирования.
     * @param xml_path путь до xml файла.
     */
    public static Repository resolveXML(String xml_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Repository.class, Person.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (Repository) unmarshaller.unmarshal(new FileReader(xml_path));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
