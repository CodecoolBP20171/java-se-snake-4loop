package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.SnakeBody;

public interface BodyInteractable {
    void apply(SnakeBody snakeBody);
    String getMessage();
}
