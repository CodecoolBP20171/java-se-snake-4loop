package com.codecool.snake.entities.powerups;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

/* Stand at a random fix point of the screen until catched
 */
public class SimplePowerup extends GameEntity implements Interactable {

    private static final int VALUE = 2;

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage();
        pane.getChildren().add(this);

        Globals.actualPowerUps++;

        Double[] coord = Utils.getRandomCoordinates();
        setX(coord[0]);
        setY(coord[1]);
    }

    public void setImage() {
        setImage(Globals.simplePowerup);
    }


    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(1);
        snakeHead.setScore(SimplePowerup.VALUE);
        destroy();
    }

    @Override
    public void apply(Projectile projectile) {
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got SimplePower-up :)";
    }

}
