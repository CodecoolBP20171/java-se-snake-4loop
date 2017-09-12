package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class FugitivePowerup extends MovingPowerup {

    private Point2D heading;
    private static final int VALUE = 4;
    private static int SPEED = 1;


    public FugitivePowerup(Pane pane) {
        super(pane);
    }

    /*@Override
    protected void setDirection() {
        // TODO use brain and set food flee from snake
        double direction = ();
        setRotate(direction);
        heading = Utils.directionToVector(direction, SPEED);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }*/

    @Override
    public String getMessage() {
        return "Got FugitivePower-up :)";
    }
}

