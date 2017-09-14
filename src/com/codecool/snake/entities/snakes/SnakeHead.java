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

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private static final int MAX_HEALTH = 100;
    private int health;
    private double direction;
    private int score;

    private int damagedAnimationTimer;

    public void setScore(int score) {
        this.score += score;
    }

    private static int maxShootDelay = 29;
    private int actualShootDelay;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = MAX_HEALTH;
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
        if (Globals.leftKeyDown) {
            direction = direction - turnRate;
        }
        if (Globals.rightKeyDown) {
            direction = direction + turnRate;
        }

        if (Globals.spaceKeyDown && actualShootDelay < 1){
            new Projectile(pane, getX(), getY(), direction, ProjectileType.SNAKE_PROJECTILE);
            actualShootDelay = maxShootDelay;
            Globals.spaceKeyDown = false;
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

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
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
}
