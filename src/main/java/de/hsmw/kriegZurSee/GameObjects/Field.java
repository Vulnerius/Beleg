package de.hsmw.kriegZurSee.GameObjects;

import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import java.awt.*;

public class Field extends GameObject{

    //list or array for Boats

    private Boat[] boats = new Boat[5];
    private final ID id;


    public Field(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height) {
        super(id, x, y, width, height, Color.BLUE);
        this.id = id;
        setBoats();
    }


    private void setBoats(){
       // FieldLogic.setBoats(boats);
    }


    public de.hsmw.kriegZurSee.constants.ID getID() {
        return id;
    }

    public Boat searchForMatching(Point2D mouseclick){
        for(Boat boat : boats){
            if(boat.getPosition().intersects(mouseclick.getX(),mouseclick.getY(),0.1,0.1))
                return boat;
        }
        return null; //nein!
    }

    @Override
    public void render(Graphics g) {

    }
}
