package de.hsmw.kriegZurSee.GameObjects;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public class HeliLandingBoat extends Boat {
    private boolean canBeDamaged = false;

    public HeliLandingBoat( int x, int y, double width, double height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.HeliLandingBoat, x, y, width, height, color);
    }

    @Override
    public boolean didIGotHit(Point2D mouseClick) {
        if (getCanBDmgd())
            super.didIGotHit(mouseClick);
        return false;
    }

    public void bsIsDestroyed() {
        canBeDamaged = true;
    }

    public boolean getCanBDmgd() {
        return canBeDamaged;
    }


    @Override
    public void tick() {

    }
}
