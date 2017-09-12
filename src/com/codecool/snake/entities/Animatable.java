package com.codecool.snake.entities;

// Interface for animated game entities. If a GameEntity implements this, the step() method will be called
// 60 times per second.
public interface Animatable {

    void step();
    void setSpeed(double speed);
    void setDirection(double direction);
    double getDirection();
}
