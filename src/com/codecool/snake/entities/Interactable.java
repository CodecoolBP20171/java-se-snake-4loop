package com.codecool.snake.entities;

import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.snakes.SnakeHead;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(SnakeHead snakeHead);
    void apply(Projectile projectile);

    String getMessage();

}
