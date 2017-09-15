package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.ShootingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.TestEnemy;
import com.codecool.snake.entities.powerups.FugitivePowerup;
import com.codecool.snake.entities.powerups.MovingPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameEntityHandler {

    public static int countdownTimer = 0;
    private static Queue<String> entityQueue = new LinkedList<>();

    public static void handleEntities() {

        checkEntities();

        if (countdownTimer > 0) {
            countdownTimer--;
        }

        if (countdownTimer == 0) {
            addNewEntityIfNeeded();
        }

        if (entityQueue.size() > 0) {
            addTimer();
        }
    }


    private static void checkEntities() {

        if (entityQueue.size() < Globals.MAX_ENTITY_NUMBER) {
            System.out.println("Entity queue size is less than max entity number");
            int possibleNewEntities = Globals.MAX_ENTITY_NUMBER - Globals.actualPowerUps - Globals.actualEnemies;
            System.out.println("Possible new entities: " + possibleNewEntities);
            List<String> missingEntities = new LinkedList<>();

            if (possibleNewEntities > 0) {
                System.out.println("Possible new entities greater than 0");
                int actualNewEntities = Utils.getRandomInt(possibleNewEntities + 1);
                System.out.println("Random Generated actual new entities: " + actualNewEntities);
                if (actualNewEntities > 0) {
                    System.out.println("Random Actual new entities greater than 0 this turn");
                    int powerupsPossibleMax = Globals.MAX_PUP_NUMBER - Globals.actualPowerUps;
                    int enemiesPossibleMax = Globals.MAX_ENEMY_NUMBER - Globals.actualEnemies;
                    System.out.println("powerupsPossibleMax: " + powerupsPossibleMax);
                    System.out.println("enemiesPossibleMax: " + enemiesPossibleMax);

                    for (int i = 0; i < powerupsPossibleMax; i++) {
                        missingEntities.add("P");
                    }

                    for (int j = 0; j < enemiesPossibleMax; j++) {
                        missingEntities.add("E");
                    }

                    for (int k = 0; k < actualNewEntities; k++) {
                        int randomMissingEntityIndex = Utils.getRandomInt(missingEntities.size());
                        entityQueue.add(missingEntities.remove(randomMissingEntityIndex));
                    }

                    System.out.println("********************************");
                    System.out.println("entityQueue contains now:");
                    for (String item : entityQueue) {
                        System.out.println(item);
                    }
                    System.out.println("*********************************");

                    //addTimer();

                }

            }

            missingEntities.clear();
        }

    }


    private static void addTimer() {

        if (countdownTimer == 0) {
            countdownTimer = Utils.getRandomTime();
            System.out.println("New countdownTimer: " + countdownTimer);
        }

    }


    private static void addNewEntityIfNeeded() {

        if (entityQueue.size() > 0) {

            if (entityQueue.poll().equals("E")) {

                switch (Utils.getRandomInt(4)) {
                    case 0:
                        new ChasingEnemy(Globals.pane);
                        break;
                    case 1:
                        new ShootingEnemy(Globals.pane);
                        break;
                    case 2:
                        new SimpleEnemy(Globals.pane);
                        break;
                    case 3:
                        new TestEnemy(Globals.pane);
                        break;
                }

            } else {

                switch (Utils.getRandomInt(3)) {
                    case 0:
                        new SimplePowerup(Globals.pane);
                        break;
                    case 1:
                        new MovingPowerup(Globals.pane);
                        break;
                    case 2:
                        new FugitivePowerup(Globals.pane);
                        break;
                }
            }
        }

    }

    public static void decrementEntityCountIfNeeded(String className) {

        switch (className) {
            case "SimplePowerup": case "MovingPowerup": case "FugitivePowerup":
                Globals.actualPowerUps--;
                break;
            case "ChasingEnemy": case "ShootingEnemy": case "SimpleEnemy": case "TestEnemy":
                Globals.actualEnemies--;
        }

    }



}
