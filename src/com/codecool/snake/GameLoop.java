package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        /*System.out.println("Game Objects before everything: " + Globals.gameObjects.size());
        System.out.println("New Objects before everything: " + Globals.newGameObjects.size());
        System.out.println("Old Objects before everything: " + Globals.oldGameObjects.size());*/

        Game.checkEntities();

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        /*System.out.println("Game Objects after add: " + Globals.gameObjects.size());
        System.out.println("New Objects after add: " + Globals.newGameObjects.size());
        System.out.println("Old Objects after add: " + Globals.oldGameObjects.size());*/

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();

        /*System.out.println("Game Objects after remove: " + Globals.gameObjects.size());
        System.out.println("New Objects after remove: " + Globals.newGameObjects.size());
        System.out.println("Old Objects after remove: " + Globals.oldGameObjects.size());*/

    }
}
