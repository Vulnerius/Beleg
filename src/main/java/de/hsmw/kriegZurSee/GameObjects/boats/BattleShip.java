package de.hsmw.kriegZurSee.GameObjects.boats;
import javafx.scene.paint.Color;


public class BattleShip extends Boat {


    public BattleShip(int x, int y, double width, double height, Color color) {
        super(de.hsmw.kriegZurSee.constants.ID.BattleShip, x, y, width, height, color);
    }



    @Override
    public void tick() {

    }
}
