package com.learning_java_ee.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Seat {
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private SeatMaterial seatMaterial;
    @Embedded
    private SeatBelt seatBelts;
}
