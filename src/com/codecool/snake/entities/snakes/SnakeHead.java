package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private double direction;
    private int score;

    private static int maxShootDelay = 29;
    private int actualShootDelay;

    private List<SnakeBody> myBody = new ArrayList<>();

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        score = 0;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        Globals.snakeHeadEntity = this;

        addPart(4);
    }

    public void step() {
        if (actualShootDelay > 0){
            actualShootDelay--;
        }

        direction = getRotate();
        if (Globals.leftKeyDown) {
            direction = direction - turnRate;
        }
        if (Globals.rightKeyDown) {
            direction = direction + turnRate;
        }

        if (Globals.spaceKeyDown && actualShootDelay < 1){
            new Shoot(pane, getX(), getY(), direction);
            actualShootDelay = maxShootDelay;
            Globals.spaceKeyDown = false;
        }

        // set rotation and position
        setRotate(direction);
        Point2D heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                } else if (myBody.size() > 16 &&
                        myBody.contains(entity) &&
                        !entity.equals(myBody.get(0)) &&
                        !entity.equals(myBody.get(1)) &&
                        !entity.equals(myBody.get(2)) &&
                        !entity.equals(myBody.get(3))){
                    Game.showEndScreen(score);
                    System.out.println("Game Over");
                    Globals.destroyAll();
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            Game.showEndScreen(score);
            System.out.println("Game Over");
            Globals.destroyAll();
        }

    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
            myBody.add(newPart);
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void setSpeed(double speed) {
        System.out.println("Cannot do this.");
    }


    public int getHealth() {
        return health;
    }
}
