package com.netcracker.lab.jaxb;

import com.netcracker.lab.repository.PersonRepository;
import ru.vsu.lab.repository.IPersonRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
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
    public static IPersonRepository resolveXML(String xml_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonRepository.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (IPersonRepository) unmarshaller.unmarshal(new FileReader(xml_path));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
