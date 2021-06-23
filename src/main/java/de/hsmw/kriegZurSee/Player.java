package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.ID;

public class Player {

    private final ID id;
    private int shotCount = 15;
    private boolean hasTurn = false;
    private final Field field;

    public Player(Field field, ID id) {
        this.field = field;
        this.id = id;
    }

    public void setHasTurn() {
        hasTurn = !hasTurn;
    }

    public final void playerDidShoot() {
        shotCount--;
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

    public void resetShotCount() {
        for (Boat b : field.getBoats())
            if (b.getID() == ID.HeliLandingBoat)
                if (!b.isBoatDrowned())
                    shotCount = 15;
    }
}
