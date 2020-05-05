package com.learning_java_ee.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static com.learning_java_ee.entity.Car.FIND_ALL;

@Data
@Entity
@Table(name="cars")
@NamedQuery(name = FIND_ALL, query = "select c from Car c")
public class Car {

    public static final String FIND_ALL = "Car.findAll";

    @Id
    private String identifier;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private EngineType engineType;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car", nullable = false)
    private Set<Seat> seats = new HashSet<>();

}
