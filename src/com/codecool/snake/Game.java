package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.FugitivePowerup;
import com.codecool.snake.entities.powerups.MovingPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);
        Globals.pane = this;

        new SimplePowerup(this);
        new MovingPowerup(this);
        new FugitivePowerup(this);


        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public static void showEndScreen(int score) {

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
