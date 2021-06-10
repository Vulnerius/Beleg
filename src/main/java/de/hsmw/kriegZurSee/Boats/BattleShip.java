package de.hsmw.kriegZurSee.Boats;

import java.awt.*;

public class BattleShip extends AbstractBoat {
    public BattleShip() {
        super(4);
    }

    @Override
    public void setPos(Point start, Point anyPointInDirection) {
        position = start;
        if (anyPointInDirection.x - start.x > anyPointInDirection.y - start.y) {
            setWidth(length);
            setHeight(1);
        } else {
            setHeight(length);
            setWidth(1);
        }

    }

    public boolean canShoot5() {
        return getCD() >= 2 && !boatIsDisabled();
    }
}
