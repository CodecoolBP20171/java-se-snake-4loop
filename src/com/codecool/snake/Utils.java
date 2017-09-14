package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    private static Random rnd = new Random();

    /*
        Converts a direction in degrees (0...360) to x and y coordinates.
        The length of this vector is the second parameter
        */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static double deltaCoordsToDirection(double deltaX, double deltaY) {
        return Math.atan2(deltaY, deltaX) * (180 / Math.PI) + 90;
    }

    public static Double getRandomDirection() {
        return rnd.nextDouble() * 360;
    }

    public static int getRandomInt(int bound) {
        return rnd.nextInt(bound);
    }

    public static Double[] getRandomCoordinates() {
        SnakeHead snakeHead = Globals.snakeHeadEntity;
        List<SnakeBody> bodyParts = Globals.snakeBodyParts;
        Double[] coordinates = new Double[2];
        int padding = 100;

        do {
            coordinates[0] = padding + rnd.nextDouble() * (Globals.WINDOW_WIDTH - padding*2);
            coordinates[1] = padding + rnd.nextDouble() * (Globals.WINDOW_HEIGHT - padding*2);
        } while (isCloseToSnake(snakeHead, bodyParts, coordinates));

        return coordinates;
    }

    private static boolean isCloseToSnake(SnakeHead snakeHead, List<SnakeBody> bodyParts, Double[] coordinates) {

        if (Math.abs(snakeHead.getY() - coordinates[1]) < 150 && Math.abs(snakeHead.getX() - coordinates[0]) < 150) {
            return true;
        }

        for (int i = 0; i < bodyParts.size(); i++) {
            if (Math.abs(bodyParts.get(i).getY() - coordinates[1]) < 150 && Math.abs(bodyParts.get(i).getX() - coordinates[0]) < 150) {
                return true;
            }
        }

        return false;
    }

    public static int getRandomTime() {
        return rnd.nextInt(6) * 60;
    }

    public static double getRandomSpeed(double rangeMin, double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * rnd.nextDouble();
    }

    public static Double[] getRandomSideCoordinates() {
        Double[] coordinates = new Double[2];
        double padding = 0.0;
        coordinates[0] = (rnd.nextInt(100) <= 50) ? 0.0 - padding : Globals.WINDOW_WIDTH + padding;
        coordinates[1] =  padding + (Globals.WINDOW_HEIGHT - padding) * rnd.nextDouble();
        return coordinates;
    }

    public static Double getRandomSideDirection(double x, double y) {
        double direction;
        boolean onLeftSide = x < Globals.WINDOW_WIDTH/2;
        boolean closeToTop = y < Globals.WINDOW_HEIGHT/2;
        if (onLeftSide && closeToTop) {
            direction = 120 + (135 - 120) * rnd.nextDouble();
        } else if (onLeftSide && !closeToTop) {
            direction = 30 + (60 - 30) * rnd.nextDouble();
        } else if (!onLeftSide && closeToTop) {
            direction = 210 + (240 - 210) * rnd.nextDouble();
        } else {
            direction = 300 + (330 - 300) * rnd.nextDouble();
        }
        return direction;
    }

    public static Double[] getRandomBottomCoordinates() {
        Double[] coordinates = new Double[2];
        coordinates[0] = 50 + (Globals.WINDOW_WIDTH - 50 - 50) * rnd.nextDouble();
        coordinates[1] = (Globals.WINDOW_HEIGHT - 120) + 30 * rnd.nextDouble();
        return coordinates;
    }

}
