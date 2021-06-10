package de.hsmw.kriegZurSee.Boats;

import org.apache.commons.lang3.Range;

import java.awt.*;

public abstract class AbstractBoat {
    protected Point position;
    protected int length;
    private int width, height, coolDown = 0;
    public short[] hitPoints;

    interface DeadZone {
        int TOP = 1, BOTTOM = 1, LEFT = 1, RIGHT = 1;
    }

    public AbstractBoat() {
        position = new Point(-1, -1);
    }

    public AbstractBoat(final int lange) {
        this();
        length = lange;
        hitPoints = new short[length];
    }

    public abstract void setPos(Point start, Point anyPointInDirection);

    private boolean didIGotHit(Point p0) {
        if (!boatIsDisabled()) {
            return Range.between((position.x), (position.x + width - DeadZone.RIGHT)).contains(p0.x) &&
                    Range.between((position.y), (position.y + height - DeadZone.TOP)).contains(p0.y);
        } else {
            return false;
        }
    }

    public boolean addHitPoint(Point p0) {
        if (didIGotHit(p0)) {
            switch (hitPoints[((p0.x - position.x) + (p0.y - position.y))]) {
                case 0:
                    hitPoints[((p0.x - position.x) + (p0.y - position.y))] = 1;
                    return true;
                case 1:
                    return false;
            }
        }
        return false;
    }

    public boolean boatIsDisabled() {
        int count = 0;
        for (int p : hitPoints) {
            count += p;
        }
        return count == length;
    }

    public final boolean collidesWith(AbstractBoat other) {
        return
                        //LK(this(incl Deadzone)) links der RK(other)
                this.position.getX() - DeadZone.LEFT < other.position.getX() + other.width
                        &&
                        //RK(this(incl Deadzone) rechts der LK(other)
                this.position.getX() + this.width + DeadZone.RIGHT > other.position.getX()
                        &&
                        //OK(this(incl Deadzone) über UK(other)
                this.position.getY() + this.height + DeadZone.TOP > other.position.getY()
                        &&
                        //UK(this(incl Deadzone) unter OK(other)
                this.position.getY() - DeadZone.BOTTOM < other.position.getY() + other.height;
    }

    public void resetCD() {
        coolDown = 0;
    }

    public void passiveCD() {
        coolDown++;
    }

    public int getCD() {
        return coolDown;
    }

    public short[] getHitPoints() {
        return hitPoints;
    }

    public Point[] getPlacement() {
        Point[] returnArray = new Point[length];
        boolean isVert = height > width;
        returnArray[0] = position;
        if (isVert)
            for (int i = 1; i < returnArray.length; i++) {
                returnArray[i] = new Point(position.x, position.y + i);
            }
        else
            for (int i = 1; i < returnArray.length; i++) {
                returnArray[i] = new Point(position.x + i, position.y);
            }
        return returnArray;
    }

    public Point getPosition(){
        return position;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setWidth(int n){
        width = n;
    }
    public void setHeight(int n){
        height = n;
    }

}
