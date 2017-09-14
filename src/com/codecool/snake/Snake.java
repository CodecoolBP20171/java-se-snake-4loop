package com.codecool.snake;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static com.codecool.snake.Globals.root;

public class Snake extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Snake Game");

        Scene scene = new Scene(root, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        // primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));

        ImageView mainTitle = new ImageView();
        mainTitle.fitWidthProperty().bind(scene.widthProperty());
        mainTitle.fitHeightProperty().bind(scene.heightProperty());
        mainTitle.setImage(Globals.title);

        // Create menu bar
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Game");
        Menu menuSettings = new Menu("Settings");

        menuBar.getMenus().addAll(menuFile, menuSettings);

        root.setTop(menuBar);
        root.setCenter(mainTitle);


        // File menu

        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Globals.snakeBodyParts.clear();
                Globals.destroyAll();
                Globals.root.setStyle("-fx-background-color: white;");
                Globals.root.setStyle("-fx-background-image: url(sea1.jpg);");
                Game game = new Game();
                root.setCenter(game);
                game.start();
            }
        });

        newGame.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));


        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });

        exit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));


        // Settings menu

        // Change Background
        Menu playerMode = new Menu("Play Mode");

        final ToggleGroup groupPlayer = new ToggleGroup();

        RadioMenuItem onePlayerMode = new RadioMenuItem("One Player");
        onePlayerMode.setUserData("one");
        onePlayerMode.setSelected(true);
        onePlayerMode.setToggleGroup(groupPlayer);
        playerMode.getItems().add(onePlayerMode);

        RadioMenuItem twoPlayerMode = new RadioMenuItem("Two Players");
        twoPlayerMode.setUserData("two");
        twoPlayerMode.setToggleGroup(groupPlayer);
        playerMode.getItems().add(twoPlayerMode);

        groupPlayer.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groupPlayer.getSelectedToggle() != null) {
                    String userChoice = (String) groupPlayer.getSelectedToggle().getUserData();
                    if (userChoice.equals("one")){
                        Globals.onePlayerMode = true;
                        System.out.println("One: " + Globals.onePlayerMode);
                    } else if (userChoice.equals("two")){
                        Globals.onePlayerMode = false;
                        System.out.println("Two: " + Globals.onePlayerMode);
                    }
                }
            }
        });

        menuSettings.getItems().addAll(playerMode);

        menuFile.getItems().addAll(newGame, new SeparatorMenuItem(), exit);

        primaryStage.setScene(scene);

        primaryStage.show();

    }

}
