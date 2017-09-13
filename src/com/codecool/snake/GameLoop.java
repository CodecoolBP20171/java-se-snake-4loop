package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        healthBar();
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }

        Game.checkEntities();

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();


    }

    void healthBar(){
        Text message = new Text("PLAYER 1   Health: " + Globals.players.get(0).getHealth());
        message.setFill(Color.GREEN);
        message.setFont(Font.font("Sans", FontWeight.BOLD, 14));
        TextFlow textFlow;

        if (Globals.players.size() > 1){
            Text message2 = new Text("PLAYER 2   Health: " + Globals.players.get(1).getHealth());
            message2.setFill(Color.BLUE);
            message2.setFont(Font.font("Sans", FontWeight.BOLD, 14));
            Text separator = new Text("\n");
            textFlow = new TextFlow(message, separator, message2);
        } else {
            textFlow = new TextFlow(message);
        }

        textFlow.setTextAlignment(TextAlignment.LEFT);

        Globals.root.setBottom(textFlow);
    }

}
