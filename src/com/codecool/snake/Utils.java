package com.codecool.snake;

import javafx.geometry.Point2D;
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
        Double[] coordinates = new Double[2];
        coordinates[0] = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        coordinates[1] = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        return coordinates;
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
