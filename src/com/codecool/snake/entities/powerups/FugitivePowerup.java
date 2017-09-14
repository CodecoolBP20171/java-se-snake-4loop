package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Behavior;
import com.codecool.snake.entities.Brain;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class FugitivePowerup extends MovingPowerup {
    /** this food created in random coordinates and head to random direction
     * if snakeHead close, fleeing opposite direction
     * by catching: snake grow, health increase
     */

    private Brain brain;
    private double speed;
    private static final int VALUE = 10;


    public FugitivePowerup(Pane pane) {
        super(pane);
        this.brain = new Brain(Behavior.FLEEING, this);
        this.speed = Globals.ENTITY_SPEED;
    }

    @Override
    public void setImage() {
        setImage(Globals.fugitivePowerup);
    }


    @Override
    public String getMessage() {
        return "Got FugitivePower-up :)";
    }

    @Override
    public void step() {

        brain.navigate();

        setRotate(getDirection());
        heading = Utils.directionToVector(getDirection(), speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(1);
        snakeHead.setScore(FugitivePowerup.VALUE);
        snakeHead.changeHealth(10);
        destroy();
    }


    public void setSpeed(double speed) {
        this.speed = speed;
    }

}

