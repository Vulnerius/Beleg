package de.hsmw.kriegZurSee.Boats;

import java.awt.*;

public class HeliLandingBoat extends AbstractBoat {
    private boolean canBeDamaged = false;

    public HeliLandingBoat() {
        super(2);
    }

    @Override
    public void setPos(Point start, Point anyPointInDirection) {
        position = start;
        if (anyPointInDirection.x - start.x > anyPointInDirection.y - start.y) {
            setWidth(length);
            setHeight(1);
        } else {
            setWidth(1);
            setHeight(length);
        }
    }

    public void bsIsDestroyed() {
        canBeDamaged = true;
    }

    @Override
    public boolean addHitPoint(Point p0) {
        if (canBeDamaged)
            return super.addHitPoint(p0);
        else
            return false;
    }
}
