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

    public ChasingEnemy(Pane pane) {
        super(pane);

        this.brain = new Brain(Behavior.CHASING, this);

        setImage();
        pane.getChildren().add(this);

        Double[] coords = Utils.getRandomCoordinates();
        setX(coords[0]);
        setY(coords[1]);

        direction = Utils.getRandomDirection();
        setRotate(direction);
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

        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-DAMAGE);
        destroy();
    }

    @Override
    public void apply(SnakeBody snakeBody) {
        Globals.snakeHeadEntity.changeHealth(-DAMAGE /2);
        destroy();
    }

    @Override
    public void apply(Projectile projectile) {
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
