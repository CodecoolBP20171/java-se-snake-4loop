package com.codecool.snake.entities.projectile;

import javafx.scene.image.Image;

public enum ProjectileType {
    SNAKE_PROJECTILE (new Image("shoot2.png"), 4),
    STATIC_SHOOTER (new Image("shoot2.png"), 2);

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
