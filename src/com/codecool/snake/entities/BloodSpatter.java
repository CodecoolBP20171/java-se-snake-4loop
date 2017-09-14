package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeBody;
import javafx.scene.layout.Pane;

public class BloodSpatter extends GameEntity implements Animatable {

    private int disappearTimer;

    public BloodSpatter(Pane pane, Double[] coords) {
        super(pane);
        pane.getChildren().add(this);
        setImage(Globals.bloodSpatter);
        setX(coords[0]);
        setY(coords[1]);
        disappearTimer = 100;
        this.toBack();
    }

    @Override
    public void step() {
        if (disappearTimer < 1) {
            destroy();
        } else {
            disappearTimer--;
        }
    }

    @Override
    public void setSpeed(double speed) {

    }

    @Override
    public void setDirection(double direction) {

    }

    @Override
    public double getDirection() {
        return 0;
    }
}
