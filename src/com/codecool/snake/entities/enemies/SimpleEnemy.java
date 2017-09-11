package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Shoot;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    // my new variable:
    private double direction;
    private int speed;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        speed = 1;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        // double direction = rnd.nextDouble() * 360;
        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            if (getX() < 0){
                direction = 360 - direction;
            } else if (getX() > Globals.WINDOW_WIDTH){
                direction = 360 - direction;
            } else if (getY() < 0){
                direction = 180 - direction;
            } else if (getY() > Globals.WINDOW_HEIGHT){
                direction = 180 - direction;
            }

            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public void apply(Shoot shoot) {
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
