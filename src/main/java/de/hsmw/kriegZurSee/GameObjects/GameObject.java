package de.hsmw.kriegZurSee.GameObjects;

import de.hsmw.kriegZurSee.constants.ID;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class GameObject {
    private final ID ID;
    protected final Rectangle position;
    //hasCooldown here so a Field can switch on UserTurn
    public static boolean hasCooldown = false;


    public GameObject(ID id, int x, int y, double width, double height, javafx.scene.paint.Color color) {
        this.ID = id;
        position = new Rectangle(x, y, width, height);
        position.setFill(color);
    }

    public abstract void tick();

    public void setHasCooldown(){
        hasCooldown = !hasCooldown;
    }

    public void setPos(int x, int y) {
        position.setX(x);
        position.setY(y);
    }
    public void setPos(int x, int y,int width,int height) {
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
    }

    public void setColor(Color color) {
        position.setFill(color);
    }

    public boolean collidesWith(GameObject other) {
        return position.intersects(other.position.getX(), other.position.getY(), other.position.getWidth(), other.position.getHeight());
    }

    public Rectangle getPosition() {
        return position;
    }

    public de.hsmw.kriegZurSee.constants.ID getID() {
        return ID;
    }


}
