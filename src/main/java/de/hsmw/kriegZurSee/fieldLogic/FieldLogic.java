package de.hsmw.kriegZurSee.fieldLogic;

import de.hsmw.kriegZurSee.gameObjects.boats.*;
import de.hsmw.kriegZurSee.utilities.Utilis;
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

    private static boolean boatCollides(Boat boat, Boat[] boats) {
        if (boat == null)
            return false;
        for (int temp = 0; temp < boats.length; temp++) {
            if (boat.getID().equals(boats[temp].getID())) {
                temp++;
            } else if (boat.collidesWith(boats[temp])) {
                return false;
            }
        }
        return true;
    }

    private static Boat[] setField1Boats() {
        // 20 <= x <= 260
        // 30 <= y <= 270
        for (int temp = 0; temp < boatsP1.length; temp++) {
            int ran = r.nextInt(2);
            Point2D forEveryBoat = Utilis.randPtF1();
            Boat boat = boatsP1[temp];
            boat.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY());
            while (!inBoundF1(boat) && !boatCollides(boat,boatsP1)) {
                forEveryBoat = Utilis.randPtF1();
                if (ran == 0) {
                    boat.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY(), boat.getHitPointCounter().length * 40, 40);
                } else {
                    boat.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY(), 40, boat.getHitPointCounter().length * 40);
                } if(!boatCollides(boat,boatsP1)){
                    if(ran == 0){
                        boat.setPos((int)boat.getPosition().getX() -40, (int)boat.getPosition().getY()-40);
                    }
                    else
                        boat.setPos((int)boat.getPosition().getX() +40, (int)boat.getPosition().getY()+40);
                }
                boatsP1[temp] = boat;
            }
        }
        return boatsP1;
    }


    private static boolean inBoundF1(Boat boat) {
        boolean isInX = false;
        boolean isInY = false;
        //checking x
        if (boat.getPosition().getX() >= 20 && boat.getPosition().getX() + boat.getPosition().getWidth() <= 260) {
            isInX = true;
        }
        //checking y
        if (boat.getPosition().getY() >= 30 && boat.getPosition().getY() + boat.getPosition().getHeight() <= 270) {
            isInY = true;
        }
        if (!isInX)
            return false;
        else return isInY;
    }

    private static Boat[] setField2Boats() {
        // 20 <= x <= 260
        // 330 <= y <= 610
        int ran = r.nextInt(2);
        Point2D forEveryBoat = Utilis.randPtF1();
        Boat[] returnArray = boatsP2;

        for (int temp = 0; temp < returnArray.length; temp++) {
            for (int i = 0; i < 150; i++) {
                Boat boat = returnArray[temp];
                boat.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY());
                while (!inBoundF2(boat)) {
                    forEveryBoat = Utilis.randPtF2();
                    if (ran == 0) {
                        boat.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY(), boat.getHitPointCounter().length * 40, 40);
                    } else {
                        boat.setPos((int) forEveryBoat.getX(), (int) forEveryBoat.getY(), 40, boat.getHitPointCounter().length * 40);
                    }
                }
                ran = r.nextInt(2);
                forEveryBoat = Utilis.randPtF2();
                if (boatCollides(boat, returnArray)) {
                    boatsP2[temp] = boat;
                } else
                    i++;
            }
        }
        return boatsP2;
    }

    private static boolean inBoundF2(Boat boat) {
        boolean isInX = false;
        boolean isInY = false;
        if (boat.getPosition().getX() >= 20 && boat.getPosition().getX() + boat.getPosition().getWidth() <= 260)
            isInX = true;
        if (boat.getPosition().getY() >= 330 && boat.getPosition().getY() + boat.getPosition().getHeight() <= 600)
            isInY = true;
        //checking x
        if (!isInX)
            return false;
        else return isInY;
    }
}
