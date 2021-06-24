package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.GameObjects.boats.BattleShip;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.GameObjects.boats.HeliLandingBoat;
import de.hsmw.kriegZurSee.GameObjects.boats.RepairBoat;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;

public class Player {

    private final ID id;
    private int shotCount = 15;
    private boolean hasTurn = false;
    private final Field field;
    private boolean hasShot = false;

    public Player(Field field, ID id) {
        this.field = field;
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setHasTurn() {
        hasTurn = !hasTurn;
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
        Boat toRepair = null;

        for(Boat b : field.getBoats()){
            if (b.didIGotHit(mouseclick)) {
                toRepair = b;
            }
        }
        for (Boat b : field.getBoats()) {
            if (b.getID().equals(ID.RepairBoat) && !b.isBoatDrowned()) {
                repairing = (RepairBoat) b;
            }
        }
        if (repairing != null && toRepair != null)
            repairing.repair(toRepair, mouseclick);
    }


    public void resetShotCount() {
        for (Boat b : field.getBoats())
            if (b.getID() == ID.HeliLandingBoat)
                if (!b.isBoatDrowned())
                    shotCount = 15;
    }
    public void updateHLB(){
        boolean bsDrowned = false;
        HeliLandingBoat hlb = null;
        for (Boat b : field.getBoats()) {
            if (b.getID() == ID.BattleShip)
                bsDrowned = b.isBoatDrowned();
            if (b.getID() == ID.HeliLandingBoat)
                hlb = (HeliLandingBoat) b;
        }
        if(bsDrowned && hlb != null)
            hlb.bsIsDestroyed();
    }
}
