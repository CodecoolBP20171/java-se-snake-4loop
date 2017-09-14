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
                Game game = new Game();
                root.setCenter(game);
                game.start();
                if (Globals.actualTheme.equals("sea")){
                    Globals.setSeaTheme();
                    Globals.root.setStyle("-fx-background-image: url('sea1.jpg')");
                } else if (Globals.actualTheme.equals("wildwest")){
                    Globals.setWildwestTheme();
                    Globals.root.setStyle("-fx-background-image: url('wildwest.jpg')");
                }
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

        Menu changeTheme = new Menu("Change Theme Suit");
        final ToggleGroup groupThemes = new ToggleGroup();

        RadioMenuItem seaTheme = new RadioMenuItem("Sea");
        seaTheme.setUserData("sea");
        seaTheme.setToggleGroup(groupThemes);
        changeTheme.getItems().add(seaTheme);

        RadioMenuItem wildwestTheme = new RadioMenuItem("Wild West");
        wildwestTheme.setUserData("wildwest");
        wildwestTheme.setToggleGroup(groupThemes);
        changeTheme.getItems().add(wildwestTheme);

        groupThemes.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groupThemes.getSelectedToggle() != null) {
                    String userChoice = (String) groupThemes.getSelectedToggle().getUserData();
                    if (userChoice.equals("sea")){
                        System.out.println("NEW THEME: SEA");
                        Globals.actualTheme = "sea";
                        Globals.setSeaTheme();
                    } else if (userChoice.equals("wildwest")){
                        System.out.println("NEW THEME: WILDWEST");
                        Globals.actualTheme = "wildwest";
                        Globals.setWildwestTheme();
                    }
                }
            }
        });

        menuSettings.getItems().addAll(playerMode, changeTheme);
        menuFile.getItems().addAll(newGame, new SeparatorMenuItem(), exit);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
