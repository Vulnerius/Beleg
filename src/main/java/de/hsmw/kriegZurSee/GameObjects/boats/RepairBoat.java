package de.hsmw.kriegZurSee.GameObjects.boats;
import javafx.scene.paint.Color;

public class RepairBoat extends Boat {
    public RepairBoat(int x, int y, int width, int height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.RepairBoat,x,y,width,height, color);
    }

    public void repair(final Boat toRepair, int index) {
        if(isBoatDrowned() && toRepair.isBoatDrowned()){
            toRepair.getRepaired(index);
        } /*else{
            showDialog(Messages.BoatIsDrowned)
        }*/
    }

    @Override
    public void tick() {

    }
}
