package de.hsmw.kriegZurSee.GameObjects;

import de.hsmw.kriegZurSee.constants.ID;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public abstract class GameObject {
    private final ID ID;
    protected final Rectangle position;
    private boolean hasCooldown = false;


    public GameObject(ID id, int x, int y, int width, int height) {
        this.ID = id;
        position = new Rectangle(x, y, width, height);
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    private void setPos(int x, int y, int width, int height){
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
    }


    private boolean collidesWith(GameObject other){
        return position.intersects(other.position.getX(),other.position.getY(),other.position.getWidth(),other.position.getHeight());
    }

    public Rectangle getPosition() {
        return position;
    }

    public de.hsmw.kriegZurSee.constants.ID getID() {
        return ID;
    }

}
