package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.gameObjects.Field;
import de.hsmw.kriegZurSee.gameObjects.boats.Boat;
import de.hsmw.kriegZurSee.gameObjects.boats.RepairBoat;
import de.hsmw.kriegZurSee.utilities.Utilis;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;

public class Player {

    private Game game;
    private final ID id;
    private final Field field;
    private int shotCount = 15;
    private boolean hasTurn = true;
//    private boolean hasShot = false;
    private boolean shoots5 = false;

    public Player(Game game, Field field, ID id) {
        this.field = field;
        this.id = id;
        this.game = game;
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
        if (hasTurn) {
            //setHasShot(false);
            setShoots5(false);
            field.newRound();
        }
    }

/*    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }*/

    public final void playerDidShoot() {
        shotCount--;
       // hasShot = true;
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

    //returning whether or not a player can Repair - a player has RepairBoats which are not drowned
    public boolean canRepair() {
        for (Boat b : field.getBoats()) {
            if (b.getID().equals(ID.RepairBoat) && !b.isBoatDrowned()) {
                return true;
            }
        }
        return false;
    }

    //repairing method for a Player
    //getting the hitten Boat and one of the RepairBoats in the players field if available
    public void repair(Point2D mouseclick) {
        RepairBoat repairing = null;
        Boat toRepair = field.searchForAliveBoat(mouseclick);

        for (Boat b : field.getBoats()) {
            if (b.getID().equals(ID.RepairBoat) && !b.isBoatDrowned() && !b.isHasCooldown()) {
                repairing = (RepairBoat) b;
            }
        }
        //checking repairingBoat and toRepairBoat to get no NullPointer
        if (repairing != null && toRepair != null) {
            if (toRepair.getHitPointCounter()[Utilis.pointToIndex(toRepair, mouseclick)] == 1) {
                repairing.repair(toRepair, mouseclick);
                game.ui.update("repaired " + toRepair.getID() + " @ " + Utilis.pointToIndex(toRepair, mouseclick) + 1);
            }
        }
    }

    public ID getFieldID() {
        return field.getID();
    }

    //resetting the shotCount if HeliLandingBoat is not drowned
    public void resetShotCount() {
        for (Boat b : field.getBoats())
            if (b.getID() == ID.HeliLandingBoat)
                if (!b.isBoatDrowned())
                    shotCount = 15;
    }

}
