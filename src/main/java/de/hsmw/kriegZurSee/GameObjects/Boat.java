package de.hsmw.kriegZurSee.GameObjects;

import javafx.geometry.Point2D;

public abstract class Boat extends GameObject {

    private final int length;
    private int hitPointCounter = 0;

    public Boat(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height, int length) {
        super(id, x, y, width, height);
        this.length = length;
    }

    public boolean isBoatDrowned(){
        return hitPointCounter == length;
    }

    private boolean didIGotHit(Point2D point){
        if( getPosition().intersects(point.getX(),point.getY(),.5,.5) ){
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
