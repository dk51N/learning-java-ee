package com.learning_java_ee.control;

import com.learning_java_ee.entity.Car;
import com.learning_java_ee.entity.Specification;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.UUID;

public class CarFactory {

    @Transactional(rollbackOn = CarStorageException.class)
    public Car createCar(Specification specification) {
        if (new Random().nextBoolean())
            throw new CarCreationException("could not create car");
        Car car = new Car();
        car.setIdentifier(UUID.randomUUID().toString());
        car.setColor(specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }

}
