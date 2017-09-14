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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2.5f;
    private static final float turnRate = 3f;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private static final int MAX_HEALTH = 100;
    private int health;
    private double direction;
    private int score;
    private int player;

    private int damagedAnimationTimer;

    private static int maxShootDelay = 29;

    private int actualShootDelay;

    public SnakeHead(Pane pane, int xc, int yc, int player) {
        super(pane);
        setX(xc);
        setY(yc);
        health = MAX_HEALTH;
        score = 0;
        tail = this;
        this.player = player;

        switch (player) {
            case 1:
                setImage(Globals.snakeHead);
                Globals.isAlive1 = true;
                Globals.snakeHeadEntity = this;
                break;
            case 2:
                setImage(Globals.snakeHead2);
                Globals.isAlive2 = true;
                Globals.snakeHeadEntity2 = this;
                break;
        }

        pane.getChildren().add(this);


        addPart(4);
    }

    public void step() {
        if (actualShootDelay > 0){
            actualShootDelay--;
        }

        // Damaged animation
        if (damagedAnimationTimer > 0) {
            if (getImage().equals(Globals.snakeHead)) {
                setImage(Globals.snakeHeadRed);
            } else if (getImage().equals(Globals.snakeHeadRed)) {
                setImage(Globals.snakeHeadPink);
            } else if (getImage().equals(Globals.snakeHeadPink)) {
                setImage(Globals.snakeHeadWhite);
            } else if (getImage().equals(Globals.snakeHeadWhite)) {
                setImage(Globals.snakeHeadRed);
            }
            damagedAnimationTimer--;
        } else if (!getImage().equals(Globals.snakeHead)) {
            setImage(Globals.snakeHead);
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
        if (Globals.onePlayerMode) {
            if (isOutOfBounds() || health <= 0) {
                Game.showEndScreen(score);
                System.out.println("Game Over");
                Globals.destroyAll();
            }
        } else {
            if (isOutOfBounds() || health <= 0) {
                switch (player) {
                    case 1:
                        Globals.isAlive1 = false;
                        break;
                    case 2:
                        Globals.isAlive2 = false;
                        break;
                }
            }

            if (!Globals.isAlive1 && !Globals.isAlive2) {
                Game.showEndScreen(score);
                System.out.println("Game Over");
                Globals.destroyAll();
            }
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
                } else if (Globals.snakeBodyParts.size() > 16 &&
                        Globals.snakeBodyParts.contains(entity) &&
                        !entity.equals(Globals.snakeBodyParts.get(0)) &&
                        !entity.equals(Globals.snakeBodyParts.get(1)) &&
                        !entity.equals(Globals.snakeBodyParts.get(2)) &&
                        !entity.equals(Globals.snakeBodyParts.get(3))){
                    Game.showEndScreen(score);
                    System.out.println("Game Over");
                    Globals.destroyAll();
                }
            }
        }
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail, player);
            tail = newPart;
            Globals.snakeBodyParts.add(newPart);
        }
    }

    public void changeHealth(int diff) {
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

    public void setDamagedAnimationTimer(int damagedAnimationTimer) {
        this.damagedAnimationTimer = damagedAnimationTimer;
    }

    private boolean getTurnLeftKeyDown() {

        boolean keyPressed = false;
        switch (player) {
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
        switch (player) {
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
        switch (player) {
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
