package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Shoot extends GameEntity implements Animatable {

    private double shootDir;
    private static final float speed = 4;
    private int stepCounter;

    protected Shoot(Pane pane, double x, double y, double dir) {
        super(pane);
        setX(x);
        setY(y);
        setImage(Globals.snakeShoot);
        pane.getChildren().add(this);
        shootDir = dir;
        stepCounter = 0;
        setRotate(shootDir);
        this.setVisible(false);
    }

    @Override
    public void step() {
        if (isOutOfBounds()){
            destroy();
        }

        if (stepCounter > 15){
            this.setVisible(true);
        }

        // set rotation and position
        setRotate(shootDir);

        Point2D heading = Utils.directionToVector(shootDir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable && entity instanceof SimpleEnemy) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                }
            }
        }

        stepCounter++;
    }



}
