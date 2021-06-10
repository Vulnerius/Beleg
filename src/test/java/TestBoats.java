
import de.hsmw.kriegZurSee.Boats.BattleShip;
import de.hsmw.kriegZurSee.Boats.Corsair;
import de.hsmw.kriegZurSee.Boats.HeliLandingBoat;
import de.hsmw.kriegZurSee.Boats.RepairBoat;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.awt.*;


public class TestBoats {
    BattleShip bs = new BattleShip();
    Corsair cs = new Corsair();
    HeliLandingBoat hlb = new HeliLandingBoat();
    RepairBoat rb1 = new RepairBoat();
    RepairBoat rb2 = new RepairBoat();
    RepairBoat rb3 = new RepairBoat();

    Point p1 = new Point(1, 1);
    Point p3 = new Point(3, 1);
    Point p2 = new Point(2, 2);
    Point p4 = new Point(3, 2);

    @Test
    public void hitPointTests(){
        cs.setPos(p1,p3);
        p2.setLocation(2,1);
        Assertions.assertTrue(cs.addHitPoint(p1));
        Assertions.assertTrue(cs.addHitPoint(p2));
        Assertions.assertTrue(cs.addHitPoint(p3));
        Assertions.assertTrue(cs.boatIsDisabled());
    }
    @Test
    public void setPos() {
        p1.setLocation(2,0);
        p2.setLocation(2,3);
        p3.setLocation(5,0);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(5, 0);
        cs.setPos(p1, p3); //horiz
        bs.setPos(p1, p2); //vert
        rb1.setPos(p1, p1);
        Assertions.assertTrue(bs.collidesWith(cs));
        Assertions.assertEquals(cs.getWidth(), Math.max(cs.getWidth(), cs.getHeight()));
        Assertions.assertEquals(bs.getHeight(), Math.max(bs.getWidth(), bs.getHeight()));
    }

    @Test
    public void boatsCollideOn_UPPERRIGHT_LOWERLEFT_EckCorners() {
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(1,1);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    }
    @Test
    public void boatsCollideOn_UPPERLEFT_LOWERRIGHT_EckCorners() {
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(3,3);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    }
    @Test
    public void boatsCollideOn_LOWERLEFT_UPPERRIGHT_EckCorners() {
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(1,3);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    }
    @Test
    public void boatsCollideOn_LOWERRIGHT_UPPERLEFT_EckCorners() {
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(3,1);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    }
    @Test
    public void boatsCollideOn_TOP_BOTTOM(){
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(3,2);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    }
    @Test
    public void boatsCollideOn_BOTTOM_TOP(){
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(2,1);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    } @Test
    public void boatsCollideOn_LEFT_RIGHT(){
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(1,2);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    }@Test
    public void boatsCollideOn_RIGHT_LEFT(){
        p1.setLocation(2,2);
        rb2.setPos(p1,p1);
        p2.setLocation(3,2);
        rb1.setPos(p2,p2);
        Assertions.assertTrue(rb2.collidesWith(rb1));
    }

}
