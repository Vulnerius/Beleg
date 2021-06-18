package de.hsmw.kriegZurSee.GameObjects;


import javafx.scene.paint.Color;

import java.awt.*;

public class BattleShip extends Boat {

    private final int coolDownCounter = 0;

    public BattleShip(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height, Color color) {
        super(id, x, y, width, height, color);
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
