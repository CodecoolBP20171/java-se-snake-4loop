package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.SnakeHead;

// Interface for animated game entities. If a GameEntity implements this, the step() method will be called
// 60 times per second.
public interface Animatable {

    void step();

 }
