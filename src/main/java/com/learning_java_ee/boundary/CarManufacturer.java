package com.learning_java_ee.boundary;

import com.learning_java_ee.control.CarFactory;
import com.learning_java_ee.control.CarStorageException;
import com.learning_java_ee.control.Tracked;
import com.learning_java_ee.entity.Car;
import com.learning_java_ee.control.ProcessTracker;
import com.learning_java_ee.entity.Specification;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @PersistenceContext(unitName = "prod")
    EntityManager entityManager;

    @Tracked(ProcessTracker.Category.MANUFACTURER)
    public Car manufactureCar(Specification specification) throws CarStorageException {
        Car car = carFactory.createCar(specification);
        entityManager.persist(car);
//        throw new CarStorageException("!");
        return car;

    }

    public List<Car> retrieveCars() {
        return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
    }
//
//    public Car retrieveCar(String identifier) {
//        Car car = new Car();
//        car.setIdentifier(identifier);
//        return car;
//    }
}
