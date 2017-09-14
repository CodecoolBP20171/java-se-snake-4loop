package com.codecool.snake.entities.projectile;

import com.codecool.snake.Globals;
import javafx.scene.image.Image;

public enum ProjectileType {
    SNAKE_PROJECTILE (Globals.snakeShoot, 4),
    STATIC_SHOOTER (Globals.enemyShoot, 2);

    private Image image;
    private float speed;

    ProjectileType(Image image, float speed) {
        this.image = image;
        this.speed = speed;
    }

    public Image getImage() {
        return image;
    }

    public float getSpeed() {
        return speed;
    }
}
