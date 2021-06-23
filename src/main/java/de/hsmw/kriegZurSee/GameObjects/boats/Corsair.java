package de.hsmw.kriegZurSee.GameObjects.boats;

import javafx.scene.paint.Color;

public class Corsair extends Boat {

    public Corsair( int x, int y, double width, double height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.Corsair, x, y, width, height,color);
    }

    @Override
    public void tick() {

    }
}
