package de.hsmw.kriegZurSee.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Boat extends GameObject {

    private final int length;
    private int hitPointCounter = 0;

    public Boat(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height, Color color) {
        super(id, x, y, width, height, color);
        if (checkIsVert()) length = width / 64;
        else length = height / 64;
    }

    private boolean checkIsVert(){
        return position.getWidth() > position.getHeight();
    }

    public boolean isBoatDrowned(){
        return hitPointCounter == length;
    }

    public final boolean didIGotHit(Point2D point){
        if( getPosition().contains(point) ){
            hitPointCounter++;
            return true;
        }
        return false;
    }

    public void getRepaired(){
        hitPointCounter--;
    }

    private void addHitPoint(){
        hitPointCounter++;
    }
}
