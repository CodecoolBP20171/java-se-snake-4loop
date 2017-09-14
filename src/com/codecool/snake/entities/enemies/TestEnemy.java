package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.*;
import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class TestEnemy extends GameEntity implements Animatable, Interactable, BodyInteractable {

    private Point2D heading;
    private static final int damage = 10;
    private double direction;
    private double speed;
    private Brain brain;

    private boolean paralyzed;
    private int paralyzedRoundCounter = 180;

    public TestEnemy(Pane pane) {
        super(pane);

        this.brain = new Brain(Behavior.HOMING, this);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        Double[] coords = Utils.getRandomCoordinates();
        setX(coords[0]);
        setY(coords[1]);

        speed = Globals.ENTITY_HOMING_SPEED;
        direction = Utils.getRandomDirection();
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        paralyzed = false;
    }

    @Override
    public void step() {
        if (paralyzedRoundCounter < 1){
            paralyzed = false;
            setImage(Globals.simpleEnemy);
            paralyzedRoundCounter = 180;
        }

        if (paralyzed){
            speed = 0;
            paralyzedRoundCounter--;
        } else {
            speed = 0.8;
            if (isOutOfBounds()) {
                destroy();
            }


            brain.navigate();

            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);

            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }

    }

    @Override
    public void apply(SnakeHead player) {
        if (paralyzed){
            // TODO: put here Jeannie's changeScore method
            System.out.println("Score increase");
        } else {
            player.changeHealth(-damage);
            Globals.snakeHeadEntity.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        }
        destroy();
    }

    @Override
    public void apply(Projectile projectile) {
        System.out.println("SHOOTED");
        if (paralyzed){
            System.out.println("I am dead.");
            paralyzedRoundCounter = 180;
        } else {
            System.out.println("Im am paralized");
            paralyzed = true;
            setImage(Globals.paralyzedSimpleEnemy);

        }
    }

    @Override
    public void apply(SnakeBody snakeBody) {
        Globals.snakeHeadEntity.changeHealth(-5);
        snakeBody.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
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
