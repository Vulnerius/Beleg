package de.hsmw.kriegZurSee.constants;

import de.hsmw.kriegZurSee.GameObjects.boats.*;
import javafx.scene.paint.Color;

public class BoatPlacements {
    public static Boat[] boats1 =
            {       new BattleShip(20,30,160,40, Color.VIOLET),
                    new Corsair(20,110,120,40, Color.VIOLET),
                    new HeliLandingBoat(220, 70,40,80, Color.VIOLET),
                    new RepairBoat(60, 190,40,40, Color.VIOLET),
                    new RepairBoat(140, 190,40,40, Color.VIOLET)
            };

    public static Boat[] boats2 =
            {       new BattleShip(20,330,160,40, Color.VIOLET),
                    new Corsair(100,410,40,120, Color.VIOLET),
                    new HeliLandingBoat(220,410,40,80,Color.VIOLET),
                    new RepairBoat(60, 530,40,40, Color.VIOLET),
                    new RepairBoat(140, 530,40,40, Color.VIOLET)
            };

}
