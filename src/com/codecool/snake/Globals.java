package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static BorderPane root;
    public static SnakeHead snakeHeadEntity;
    public static List<SnakeBody> snakeBodyParts = new ArrayList<>();

    public static String actualTheme = "sea";

    public static final double ENTITY_SPEED = 0.7;
    public static final int ENTITY_CHASING_RADIUS = 150;
    public static final int ENTITY_FLEEING_RADIUS = 150;
    public static final int ENTITY_HOMING_RADIUS = 1000;
    public static final double ENTITY_HOMING_SPEED = 0.3;
    public static final double ENTITY_CHASING_SPEED = 1.0;
    public static final double ENTITY_FLEEING_SPEED = 1.3;

    public static final int SIMPLE_ENEMY_ATTACK_COOLDOWN = 120;
    public static final int RECENTLY_SPAWNED_TIME = 240;
    public static final int ENEMY_SHOOTING_COOLDOWN = 300;
    public static final int DAMAGED_ANIMATION_TIME = 20;

    // max entity numbers
    public static final int MAX_ENTITY_NUMBER = 10;
    public static final int MAX_ENEMY_NUMBER = 8;
    public static final int MAX_PUP_NUMBER = 7;
    public static int actualPowerUps = 0;
    public static int actualEnemies = 0;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static boolean onePlayerMode;

    public static Image bloodSpatter = new Image("blood_spatter.png");
    public static Image snakeHeadRed = new Image("snake_head_red.png");
    public static Image snakeHeadPink = new Image("snake_head_pink.png");
    public static Image snakeHeadWhite = new Image("snake_head_white.png");
    public static Image snakeBodyRed = new Image("snake_body_red.png");
    public static Image snakeBodyPink = new Image("snake_body_pink.png");
    public static Image snakeBodyWhite = new Image("snake_body_white.png");

    public static Image title = new Image ("snake_main.png");
    public static Image snakeHead;
    public static Image snakeBody;
    public static Image snakeShoot = new Image("shoot2.png");

    public static Image simplePowerup;
    public static Image movingPowerupLeft;
    public static Image movingPowerupRight;
    public static Image fugitivePowerupLeft;
    public static Image fugitivePowerupRight;

    public static Image simpleEnemyToRight;
    public static Image simpleEnemyToLeft;
    public static Image shootingEnemy;
    public static Image chasingEnemy;
    public static Image testEnemy;
    public static Image paralyzedTestEnemy;
    public static Image enemyShoot;

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

        actualEnemies = 0;
        actualPowerUps = 0;
    }

    static void setSeaTheme(){
        snakeHead = new Image("snake_head_sea_grey.png");
        snakeBody = new Image("snake_body_sea_grey.png");
        simpleEnemyToRight = new Image("sea/shark_right_side.png");
        simpleEnemyToLeft = new Image("sea/shark_left_side.png");
        shootingEnemy = new Image("sea/simple_enemy.png");
        chasingEnemy = new Image("sea/octopus.png");
        testEnemy = new Image("sea/crab.png");
        paralyzedTestEnemy = new Image("sea/crab_poisoned.png");
        enemyShoot = new Image("sea/sea_enemy_projectile.png");

        simplePowerup = new Image("sea/starfish.png");
        movingPowerupLeft = new Image("sea/happyfish_left.png");
        movingPowerupRight = new Image("sea/happyfish_right.png");
        fugitivePowerupLeft = new Image("sea/goldfish_left.png");
        fugitivePowerupRight = new Image("sea/goldfish_right.png");
    }

    static void setWildwestTheme(){
        snakeHead = new Image("snake_head_wild_west.png");
        snakeBody = new Image("snake_body_wild_west.png");
        simpleEnemyToRight = new Image("wildwest/vulture_right.png");
        simpleEnemyToLeft = new Image("wildwest/vulture_left.png");
        shootingEnemy = new Image("wildwest/cowboy.png");
        chasingEnemy = new Image("wildwest/bee.png");
        testEnemy = new Image("wildwest/hedgehog.png");
        paralyzedTestEnemy = new Image("wildwest/hedgehog_poisoned.png");
        enemyShoot = new Image("wildwest/wildwest_enemy_projectile.png");

        simplePowerup = new Image("wildwest/egg.png");
        movingPowerupLeft = new Image("wildwest/mouse_goes_left.png");
        movingPowerupRight = new Image("wildwest/mouse_goes_right.png");
        fugitivePowerupLeft = new Image("wildwest/ladybird_goes_left.png");
        fugitivePowerupRight = new Image("wildwest/ladybird_goes_right.png");
    }
}
