package com.codecool.snake.entities.powerups;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.Behavior;
import com.codecool.snake.entities.Brain;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class FugitivePowerup extends MovingPowerup {
    /** this food created in random coordinates and head to random direction
     * if snakeHead close, fleeing opposite direction
     */

    private Point2D heading;
    private Brain brain;
    private double speed;
    private static final int VALUE = 4;


    public FugitivePowerup(Pane pane) {
        super(pane);
        this.brain = new Brain(Behavior.FLEEING, this);

        speed = 1;

        Double[] coords = Utils.getRandomCoordinates();
        setX(coords[0]);
        setY(coords[1]);
    }


    @Override
    public String getMessage() {
        return "Got FugitivePower-up :)";
    }

    @Override
    public void step() {

        speed = 0.8;

        brain.navigate();

        setRotate(getDirection());
        heading = Utils.directionToVector(getDirection(), speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }


    public void setSpeed(double speed) {
        this.speed = speed;
    }

}

