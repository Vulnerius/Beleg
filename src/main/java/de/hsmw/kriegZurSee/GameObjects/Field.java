package de.hsmw.kriegZurSee.GameObjects;

import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.fieldLogic.FieldLogic;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Arrays;
import java.util.Random;


public class Field extends GameObject {

    //list or array for Boats

    private final Boat[] boats;
    private final ID id;


    public Field(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height) {
        super(id, x, y, width, height, Color.BLUE);
        this.id = id;
        boats = FieldLogic.setBoats(id);
    }


    public de.hsmw.kriegZurSee.constants.ID getID() {
        return id;
    }

    public boolean searchForMatching(Point2D mouseclick) {
        for (int i = 0; i < boats.length; i++) {
            if( boats[i].didIGotHit(mouseclick) ) {
                System.out.println(Arrays.toString(boats[i].getHitPointCounter()));
                 return true;
            }
        }
        return false;
    }

    public Boat[] getBoats() {
        return boats;
    }
    public Circle getBoatPos(){
        Random i = new Random();
        Boat discovered;
        int found = i.nextInt(boats.length);
        if(boats[found].getID() != ID.HeliLandingBoat){
            discovered = boats[found];
        }
        else {
            discovered = boats[1];
        }
        return new Circle(discovered.getPosition().getX() +15 ,discovered.getPosition().getY() +15,10);
    }

    @Override
    public void tick() {

    }
}
