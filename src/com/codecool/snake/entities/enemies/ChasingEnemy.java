package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.*;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

// a simple enemy TODO make better ones.
public class ChasingEnemy extends GameEntity implements Animatable, Interactable, BodyInteractable {

    private Point2D heading;
    private static final int DAMAGE = 10;
    private double direction;
    private double speed;
    private Brain brain;

    private int swingTimer = 60;
    private boolean swingToRight = false;

    public ChasingEnemy(Pane pane) {
        super(pane);

        this.brain = new Brain(Behavior.CHASING, this);

        setImage();
        pane.getChildren().add(this);

        Double[] coords = Utils.getRandomCoordinates();
        setX(coords[0]);
        setY(coords[1]);

        direction = Utils.getRandomDirection();
        setRotate(-30);
        speed = Globals.ENTITY_SPEED;
        heading = Utils.directionToVector(direction, speed);

        Globals.actualEnemies++;
    }

    public void setImage() { setImage(Globals.chasingEnemy); }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        brain.navigate();

        // Swinging motion
        if (swingTimer == 0 || swingTimer == 60) {
            swingToRight = !swingToRight;
        }
        if (swingTimer > 0 && !swingToRight) {
            setRotate(getRotate()+1);
            swingTimer--;
        } else if (swingTimer < 60 && swingToRight) {
            setRotate(getRotate()-1);
            swingTimer++;
        }

        heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        Globals.snakeHeadEntity.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        player.changeHealth(-DAMAGE);
        Double[] coords = {getX(), getY()};
        new BloodSpatter(Globals.pane, coords);
        destroy();
    }

    @Override
    public void apply(SnakeBody snakeBody) {
        Globals.snakeHeadEntity.changeHealth(-DAMAGE/2);
        snakeBody.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
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
        return "10 DAMAGE";
    }


    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    @Override
    public double getDirection() {
        return direction;
    }

}
