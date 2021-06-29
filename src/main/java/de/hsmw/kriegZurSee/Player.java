package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.GameObjects.boats.RepairBoat;
import de.hsmw.kriegZurSee.Utilities.Utilis;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;

public class Player {

    private final ID id;
    private final Field field;
    private int shotCount = 15;
    private boolean hasTurn = true;
    private boolean hasShot = false;
    private boolean shoots5 = false;

    public Player(Field field, ID id) {
        this.field = field;
        this.id = id;
    }

    public boolean isShoots5() {
        return shoots5;
    }

    public void setShoots5(boolean shoots5) {
        this.shoots5 = shoots5;
    }

    public Field getField() {
        return field;
    }

    public void setHasTurn(boolean toSet) {
        hasTurn = toSet;
        if(hasTurn){
            setHasShot(false);
            setShoots5(false);

            field.newRound();
        }
    }

    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }

    public boolean isHasShot() {
        return hasShot;
    }

    public final void playerDidShoot() {
        shotCount--;
        hasShot = true;
    }

    public ID getID() {
        return id;
    }

    public boolean getHasTurn() {
        return hasTurn;
    }

    public String getShotCount() {
        return String.valueOf(shotCount);
    }

    public boolean canRepair() {
        for (Boat b : field.getBoats()) {
            if (b.getID().equals(ID.RepairBoat) && !b.isBoatDrowned()) {
                return true;
            }
        }
        return false;
    }

    public void repair(Point2D mouseclick) {
        RepairBoat repairing = null;
        Boat toRepair = field.searchForAliveBoat(mouseclick);

        for (Boat b : field.getBoats()) {
            if (b.getID().equals(ID.RepairBoat) && !b.isBoatDrowned()) {
                repairing = (RepairBoat) b;
            }
        }
        if (repairing != null && toRepair != null) {
            if (toRepair.getHitPointCounter()[Utilis.pointToIndex(toRepair, mouseclick)] == 1) {
                repairing.repair(toRepair, mouseclick);
                repairing.setHasCooldown();
                Game.ui.tOUT.setText("repaired");
            }
        }
    }

    public ID getFieldID() {
        return field.getID();
    }

    public void resetShotCount() {
        for (Boat b : field.getBoats())
            if (b.getID() == ID.HeliLandingBoat)
                if (!b.isBoatDrowned())
                    shotCount = 15;
    }

}
