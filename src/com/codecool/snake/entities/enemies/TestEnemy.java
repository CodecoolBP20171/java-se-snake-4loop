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
    private static final int DAMAGE = 10;
    private static final int SCORE = 10;
    private double direction;
    private double speed;
    private Brain brain;

    private boolean paralyzed;
    private int paralyzedRoundCounter = 180;

    public TestEnemy(Pane pane) {
        super(pane);

        this.brain = new Brain(Behavior.HOMING, this);

        setImage();
        pane.getChildren().add(this);

        Double[] coords = Utils.getRandomCoordinates();
        setX(coords[0]);
        setY(coords[1]);

        speed = Globals.ENTITY_HOMING_SPEED;
        direction = Utils.getRandomDirection();
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        paralyzed = false;

        Globals.actualEnemies++;
    }

    @Override
    public void step() {
        if (paralyzedRoundCounter < 1){
            paralyzed = false;
            setImage();
            paralyzedRoundCounter = 180;
        }

        if (paralyzed){
            speed = 0;
            paralyzedRoundCounter--;
        } else {
            speed = Globals.ENTITY_SPEED;
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

    public void setImage() {setImage(Globals.testEnemy);}

    @Override
    public void apply(SnakeHead player) {
        if (paralyzed){
            player.setScore(SCORE);
            System.out.println("Score increase");
        } else {
            Globals.snakeHeadEntity.setDamagedAnimationTimer(Globals.DAMAGED_ANIMATION_TIME);
            player.changeHealth(-DAMAGE);
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
            setImage(Globals.paralyzedTestEnemy);

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
