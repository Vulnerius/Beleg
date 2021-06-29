package de.hsmw.kriegZurSee.GameObjects;

import de.hsmw.kriegZurSee.constants.ID;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class GameObject {
    private final ID ID;
    protected final Rectangle position;
    //hasCooldown here so a Field can switch on UserTurn
    public boolean hasCooldown = false;


    public GameObject(ID id, int x, int y, double width, double height, javafx.scene.paint.Color color) {
        this.ID = id;
        position = new Rectangle(x, y, width, height);
        position.setFill(color);
    }

    public abstract void tick();

    public boolean isHasCooldown() {
        return hasCooldown;
    }

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

    public final boolean collidesWith(GameObject other) {
        return
                //LK(this(incl Deadzone)) links der RK(other)
                this.position.getX() - 40 < other.position.getX() + other.getPosition().getWidth()
                        &&
                        //RK(this(incl Deadzone) rechts der LK(other)
                        this.position.getX() + this.getPosition().getWidth() + 40 > other.position.getX()
                        &&
                        //OK(this(incl Deadzone) über UK(other)
                        this.position.getY() + this.getPosition().getHeight() + 40 > other.position.getY()
                        &&
                        //UK(this(incl Deadzone) unter OK(other)
                        this.position.getY() - 40 < other.position.getY() + other.getPosition().getHeight();
    }


    public Rectangle getPosition() {
        return position;
    }

    public de.hsmw.kriegZurSee.constants.ID getID() {
        return ID;
    }


}
