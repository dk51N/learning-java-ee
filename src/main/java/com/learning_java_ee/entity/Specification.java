package com.learning_java_ee.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class Specification {

    @NotNull
    private Color color;

    @NotNull
    @EnvironmentalFriendly
    private EngineType engineType;
}
