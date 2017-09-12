package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Snake extends Application {

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        primaryStage.setTitle("Snake Game");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        // primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));

        // Create menu bar
        




        primaryStage.show();
        game.start();
    }

}
