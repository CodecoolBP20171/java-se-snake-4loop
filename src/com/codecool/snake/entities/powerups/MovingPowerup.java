package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class MovingPowerup extends SimplePowerup implements Animatable {
    /** this food created in random coordinates and head to random direction
     * and pass over the screen, disappears when outOfBounds
     */

    protected Point2D heading;
    private double direction;
    private static final int VALUE = 5;
    private static double speed = Globals.ENTITY_SPEED;

    public MovingPowerup(Pane pane) {
        super(pane);
        setDirection();
    }

    @Override
    protected void setImage() {
        setImage(Globals.movingPowerup);
    }

    @Override
    public void setSpeed(double speed) {
        MovingPowerup.speed = speed;
    }

    @Override
    public void setDirection(double direction) {
        this.direction = direction;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    public void setDirection() {
        setDirection(Utils.getRandomDirection());
    }

    @Override
    public double getDirection() {
        return direction;
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
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(1);
        snakeHead.setScore(MovingPowerup.VALUE);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got MovingPower-up :)";
    }

}
