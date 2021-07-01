package de.hsmw.kriegZurSee.gameObjects;

import de.hsmw.kriegZurSee.constants.ID;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class GameObject {
    private final ID ID;
    protected final Rectangle position;
    public boolean hasCoolDown = false;


    public GameObject(ID id, int x, int y, double width, double height, javafx.scene.paint.Color color) {
        this.ID = id;
        position = new Rectangle(x, y, width, height);
        position.setFill(color);
    }

    public boolean isHasCoolDown() {
        return hasCoolDown;
    }

    public void setHasCooldown() {
        hasCoolDown = !hasCoolDown;
    }

    //overloaded setPosition method, once with x&y
    public void setPos(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    //once with x,y,width and height
    public void setPos(int x, int y, int width, int height) {
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
    }

    public void setColor(Color color) {
        position.setFill(color);
    }

    //boat collides with other boat if boatPositions intersect with each other
    public final boolean collidesWith(GameObject other) {
        if (position.intersects(other.getPosition().getX(), other.getPosition().getY(), other.getPosition().getWidth(), other.getPosition().getHeight()))
            return true;
        //or :
        return
                //LK(this) links der RK(other)
                position.getX() - 40 < other.getPosition().getX() + other.getPosition().getWidth()
                        &&
                        //RK(this rechts der LK(other)
                        position.getX() + position.getWidth() + 40 > other.getPosition().getX()
                        &&
                        //OK(this über UK(other)
                        position.getY() + position.getHeight() + 40 > other.getPosition().getY()
                        &&
                        //UK(this unter OK(other)
                        position.getY() - 40 < other.getPosition().getY() + other.getPosition().getHeight();
    }


    public Rectangle getPosition() {
        return position;
    }

    public de.hsmw.kriegZurSee.constants.ID getID() {
        return ID;
    }


}
