package com.codecool.snake.entities;

public enum Behavior {

    CHASING (0),
    FLEEING (180);

    private int direction;

    Behavior(int direction) {
        this.direction = direction;
    }

    public int direction() {
        return direction;
    }
}
