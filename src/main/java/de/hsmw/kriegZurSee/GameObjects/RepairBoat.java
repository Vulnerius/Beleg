package de.hsmw.kriegZurSee.GameObjects;

import javafx.scene.paint.Color;

import java.awt.*;

public class RepairBoat extends Boat {
    public RepairBoat(int x, int y, int width, int height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.RepairBoat,x,y,width,height, color);
    }

    public void repair(final Boat toRepair) {
        if(isBoatDrowned() && toRepair.isBoatDrowned()){
            toRepair.getRepaired();
        } /*else{
            showDialog(Messages.BoatIsDrowned)
        }*/
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

    }
}
