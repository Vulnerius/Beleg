package de.hsmw.kriegZurSee.gameObjects.boats;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public class RepairBoat extends Boat {
    public RepairBoat(int x, int y, int width, int height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.RepairBoat, x, y, width, height, color);
    }

    public void repair(Boat toRepair, Point2D index) {
        //repairing a boat
        if (!isBoatDrowned() && !toRepair.isBoatDrowned() && !isHasCoolDown()) {
            toRepair.getRepaired(index);
            setHasCooldown();
        }
    }
}
