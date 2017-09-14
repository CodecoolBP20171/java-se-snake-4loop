package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.BodyInteractable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SimpleEnemy extends GameEntity implements Animatable, Interactable, BodyInteractable {

    private Point2D heading;
    private static final int DAMAGE = 10;
    private double direction;
    private double speed;
    private int attackCoolDown;
    private int recentlySpawned;

    public SimpleEnemy(Pane pane) {

        super(pane);

        pane.getChildren().add(this);

        recentlySpawned = Globals.RECENTLY_SPAWNED_TIME;
        attackCoolDown = 0;

        Double[] coords = Utils.getRandomSideCoordinates();
        setX(coords[0]);
        setY(coords[1]);

        direction = Utils.getRandomSideDirection(coords[0], coords[1]);
        setImage();
        speed = Utils.getRandomSpeed(1, 3);
        heading = Utils.directionToVector(direction, speed);

        Globals.actualEnemies++;
    }

    public void setImage() {
        if (getX() < Globals.WINDOW_WIDTH / 2) {
            setImage(Globals.simpleEnemyToRight);
            setRotate(direction-90);
        } else {
            setImage(Globals.simpleEnemyToLeft);
            setRotate(direction+90);
        }
    }

    @Override
    public void step() {
        if (recentlySpawned < 1 && isOutOfBounds()) {
            destroy();
            return;
        }

        recentlySpawned--;
        heading = Utils.directionToVector(direction, speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        if (attackCoolDown < 1) {
            Globals.snakeHeadEntity.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
            player.changeHealth(-DAMAGE);
            attackCoolDown = Globals.SIMPLE_ENEMY_ATTACK_COOLDOWN;
        } else {
            attackCoolDown--;
        }
    }

    @Override
    public void apply(SnakeBody snakeBody) {
        if (attackCoolDown < 1) {
            Globals.snakeHeadEntity.changeHealth(-DAMAGE /2);
            snakeBody.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
            attackCoolDown = Globals.SIMPLE_ENEMY_ATTACK_COOLDOWN;
        } else {
            attackCoolDown--;
        }
    }

    @Override
    public void apply(Projectile projectile) {
        destroy();
    }

    @Override
    public void setSpeed(double speed) { this.speed = speed; }

    @Override
    public void setDirection(double direction) { this.direction = direction; }

    @Override
    public double getDirection() {
        return direction;
    }

    @Override
    public String getMessage() {
        return "Body Shot";
    }
}
