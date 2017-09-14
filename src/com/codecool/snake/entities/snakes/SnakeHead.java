package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.projectile.ProjectileType;
import com.codecool.snake.entities.projectile.Projectile;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private static final int MAX_HEALTH = 100;
    private int health;
    private double direction;
    private int score;
    private int controlKeys;

    public void setScore(int score) {
        this.score += score;
    }

    private static int maxShootDelay = 29;
    private int actualShootDelay;

    private List<SnakeBody> myBody = new ArrayList<>();

    public SnakeHead(Pane pane, int xc, int yc, int controlKeys) {
        super(pane);
        setX(xc);
        setY(yc);
        health = MAX_HEALTH;
        score = 0;
        tail = this;
        this.controlKeys = controlKeys;
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
        if (getTurnLeftKeyDown()) {
            direction = direction - turnRate;
        }

        if (getTurnRightKeyDown()) {
            direction = direction + turnRate;
        }

        if (getShootKeyDown() && actualShootDelay < 1){
            new Projectile(pane, getX(), getY(), direction, ProjectileType.SNAKE_PROJECTILE);
            actualShootDelay = maxShootDelay;
            Globals.upKeyDown = false;
        }

        // set rotation and position
        setRotate(direction);
        Point2D heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            Game.showEndScreen(score);
            System.out.println("Game Over");
            Globals.destroyAll();
        }

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {

                if (entity instanceof Projectile &&
                        (((Projectile)entity).getProjectileType().equals(ProjectileType.SNAKE_PROJECTILE))) {
                    return;
                }

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
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
            myBody.add(newPart);
        }
    }

    public void changeHealth(int diff) {
        // TODO: if taking damage make damage taker part red for a bit (or whole worm)
        health += diff;
        if (health > MAX_HEALTH) health = MAX_HEALTH;
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


    private boolean getTurnLeftKeyDown() {

        boolean keyPressed = false;
        switch (controlKeys) {
            case 1:
                keyPressed = Globals.leftKeyDown;
                break;
            case 2:
                keyPressed = Globals.qKeyDown;
                break;
        }

        return keyPressed;
    }

    private boolean getTurnRightKeyDown() {

        boolean keyPressed = false;
        switch (controlKeys) {
            case 1:
                keyPressed = Globals.rightKeyDown;
                break;
            case 2:
                keyPressed = Globals.wKeyDown;
                break;
        }

        return keyPressed;
    }

    private boolean getShootKeyDown() {

        boolean keyPressed = false;
        switch (controlKeys) {
            case 1:
                keyPressed = Globals.upKeyDown;
                break;
            case 2:
                keyPressed = Globals.spaceKeyDown;
                break;
        }

        return keyPressed;
    }


}
