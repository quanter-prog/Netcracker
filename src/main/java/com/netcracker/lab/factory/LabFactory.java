package com.netcracker.lab.factory;

import com.netcracker.lab.entities.Division;
import com.netcracker.lab.entities.Person;
import com.netcracker.lab.exceptions.InjectorException;
import com.netcracker.lab.inject.Injector;
import com.netcracker.lab.repository.PersonRepository;
import com.netcracker.lab.repository.Repository;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

/**
 * Фабрика.
 */
public class LabFactory implements ILabFactory {
    /**
     * Create {@link IPerson} entity
     *
     * @return {@link IPerson} entity
     */
    @Override
    public IPerson createPerson() {
        return new Person();
    }

    /**
     * @return {@link IDivision} entity
     */
    @Override
    public IDivision createDivision() {
        return new Division();
    }

    @Override
    public <T> IRepository<T> createRepository(Class<T> clazz) {
        return new Repository<T>();
    }

    @Override
    public IPersonRepository createPersonRepository() throws InjectorException {
        return new Injector().inject(new PersonRepository());
    }
}
