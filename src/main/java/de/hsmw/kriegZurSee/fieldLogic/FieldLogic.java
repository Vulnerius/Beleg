package de.hsmw.kriegZurSee.fieldLogic;

import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.BoatPlacements;
import de.hsmw.kriegZurSee.constants.ID;

import java.util.Random;

public class FieldLogic {
    private static Random r = new Random();
    public static Boat[] setBoats(ID id) {

        if (id.equals(ID.Player2Field))
            return BoatPlacements.boats2;
        else if (id.equals(ID.Player1Field))
            return BoatPlacements.boats1;
        return null;
    }
}
