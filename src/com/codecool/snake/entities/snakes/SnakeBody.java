package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.BodyInteractable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.projectile.Projectile;
import com.codecool.snake.entities.projectile.ProjectileType;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeBody extends GameEntity implements Animatable {

    private GameEntity parent;
    private Queue<Vec2d> history = new LinkedList<>();
    private static final int historySize = 10;

    private int damagedAnimationTimer;

    public SnakeBody(Pane pane, GameEntity parent) {
        super(pane);
        this.parent = parent;
        setImage(Globals.snakeBody);

        // place it visually below the current tail
        List<Node> children = pane.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        setX(xc);
        setY(yc);
        for (int i = 0; i < historySize; i++) {
            history.add(new Vec2d(xc, yc));
        }
    }

    public void step() {

        // Damaged animation
        if (damagedAnimationTimer > 0) {
            if (getImage().equals(Globals.snakeBody)) {
                setImage(Globals.snakeBodyRed);
            } else if (getImage().equals(Globals.snakeBodyRed)) {
                setImage(Globals.snakeBodyPink);
            } else if (getImage().equals(Globals.snakeBodyPink)) {
                setImage(Globals.snakeBodyWhite);
            } else if (getImage().equals(Globals.snakeBodyWhite)) {
                setImage(Globals.snakeBodyRed);
            }
            damagedAnimationTimer--;
        } else if (!getImage().equals(Globals.snakeBody)) {
            setImage(Globals.snakeBody);
        }

        Vec2d pos = history.poll(); // remove the oldest item from the history
        setX(pos.x);
        setY(pos.y);
        history.add(new Vec2d(parent.getX(), parent.getY())); // add the parent's current position to the beginning of the history

        // check if collided with an enemy (BodyInteractable)
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof BodyInteractable) {
                    if (entity instanceof Projectile &&
                            (((Projectile)entity).getProjectileType().equals(ProjectileType.SNAKE_PROJECTILE))) {
                        return;
                    }
                    BodyInteractable interactable = (BodyInteractable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }
    }

    public void setSpeed(double speed) {
        System.out.println("Cannot do this.");
    }

    public void setDirection(double direction) {
        System.out.println("Cannot do this.");
    }

    public double getDirection() {
        return 0.0;
    }

    public void setDamagedAnimationTimer(int damagedAnimationTimer) {
        this.damagedAnimationTimer = damagedAnimationTimer;
    }
}
