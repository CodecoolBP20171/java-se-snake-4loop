package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// class for holding all static stuff
public class Globals {

    public static SnakeHead snakeHeadEntity;

    public static final double ENEMY_CHASING_SPEED = 1.0;
    public static final double ENEMY_FLEEING_SPEED = 1.0;
    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static boolean onePlayerMode;

    public static Image title = new Image ("snake_main.png");
    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static Pane pane;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        onePlayerMode = true;
    }

    public static void addGameObject(GameEntity toAdd) { newGameObjects.add(toAdd); }
    private static Random rnd = new Random();

    public static Double getRandomDirection() {
        return rnd.nextDouble() * 360;
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


    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    public static void destroyAll() {

        if (Globals.gameLoop != null) {
            Globals.gameLoop.stop();
        }

        Globals.newGameObjects.clear();
        Globals.oldGameObjects.clear();
        Globals.gameObjects.clear();
    }
}
