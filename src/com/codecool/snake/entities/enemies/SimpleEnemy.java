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
    private static final int damage = 10;
    private double direction;
    private double speed;
    private int attackCoolDown;
    private int recentlySpawned;

    public SimpleEnemy(Pane pane) {

        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        recentlySpawned = Globals.RECENTLY_SPAWNED_TIME;

        Double[] coords = Utils.getRandomSideCoordinates(); //
        setX(coords[0]);
        setY(coords[1]);

        direction = Utils.getRandomSideDirection(coords[0], coords[1]);
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        speed = Utils.getRandomSpeed(1, 3);

    }

    @Override
    public void step() {
        if (recentlySpawned < 1 && isOutOfBounds()) {
            destroy();
            return;
        }

        recentlySpawned--;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        if (attackCoolDown < 1) {
            // TODO: biting "animation" and/or sound
            player.changeHealth(-damage);
            attackCoolDown = Globals.SIMPLE_ENEMY_ATTACK_COOLDOWN;
        } else {
            attackCoolDown--;
        }
        // destroy();
    }

    @Override
    public void apply(SnakeBody player) {
        if (attackCoolDown < 1) {
            // TODO: biting "animation" and/or sound
            Globals.snakeHeadEntity.changeHealth(-damage/2);
            attackCoolDown = Globals.SIMPLE_ENEMY_ATTACK_COOLDOWN;
        } else {
            attackCoolDown--;
        }
        // destroy();
    }

    @Override
    public void apply(Projectile projectile) {
        destroy();
    }

    @Override
    public void setSpeed(double speed) {

    }

    @Override
    public void setDirection(double direction) {

    }

    @Override
    public double getDirection() {
        return 0;
    }

    @Override
    public String getMessage() {
        return "Body Shot";
    }
}
