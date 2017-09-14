package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;

public class Brain {

    private Behavior behavior;
    private GameEntity body;
    private int turnFromEdgeCD;
    private int randomTurnCD;
    private int toTurnRight;
    private int toTurnLeft;

    public Brain(Behavior behavior, GameEntity body) {
        this.behavior = behavior;
        this.body = body;
    }

    public void navigate() {
        SnakeHead snakeHead = Globals.snakeHeadEntity;

        if (turnFromEdgeCD >= 1) {
            turnFromEdgeCD--;
        } else if (isCloseToAnyEdge()) {
            double towardCenterRandom = Utils.deltaCoordsToDirection(Globals.WINDOW_WIDTH/2-body.getX(),
                    Globals.WINDOW_HEIGHT/2-body.getY()) + Utils.getRandomDirection() / 6 - 30;
            ((Animatable) body).setDirection(towardCenterRandom);
            turnFromEdgeCD = 150;
        } else if (randomTurnCD < 1) {

            boolean turnRight = Utils.getRandomInt(100) <= 50;

            if (turnRight) {
                toTurnRight = Utils.getRandomInt(90);
            } else {
                toTurnLeft = Utils.getRandomInt(90);
            }
            randomTurnCD = 240;
        } else if (toTurnRight > 0) {
            ((Animatable) body).setDirection(((Animatable) body).getDirection() + 3);
            toTurnRight -= 3;
        } else if (toTurnLeft > 0) {
            ((Animatable) body).setDirection(((Animatable) body).getDirection() - 3);
            toTurnLeft -= 3;
        }

        if (randomTurnCD >= 1) {
            randomTurnCD--;
        }

        if (isCloseToSnakeHead()) {
            double deltaX = snakeHead.getX() - body.getX();
            double deltaY = snakeHead.getY() - body.getY();
            ((Animatable) body).setDirection(Utils.deltaCoordsToDirection(deltaX, deltaY) + behavior.direction());

            Animatable animatable = (Animatable) body;
            animatable.setSpeed(behavior.speed());
        }

    }

    private boolean isCloseToAnyEdge() {
        return (isCloseToTop(100) || isCloseToBottom(100) ||
                isCloseToRight(100) || isCloseToLeft(100));
    }

    private boolean isCloseToTop(int proximity) {
        return body.getBoundsInParent().getMaxY() < proximity;
    }

    private boolean isCloseToBottom(int proximity) {
        return body.getBoundsInParent().getMaxY() > Globals.WINDOW_HEIGHT - proximity;
    }

    private boolean isCloseToRight(int proximity) {
        return body.getBoundsInParent().getMaxX() < proximity;
    }

    private boolean isCloseToLeft(int proximity) {
        return body.getBoundsInParent().getMaxX() > Globals.WINDOW_WIDTH - proximity;
    }

    private boolean isCloseToSnakeHead() {
        SnakeHead snakeHead = Globals.snakeHeadEntity;
        return (Math.abs(snakeHead.getBoundsInParent().getMaxX() - body.getBoundsInParent().getMaxX()) < behavior.radius() &&
                Math.abs(snakeHead.getBoundsInParent().getMaxY() - body.getBoundsInParent().getMaxY()) < behavior.radius());
    }
}
