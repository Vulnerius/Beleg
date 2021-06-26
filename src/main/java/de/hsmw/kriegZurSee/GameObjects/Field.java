package de.hsmw.kriegZurSee.GameObjects;

import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.GameObjects.boats.HeliLandingBoat;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.fieldLogic.FieldLogic;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Arrays;
import java.util.Random;


public class Field extends GameObject {

    private final Boat[] boats;
    private final ID id;


    public Field(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height) {
        super(id, x, y, width, height, Color.BLUE);
        this.id = id;
        boats = FieldLogic.setBoats(id);
    }
    public void updateField(){
        updateBS_HLB();
        for(Boat b : boats){
            b.isBoatDrowned();
        }
    }

    private void updateBS_HLB() {
        boolean hoya = false;
        for (Boat b : boats) {
            if (b.getID() == ID.BattleShip && b.isBoatDrowned())
                hoya = true;
            if (b.getID() == ID.HeliLandingBoat) {
                if (hoya) {
                    ((HeliLandingBoat) b).bsIsDestroyed();
                }
            }
        }
    }

    public de.hsmw.kriegZurSee.constants.ID getID() {
        return id;
    }

    public boolean searchForMatching(Point2D mouseclick) {
        for (Boat boat : boats) {
            if (boat.didIGotHit(mouseclick)) {
                System.out.println(Arrays.toString(boat.getHitPointCounter()));
                return true;
            }
        }
        return false;
    }

    public Boat[] getBoats() {
        return boats;
    }

    public Circle getBoatPos() {
        Random i = new Random();
        int found = i.nextInt(boats.length);
        Boat discovered = boats[found];
        while (boats[found].isBoatDrowned()) {
            found = i.nextInt(boats.length);
            discovered = boats[found];
        }
        return new Circle(discovered.getPosition().getX() + 15, discovered.getPosition().getY() + 15, 10);
    }

    @Override
    public void tick() {

    }
}
