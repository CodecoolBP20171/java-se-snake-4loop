package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

    /* this food created in random coordinates and pass over the screen
     * disappears when outOfBounds
     */
public class MovingPowerup extends SimplePowerup implements Animatable {

        protected Point2D heading;
        private double direction;
        private static final int VALUE = 5;
        private static double speed;
        private int recentlySpawned;

        public MovingPowerup(Pane pane) {
            super(pane);
            this.speed = Globals.ENTITY_SPEED;

            Double[] coords = Utils.getRandomSideCoordinates();
            setX(coords[0]);
            setY(coords[1]);
            this.direction = Utils.getRandomSideDirection(coords[0], coords[1]);

            setRotate(direction);
            this.heading = Utils.directionToVector(direction, speed);

            this.recentlySpawned = Globals.RECENTLY_SPAWNED_TIME;

        }

        @Override
        public void setImage() {
            setImage(Globals.movingPowerup);
        }

        @Override
        public void setSpeed(double speed) {
            MovingPowerup.speed = speed;
        }

        @Override
        public void setDirection(double direction) {
            this.direction = direction;
            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
        }

        @Override
        public double getDirection() {
            return this.direction;
        }

        @Override
        public void step() {
            if (recentlySpawned < 1 && isOutOfBounds()) {
                destroy();
            } else {
                recentlySpawned--;
                setRotate(direction);
                heading = Utils.directionToVector(direction, speed);

                setX(getX() + heading.getX());
                setY(getY() + heading.getY());
            }
        }

        @Override
        public void apply(SnakeHead snakeHead) {
            snakeHead.addPart(1);
            snakeHead.setScore(MovingPowerup.VALUE);
            destroy();
        }

        @Override
        public String getMessage() {
            return "Got MovingPower-up :)";
        }
    }