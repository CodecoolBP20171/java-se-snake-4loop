package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.*;
import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.projectile.ProjectileType;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class ShootingEnemy extends GameEntity implements Animatable, Interactable, BodyInteractable {

    private static final int DAMAGE = 10;
    private int shootingCooldown;

    public ShootingEnemy(Pane pane) {
        super(pane);
        shootingCooldown = Globals.ENEMY_SHOOTING_COOLDOWN;

        setImage();
        pane.getChildren().add(this);

        Double[] coords = Utils.getRandomBottomCoordinates();
        setX(coords[0]);
        setY(coords[1]);

        Globals.actualEnemies++;
    }

    public void setImage() { setImage(Globals.shootingEnemy); }

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
    public void setSpeed(double speed) {}

    @Override
    public void setDirection(double direction) {}

    @Override
    public double getDirection() {
        return 0;
    }

    @Override
    public void apply(SnakeBody snakeBody) {
        Globals.snakeHeadEntity.changeHealth(-DAMAGE /2);
        snakeBody.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);

        Double[] coords = {getX(), getY()};
        new BloodSpatter(Globals.pane, coords);
        destroy();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        Globals.snakeHeadEntity.changeHealth(-DAMAGE);
        Globals.snakeHeadEntity.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        Double[] coords = {getX(), getY()};
        new BloodSpatter(Globals.pane, coords);
        destroy();
    }

    @Override
    public void apply(Projectile projectile) {
        Double[] coords = {getX(), getY()};
        new BloodSpatter(Globals.pane, coords);
        destroy();
    }

    @Override
    public String getMessage() {
        return "ShootingEnemy contacted.";
    }
}
