package com.sebastian_daschner.learning_java_ee.entity;

import javax.validation.constraints.NotNull;

public class Specification {

    @NotNull
    private Color color;

    @NotNull
    @EnvironmentalFriendly
    private EngineType engineType;

    public Specification() {
    }

    public Specification(@NotNull Color color, @NotNull EngineType engineType) {
        this.color = color;
        this.engineType = engineType;
    }

    public Color getColor() {
        return color;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "color=" + color +
                ", engineType=" + engineType +
                '}';
    }
}
