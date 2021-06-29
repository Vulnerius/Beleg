package de.hsmw.kriegZurSee.fieldLogic;

import de.hsmw.kriegZurSee.GameObjects.boats.*;
import de.hsmw.kriegZurSee.Utilities.Utilis;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;

public class FieldLogic {
    private static final Random r = new Random();
    private static final BattleShip battleShipp1 = new BattleShip(0, 0, 4 * 40, 40, Color.WHITE);
    private static final Corsair corsairp1 = new Corsair(0, 0, 40, 3 * 40, Color.WHITE);
    private static final HeliLandingBoat heliLandingBoatp1 = new HeliLandingBoat(0, 0, 2 * 40, 40, Color.WHITE);
    private static final RepairBoat repairBoat1p1 = new RepairBoat(0, 0, 40, 40, Color.WHITE);
    private static final RepairBoat repairBoat2p1 = new RepairBoat(0, 0, 40, 40, Color.WHITE);


    private static final BattleShip battleShipp2 = new BattleShip(0, 0, 4 * 40, 40, Color.WHITE);
    private static final Corsair corsairp2 = new Corsair(0, 0, 40, 3 * 40, Color.WHITE);
    private static final HeliLandingBoat heliLandingBoatp2 = new HeliLandingBoat(0, 0, 2 * 40, 40, Color.WHITE);
    private static final RepairBoat repairBoat1p2 = new RepairBoat(0, 0, 40, 40, Color.WHITE);
    private static final RepairBoat repairBoat2p2 = new RepairBoat(0, 0, 40, 40, Color.WHITE);

    private static final Boat[] boatsP1 = new Boat[]{battleShipp1, corsairp1, heliLandingBoatp1, repairBoat1p1, repairBoat2p1};
    private static final Boat[] boatsP2 = new Boat[]{battleShipp2, corsairp2, heliLandingBoatp2, repairBoat1p2, repairBoat2p2};


    public static Boat[] setBoats(ID id) {
        if (id.equals(ID.Player1Field)) {
            return setField1Boats();
        } else {
            return setField2Boats();
        }
    }

    private static boolean boatsCollides(Boat boat, Boat[] boats) {
        int counter = 0;
        for (int temp = 0; temp < boats.length; temp++) {
            if (boat.getID().equals(boats[temp].getID())) {
                temp++;
            }
            else {
                if (boat.collidesWith(boats[temp]))
                    counter++;
            }
        }
        return counter == 0;
    }

    private static Boat[] setField1Boats() {
        // 20 <= x <= 260
        // 30 <= y <= 270
        Point2D forEveryBoat;
        Boat boat = null;
            do {
                for (Boat b : boatsP1) {
                    forEveryBoat = Utilis.randPt(20, 260-160, 30, 270-160);
                    b.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY());
                    boat = b;
                }
            } while (boatsCollides(boat,boatsP1));
        return boatsP1;
    }

    private static Boat[] setField2Boats() {
        // 20 <= x <= 260
        // 330 <= y <= 610
/*
        boolean running = true;
        Point2D forEveryBoat = Utilis.randPt(20, 260, 330, 610);
        while (running) {
            for (Boat b : boats) {
                for (int index = 0; index < boats.length; index++) {
                    Boat comparison = boats[index];
                    b.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY());
                    if (b.getID().equals(comparison.getID()))
                        index++;
                    if (boats[index].getPosition().intersects(b.getPosition().getX(), b.getPosition().getY(), b.getPosition().getWidth(), b.getPosition().getHeight())) {
                        forEveryBoat = Utilis.randPt(20, 260, 330, 610);
                        b.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY());
                    } else
                        running = false;
                }
            }
        }*/
        battleShipp2.setPos(20,330,160,40);
        corsairp2.setPos(60,410);
        heliLandingBoatp2.setPos(180,370);
        repairBoat1p2.setPos(180,450);
        repairBoat2p2.setPos(180,530);
        return boatsP2;
    }
}
