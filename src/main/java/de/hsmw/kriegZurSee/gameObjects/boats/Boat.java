package de.hsmw.kriegZurSee.gameObjects.boats;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.gameObjects.GameObject;
import de.hsmw.kriegZurSee.utilities.Utilis;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Arrays;


public abstract class Boat extends GameObject {

    private final int[] hitPointCounter;

    public Boat(de.hsmw.kriegZurSee.constants.ID id, int x, int y, double width, double height, Color color) {
        super(id, x, y, width, height, color);
        hitPointCounter = new int[(int) (width+height)/40-1];
        Arrays.fill(hitPointCounter, 0);
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
        return getPosition().intersects(point.getX(), point.getY(), 0.1, 0.1);
    }

    public void addHitPoint(Point2D p0) {
        if(didIGotHit(p0) && hitPointCounter[Utilis.pointToIndex(this,p0)] == 0) {
            hitPointCounter[Utilis.pointToIndex(this,p0)] = 1;
        }
    }

    public int[] getHitPointCounter() {
        return hitPointCounter;
    }

    public void getRepaired(Point2D mouseClick) {
        hitPointCounter[Utilis.pointToIndex(this, mouseClick)] = 0;
    }
}
