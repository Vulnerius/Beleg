package de.hsmw.kriegZurSee.fieldLogic;

import de.hsmw.kriegZurSee.gameObjects.boats.*;
import de.hsmw.kriegZurSee.utilities.Utilis;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;

public interface FieldLogic {
    Random r = new Random();
    //initialising Player1 Boats
    BattleShip battleShipp1 = new BattleShip((int) Utilis.randPtF1().getX(), (int) Utilis.randPtF1().getY(), 4 * 40, 40, Color.WHITE);
    Corsair corsairp1 = new Corsair((int) Utilis.randPtF1().getX(), (int) Utilis.randPtF1().getY(), 40, 3 * 40, Color.WHITE);
    HeliLandingBoat heliLandingBoatp1 = new HeliLandingBoat((int) Utilis.randPtF1().getX(), (int) Utilis.randPtF1().getY(), 2 * 40, 40, Color.WHITE);
    RepairBoat repairBoat1p1 = new RepairBoat((int) Utilis.randPtF1().getX(), (int) Utilis.randPtF1().getY(), 40, 40, Color.WHITE);
    RepairBoat repairBoat2p1 = new RepairBoat((int) Utilis.randPtF1().getX(), (int) Utilis.randPtF1().getY(), 40, 40, Color.WHITE);

    //initialising Player2 Boats
    BattleShip battleShipp2 = new BattleShip((int) Utilis.randPtF2().getX(), (int) Utilis.randPtF2().getY(), 4 * 40, 40, Color.WHITE);
    Corsair corsairp2 = new Corsair((int) Utilis.randPtF2().getX(), (int) Utilis.randPtF2().getY(), 40, 3 * 40, Color.WHITE);
    HeliLandingBoat heliLandingBoatp2 = new HeliLandingBoat((int) Utilis.randPtF2().getX(), (int) Utilis.randPtF2().getY(), 2 * 40, 40, Color.WHITE);
    RepairBoat repairBoat1p2 = new RepairBoat((int) Utilis.randPtF2().getX(), (int) Utilis.randPtF2().getY(), 40, 40, Color.WHITE);
    RepairBoat repairBoat2p2 = new RepairBoat((int) Utilis.randPtF2().getX(), (int) Utilis.randPtF2().getY(), 40, 40, Color.WHITE);

    //assigning boats to Boat-Arrays
    Boat[] boatsP1 = new Boat[]{battleShipp1, corsairp1, heliLandingBoatp1, repairBoat1p1, repairBoat2p1};
    Boat[] boatsP2 = new Boat[]{battleShipp2, corsairp2, heliLandingBoatp2, repairBoat1p2, repairBoat2p2};


    static Boat[] setBoats(ID id) {
        if (id.equals(ID.Player1Field)) {
            return setField1Boats();
        } else {
            return setField2Boats();
        }
    }

    //private method to check if a Boat collides with other Boats in the Array
    private static boolean boatCollides(Boat boat, Boat[] boats) {
        int counter = 0;
        //checking whether or not a boat collides with other boats
        for (Boat b : boats) {
            if (b.collidesWith(boat)) {
                counter++;
            }
            if (b.getID().equals(boat.getID())) {
                counter--;
            }
        }
        return counter == 0;
    }

    private static Boat[] setField1Boats() {
        // 20 <= x <= 260
        // 30 <= y <= 270
        //for every Boat in BoatsP1 set Position
        for (int temp = 0; temp < boatsP1.length; temp++) {
            Boat boat = boatsP1[temp];
            //making sure not to get stuck in the while loop
            int whileLoopCounter = 0;
            //setting the Position for each Point in bounds of Field
            do {
                whileLoopCounter++;
                Point2D forEveryBoat = Utilis.randPtF1();
                int ran = r.nextInt(2);
                Utilis.setBoatPos(boat, forEveryBoat, ran);
                if (whileLoopCounter == 100000) {
                    break;
                }
                //checking if a boat collides with other boats on the Field
            } while (!boatCollides(boat, boatsP1));
            if (boatCollides(boat, boatsP1) && temp == 3) {
                boatsP1[3].setPos((int) boatsP1[3].getPosition().getX() + 80, (int) boatsP1[3].getPosition().getY() + 40);
            }
            if (boatCollides(boat, boatsP1) && temp == 4) {
                boatsP1[4].setPos((int) boatsP1[4].getPosition().getX() + 80, (int) boatsP1[4].getPosition().getY() + 40);
            }
        }
        return boatsP1;
    }

    private static Boat[] setField2Boats() {
        // 20 <= x <= 260
        // 330 <= y <= 610
        //for every Boat in BoatsP2
        for (int temp = 0; temp < boatsP2.length; temp++) {
            Boat boat = boatsP2[temp];
            //making sure not to get stuck in the while loop
            int whileLoopCounter = 0;
            //setting the Position for each Point in bounds of Field
            do {
                whileLoopCounter++;
                Point2D forEveryBoat = Utilis.randPtF2();
                int ran = r.nextInt(2);
                Utilis.setBoatPos(boat, forEveryBoat, ran);
                if (whileLoopCounter == 1000000) {
                    break;
                }
                //checking if a boat collides with other boats on the Field
            } while (!boatCollides(boat, boatsP2));
            if (boatCollides(boat, boatsP2) && temp == 3) {
                boatsP2[3].setPos((int) boatsP2[3].getPosition().getX() + 80, (int) boatsP2[3].getPosition().getY() + 40);
            }
            if (boatCollides(boat, boatsP2) && temp == 4) {
                boatsP2[4].setPos((int) boatsP2[4].getPosition().getX() + 80, (int) boatsP2[4].getPosition().getY() + 40);
            }
        }
        return boatsP2;
    }

}
