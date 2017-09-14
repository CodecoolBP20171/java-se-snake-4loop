package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.BodyInteractable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.projectile.ProjectileType;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class ShootingEnemy extends GameEntity implements Animatable, Interactable, BodyInteractable {

    private static final int damage = 10;
    private int shootingCooldown;

    public ShootingEnemy(Pane pane) {
        super(pane);
        shootingCooldown = Globals.ENEMY_SHOOTING_COOLDOWN;

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        Double[] coords = Utils.getRandomBottomCoordinates();
        setX(coords[0]);
        setY(coords[1]);
    }

    @Override
    public void step() {
        double deltaX = Globals.snakeHeadEntity.getX() - this.getX();
        double deltaY = Globals.snakeHeadEntity.getY() - this.getY();
        double direction = Utils.deltaCoordsToDirection(deltaX, deltaY);
        if (shootingCooldown < 1) {
            new Projectile(pane, getX(), getY(), direction, ProjectileType.STATIC_SHOOTER);
            shootingCooldown = Globals.ENEMY_SHOOTING_COOLDOWN;
        } else {
            shootingCooldown--;
        }
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
    public void apply(SnakeBody snakeBody) {
        Globals.snakeHeadEntity.changeHealth(-damage/2);
        snakeBody.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        destroy();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        Globals.snakeHeadEntity.changeHealth(-damage);
        Globals.snakeHeadEntity.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        destroy();

    }

    @Override
    public void apply(Projectile projectile) {
        destroy();

    }

    @Override
    public String getMessage() {
        return "ShootingEnemy contacted.";
    }
}
