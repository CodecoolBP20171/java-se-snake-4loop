package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class MovingPowerup extends SimplePowerup implements Animatable {
    /** this food created in random coordinates and head to random direction
     * and pass over the screen, disappears when outOfBounds
     */

    private Point2D heading;
    private static final int VALUE = 3;
    private static final double SPEED = 0.5;

    public MovingPowerup(Pane pane) {
        super(pane);
        setDirection();
    }


    protected void setDirection() {
        double direction = Globals.getRandomDirection();
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
    }

    @Override
    public String getMessage() {
        return "Got MovingPower-up :)";
    }

}
