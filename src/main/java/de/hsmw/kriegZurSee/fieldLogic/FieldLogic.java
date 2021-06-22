package de.hsmw.kriegZurSee.fieldLogic;

import de.hsmw.kriegZurSee.GameObjects.Boat;
import de.hsmw.kriegZurSee.constants.BoatPlacements;
import de.hsmw.kriegZurSee.constants.ID;

import java.util.Random;

public class FieldLogic {
    private static Random r = new Random();
    public static Boat[] setBoats(ID id) {

        if (id.equals(ID.EnemyField))
            return BoatPlacements.boats2;
        else if (id.equals(ID.MyField))
            return BoatPlacements.boats1;
        return null;
    }
}
