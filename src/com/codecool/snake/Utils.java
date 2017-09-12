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
        return rnd.nextInt(10) * 60;
    }
}
