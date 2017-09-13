package com.codecool.snake.entities;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;


    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }

        GameEntityHandler.decrementEntityCountIfNeeded(this.getClass());

        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getBoundsInParent().getMaxX() > Globals.WINDOW_WIDTH || getBoundsInParent().getMinX() < 0 ||
            getBoundsInParent().getMaxY() > Globals.WINDOW_HEIGHT - 20 || getBoundsInParent().getMinY() < 0) {
            return true;
        }
        return false;
    }

}
