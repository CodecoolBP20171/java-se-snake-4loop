package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

// class for holding all static stuff
public class Globals {

    public static BorderPane root;
    public static SnakeHead snakeHeadEntity;

    public static String actualTheme = "sea";

    public static final double ENEMY_CHASING_SPEED = 1.0;
    public static final double ENEMY_FLEEING_SPEED = 1.5;
    public static final double ENTITY_CHASING_SPEED = 1.0;
    public static final double ENTITY_FLEEING_SPEED = 1.2;
    public static final double ENTITY_SPEED = 0.7;

    // max entity numbers
    public static final int MAX_ENTITY_NUMBER = 10;
    public static final int MAX_ENEMY_NUMBER = 8;
    public static final int MAX_PUP_NUMBER = 7;
    public static int actualPowerUps;
    public static int actualEnemies;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;
    public static final int SIMPLE_ENEMY_ATTACK_COOLDOWN = 120;
    public static final int RECENTLY_SPAWNED_TIME = 240;
    public static final int ENEMY_SHOOTING_COOLDOWN = 120;

    public static boolean onePlayerMode;

    // entity images
    public static Image title = new Image ("snake_main.png");
    public static Image seaBackground = new Image("sea1.jpg");
    public static Image wildwestBackround = new Image("wildwest.jpg");
    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image snakeShoot = new Image("shoot2.png");

    public static Image simplePowerup;
    public static Image movingPowerup;
    public static Image fugitivePowerup;

    public static Image simpleEnemy;
    public static Image shootingEnemy;
    public static Image chasingEnemy;
    public static Image testEnemy;
    public static Image paralyzedTestEnemy;
    public static Image enemyShoot;

    public static Image powerupBerry = new Image("powerup_berry.png");

    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean spaceKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static Pane pane;

    static List<SnakeHead> players;

    static {
        root = new BorderPane();
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        onePlayerMode = true;
        players = new ArrayList<>();
        if (actualTheme.equals("sea")){
            setSeaTheme();
        } else if (actualTheme.equals("wildwest")){
            setWildwestTheme();
        }
    }

    public static void addGameObject(GameEntity toAdd) { newGameObjects.add(toAdd); }


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
        Globals.players.clear();
    }

    static void setSeaTheme(){

        simpleEnemy = new Image("sea/octopus.png");
        shootingEnemy = new Image("sea/simple_enemy.png");
        chasingEnemy = new Image("sea/goldfish.png");
        testEnemy = new Image("sea/crab.png");
        paralyzedTestEnemy = new Image("sea/crab_poisoned.png");
        enemyShoot = new Image("sea/sea_enemy_projectile.png");

        simplePowerup = new Image("sea/starfish.png");
        movingPowerup = new Image("sea/happyfish.png");
        fugitivePowerup = new Image("sea/goldfish.png");
    }

    static void setWildwestTheme(){

        simpleEnemy = new Image("wildwest/bee.png");
        shootingEnemy = new Image("wildwest/cowboy.png");
        chasingEnemy = new Image("wildwest/hedgehog.png");
        testEnemy = new Image("wildwest/pig.png");
        paralyzedTestEnemy = new Image("wildwest/pig_poisoned.png");
        enemyShoot = new Image("wildwest/wildwest_enemy_projectile.png");

        simplePowerup = new Image("wildwest/lizard.png");
        movingPowerup = new Image("wildwest/ladybird.png");
        fugitivePowerup = new Image("wildwest/butterfly.png");

    }

}
