package de.hsmw.kriegZurSee.Boats;

import java.awt.*;

public class Corsair extends AbstractBoat{
    public Corsair(){
        super(3);
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

    public boolean canSearchForEnemy(){
        return !boatIsDisabled() /*&& coolDown >= 2*/;
    }
}
