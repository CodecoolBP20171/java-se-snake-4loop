package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.*;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private double direction;
    private double speed = 0.8;

    private Brain brain;

    public SimpleEnemy(Pane pane) {
        super(pane);

        this.brain = new Brain(Behavior.CHASING, this);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        int speed = 1;
        Double[] coords = Globals.getRandomCoordinates();
        setX(coords[0]);
        setY(coords[1]);

        direction = rnd.nextDouble() * 360;
        double direction = Globals.getRandomDirection();
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        speed = 0.8;

        brain.navigate();

        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }


    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    @Override
    public double getDirection() {
        return direction;
    }
}
