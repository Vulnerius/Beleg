package de.hsmw.kriegZurSee.GameComponents;

import de.hsmw.kriegZurSee.Boats.*;
import de.hsmw.kriegZurSee.Interface.Utilis;
import org.apache.commons.lang3.Range;


import java.awt.*;
import java.util.ArrayList;

public class Field {
    final int length = 6;
    public final BattleShip bs = new BattleShip();
    public final Corsair cs = new Corsair();
    public final HeliLandingBoat hlb = new HeliLandingBoat();
    public final RepairBoat rb1 = new RepairBoat();
    public final RepairBoat rb2 = new RepairBoat();
    public final AbstractBoat[] boats = new AbstractBoat[]{bs, cs, hlb, rb1, rb2};

    public Field(){
        setBoats();
    }

    public boolean anyBoatGotHit(Point shot) {
        for (AbstractBoat b : boats) {
            return b.addHitPoint(shot);
        }
        return false;
    }

    public boolean allBoatsInField() {
        Point p0 = new Point(1, 1);
        Point pEnd = new Point(length, length);
        for (AbstractBoat b : boats) {
            return Range.between(p0.x, pEnd.x).contains((int) (b.getPosition().getX() + b.getWidth())) &&
                    Range.between(p0.y, pEnd.y).contains((int)b.getPosition().getY()+ b.getHeight());
        }
        return false;
    }

    public Point searchFct() {
        //get random boat -> getRandomNotAlreadyShotPosition
        ArrayList<Point> randomPoints = new ArrayList<>();
        for(AbstractBoat b : boats){
            if(!b.boatIsDisabled()){
                for(int temp = 0; temp < b.hitPoints.length; temp++){
                    if(b.hitPoints[temp] == 0)
                        randomPoints.add(new Point( (int)(b.getPosition().getX()+temp) , (int)(b.getPosition().getY()+temp)));
                }
            }
        }
        return randomPoints.get(Utilis.randInt(1,randomPoints.size()));
    }

    private void setBoats(){
        bs.setPos(new Point(2,0),new Point(3,0));
        cs.setPos(new Point(4,3), new Point(4,4));
        hlb.setPos(new Point(2,4),new Point(2,5));
        rb1.setPos(new Point(6,6),new Point(6,6));
        rb2.setPos(new Point(6,3),new Point(6,3));
    }

}
