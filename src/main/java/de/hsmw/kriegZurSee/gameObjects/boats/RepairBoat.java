package de.hsmw.kriegZurSee.gameObjects.boats;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public class RepairBoat extends Boat {
    public RepairBoat(int x, int y, int width, int height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.RepairBoat, x, y, width, height, color);
    }

    public void repair(Boat toRepair, Point2D index) {
        if (!isBoatDrowned() && !toRepair.isBoatDrowned() && !isHasCooldown()) {
            toRepair.getRepaired(index);
            setHasCooldown();
        }
    }
}