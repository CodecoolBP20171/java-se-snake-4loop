package com.codecool.snake;


import com.codecool.snake.entities.GameEntityHandler;
import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.FugitivePowerup;
import com.codecool.snake.entities.powerups.MovingPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class Game extends Pane {


    public Game() {

        Globals.players.add(new SnakeHead(this, 500, 500, 1));

        if (!Globals.onePlayerMode) {
            Globals.players.add(new SnakeHead(this, 200, 200, 2));
        }

        Globals.pane = this;

        GameEntityHandler.countdownTimer = Utils.getRandomTime();

        new SimplePowerup(this);
        new MovingPowerup(this);
        new FugitivePowerup(this);

        new ChasingEnemy(this);
        new ChasingEnemy(this);
        /*new ChasingEnemy(this);
        new ChasingEnemy(this);*/

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        /*new SimpleEnemy(this);
        new SimpleEnemy(this);*/

        new SimplePowerup(this);
        /*new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
*/
        /*new ShootingEnemy(this);
        new ShootingEnemy(this);*/
        /*new ShootingEnemy(this);
        new ShootingEnemy(this);*/

        /*new TestEnemy(this);
        new TestEnemy(this);*/
        /*new TestEnemy(this);
        new TestEnemy(this);*/
    }

    public void start() {
        Scene scene = getScene();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case UP: Globals.upKeyDown = true; break;
                case Q: Globals.qKeyDown = true; break;
                case W: Globals.wKeyDown = true; break;
                case SPACE: Globals.spaceKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case UP: Globals.upKeyDown = false; break;
                case Q: Globals.qKeyDown = false; break;
                case W: Globals.wKeyDown = false; break;
                case SPACE: Globals.spaceKeyDown = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public static void showEndScreen(int score) {
        Globals.root.setBottom(null);

        Text message = new Text("Game Over!");
        message.setFill(Color.RED);
        message.setFont(Font.font("Sans", FontWeight.BOLD, FontPosture.ITALIC, 40));

        Text message2 = new Text("Player Score: " + score);
        message2.setFill(Color.BLUE);
        message2.setFont(Font.font("Sans", FontWeight.BOLD, 30));

        Text separator = new Text("\n\n\n");
        TextFlow textFlow = new TextFlow(message, separator, message2);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setStyle("-fx-padding: " + Globals.WINDOW_HEIGHT / 2.40 + " 0 0 0");

        Globals.root.setStyle("-fx-background-color: tan;");


        Globals.root.setCenter(textFlow);

    }


}
