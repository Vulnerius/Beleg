package de.hsmw.kriegZurSee.GameObjects;

import javafx.scene.paint.Color;

import java.awt.*;

public class Corsair extends Boat {

    public Corsair( int x, int y, double width, double height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.Corsair, x, y, width, height,color);
    }

    @Override
    public void tick() {

    }
}
