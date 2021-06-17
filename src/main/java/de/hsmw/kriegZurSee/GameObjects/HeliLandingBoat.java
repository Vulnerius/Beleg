package de.hsmw.kriegZurSee.GameObjects;

import java.awt.*;

public class HeliLandingBoat extends Boat {
    private boolean canBeDamaged = false;

    public HeliLandingBoat(de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height, int length) {
        super(id, x, y, width, height, length);
    }


    public void bsIsDestroyed() {
        canBeDamaged = true;
    }

    public boolean getCanBDmgd(){
        return canBeDamaged;
    }


    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int)position.getX(),(int)position.getY(),(int)position.getWidth(),(int)position.getHeight());
    }
}
