package de.hsmw.kriegZurSee.GameObjects;

import java.awt.*;

public class RepairBoat extends Boat {
    public RepairBoat(int x, int y, int width, int height, int length) {
        super(de.hsmw.kriegZurSee.constants.ID.RepairBoat,x,y,width,height,1);
    }

    public void repair(final Boat toRepair) {
        if(!isBoatDrowned()){
            toRepair.getRepaired();
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

    }
}
