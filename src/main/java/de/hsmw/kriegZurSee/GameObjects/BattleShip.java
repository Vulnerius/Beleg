package de.hsmw.kriegZurSee.GameObjects;


import javafx.scene.paint.Color;

import java.awt.*;

public class BattleShip extends Boat {

    private final int coolDownCounter = 0;

    public BattleShip(int x, int y, double width, double height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.BattleShip, x, y, width, height, color);
    }


    @Override
    public void tick() {

    }
}
