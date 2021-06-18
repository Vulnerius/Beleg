package de.hsmw.kriegZurSee.GameObjects;

import javafx.scene.paint.Color;
import java.awt.*;

public class Field extends GameObject{

    //list or array for Boats


    public Field(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height) {
        super(id, x, y, width, height, Color.BLUE);
        setBoats();
    }


    private void setBoats(){
        //GameLogic.buildField();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
