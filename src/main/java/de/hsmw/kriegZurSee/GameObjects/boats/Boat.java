package de.hsmw.kriegZurSee.GameObjects.boats;

import de.hsmw.kriegZurSee.GameObjects.GameObject;
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

    private boolean checkIsVert() {
        return position.getWidth() > position.getHeight();
    }

    public boolean isBoatDrowned() {
        int counter = 0;
        int temp = 0;
        while (counter < hitPointCounter.length) {
            temp += hitPointCounter[counter];
            counter++;
        }

        return temp == hitPointCounter.length;
    }

    public boolean didIGotHit(Point2D point) {
        if (getPosition().intersects(point.getX(), point.getY(), 0.1, 0.1)) {
            if (isBoatDrowned())
                return false;
            if (checkIsVert()) {
                if (hitPointCounter[(int) ((getPosition().getX() + getPosition().getWidth() - point.getX()) / 40)] == 1) {
                    return false;
                } else {
                    hitPointCounter[(int) ((getPosition().getX() + getPosition().getWidth() - point.getX()) / 40)] = 1;
                    return true;
                }
            } else {
                if (hitPointCounter[(int) (((getPosition().getY() + getPosition().getHeight()) - point.getY()) / 40)] == 1)
                    return false;
                else {
                    hitPointCounter[(int) (((getPosition().getY() + getPosition().getHeight()) - point.getY()) / 40)] = 1;
                    return true;
                }
            }

        }
        return false;
    }

    public int[] getHitPointCounter() {
        return hitPointCounter;
    }

    public void getRepaired(int index) {
        hitPointCounter[index] = 0;
    }

}
