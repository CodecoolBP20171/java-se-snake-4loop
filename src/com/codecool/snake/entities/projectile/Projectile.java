package com.codecool.snake.entities.projectile;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.BodyInteractable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.ShootingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.enemies.HomingEnemy;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Projectile extends GameEntity implements Animatable, Interactable, BodyInteractable {

    private double shootDir;
    private float speed;
    private int stepCounter;
    private int recentlySpawned;
    private ProjectileType projectileType;

    public Projectile(Pane pane, double x, double y, double dir, ProjectileType projectileType) {
        super(pane);
        this.projectileType = projectileType;
        setX(x);
        setY(y);

        setImage(projectileType.equals(ProjectileType.SNAKE_PROJECTILE) ?
                projectileType.getImage() : Globals.enemyShoot);

        this.speed = projectileType.getSpeed();
        pane.getChildren().add(this);
        shootDir = dir;
        stepCounter = 0;
        setRotate(shootDir);
        this.setVisible(false);
        recentlySpawned = Globals.RECENTLY_SPAWNED_TIME;
    }

    @Override
    public void step() {
        if (isOutOfBounds() && recentlySpawned < 1){
            destroy();
        } else {
            recentlySpawned--;
        }

        if (stepCounter > 15){
            this.setVisible(true);
        }

        // set rotation and position
        setRotate(shootDir);

        Point2D heading = Utils.directionToVector(shootDir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Projectile) {
                    if (!projectileType.equals(((Projectile) entity).getProjectileType())) {
                        Interactable interactable = (Interactable) entity;
                        interactable.apply(this);
                        destroy();
                    }
                }

                if (projectileType.equals(ProjectileType.SNAKE_PROJECTILE)) {
                    if (entity instanceof ChasingEnemy || entity instanceof SimpleEnemy || entity instanceof ShootingEnemy || entity instanceof HomingEnemy) {
                        Interactable interactable = (Interactable) entity;
                        interactable.apply(this);
                        destroy();
                    }
                }
            }
        }

        stepCounter++;
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        Globals.snakeHeadEntity.changeHealth(-10);
        Globals.snakeHeadEntity.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        destroy();
    }

    public void apply(SnakeBody snakeBody) {
        Globals.snakeHeadEntity.changeHealth(-5);
        snakeBody.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        destroy();
    }

    @Override
    public void apply(Projectile projectile) {
        destroy();
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void setSpeed(double speed) {
        System.out.println("Cannot do this.");
    }

    @Override
    public void setDirection(double direction) {
        System.out.println("Cannot do this.");

    }

    @Override
    public double getDirection() {
        return 0;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }
}
