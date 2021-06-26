package de.hsmw.kriegZurSee.GameObjects.boats;

import de.hsmw.kriegZurSee.GameObjects.GameObject;
import de.hsmw.kriegZurSee.Utilities.Utilis;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Arrays;


public abstract class Boat extends GameObject {

    private final double length;
    private final int[] hitPointCounter;

    public Boat(de.hsmw.kriegZurSee.constants.ID id, int x, int y, double width, double height, Color color) {
        super(id, x, y, width, height, color);
        if (checkIsVert()) length = width / 40;
        else length = height / 40;
        hitPointCounter = new int[(int) length];
        Arrays.fill(hitPointCounter, 0);
    }

    public double getLength() {
        return length;
    }

    public boolean checkIsVert() {
        return position.getWidth() > position.getHeight();
    }

    public boolean isBoatDrowned() {
        int counter = 0;
        for (int temp = 0; temp < hitPointCounter.length; temp++) {
            counter += hitPointCounter[counter];
        }
        if (counter == hitPointCounter.length) {
            setColor(Color.RED);
            return true;
        }
        return false;
    }

    public boolean didIGotHit(Point2D point) {
        if (getPosition().intersects(point.getX(), point.getY(), 0.1, 0.1)) {
            return addHitPoint(Utilis.pointToIndex(this, point));
        }
        return false;
    }


    protected boolean addHitPoint(int i) {
        if (!isBoatDrowned()) {
            hitPointCounter[i] = 1;
            return true;
        }
        return false;
    }

    public int[] getHitPointCounter() {
        return hitPointCounter;
    }

    public void getRepaired(Point2D mouseClick) {
        hitPointCounter[Utilis.pointToIndex(this, mouseClick)] = 0;
    }
}
