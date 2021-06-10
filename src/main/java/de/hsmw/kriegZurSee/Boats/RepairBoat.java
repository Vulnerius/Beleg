package de.hsmw.kriegZurSee.Boats;

import java.awt.*;

public class RepairBoat extends AbstractBoat {
    public RepairBoat() {
        super(1);
    }

    @Override
    public void setPos(Point start, Point anyPointInDirection) {
        if(!start.equals(anyPointInDirection))
            System.out.println("that's a repairBoat with the length 1 startPoint has to be directionPoint");
        position = start;
        setWidth(length);
        setHeight(length);
    }

    public void repair(final AbstractBoat toRepair, final int index) {
        if (!boatIsDisabled() && !toRepair.boatIsDisabled())
            toRepair.hitPoints[index - 1] = 0;
    }
}
