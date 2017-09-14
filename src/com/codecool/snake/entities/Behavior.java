package com.codecool.snake.entities;

import static com.codecool.snake.Globals.*;

public enum Behavior {

    CHASING (0, ENTITY_CHASING_SPEED, ENTITY_CHASING_RADIUS),
    FLEEING (180, ENTITY_FLEEING_SPEED, ENTITY_FLEEING_RADIUS),
    HOMING (0, ENTITY_HOMING_SPEED, ENTITY_HOMING_RADIUS);

    private int direction;
    private double speed;
    private  int radius;

    Behavior(int direction, double speed, int radius) {
        this.direction = direction;
        this.speed = speed;
        this.radius = radius;
    }

    public int direction() {
        return direction;
    }

    public double speed() {
        return speed;
    }

    public int radius() {
        return radius;
    }
}
