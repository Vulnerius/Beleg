import de.hsmw.kriegZurSee.GameObjects.boats.BattleShip;
import de.hsmw.kriegZurSee.GameObjects.boats.Corsair;
import de.hsmw.kriegZurSee.GameObjects.boats.HeliLandingBoat;
import de.hsmw.kriegZurSee.GameObjects.boats.RepairBoat;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestBoat {
    Corsair cs = new Corsair(4,4,3,1, Color.RED);
    BattleShip bs1 = new BattleShip(2,2, 4, 1,Color.RED);
    BattleShip bs2 = new BattleShip(3,2, 4, 1,Color.RED);
    HeliLandingBoat hlb = new HeliLandingBoat(3,2, 4, 1,Color.RED);
    RepairBoat rb = new RepairBoat(3,2, 4, 1,Color.RED);


    @Test
    public void testingCollision(){
        //Collision
        Assertions.assertTrue(bs1.collidesWith(bs2));

        //no Collision bc outOufRange
        bs1.setPos(3,3);
        bs2.setPos(1,1);
        Assertions.assertFalse(bs1.collidesWith(bs2));

        //Collision on UpperRightCorner
        bs2.setPos(7,5);
        Assertions.assertTrue(cs.collidesWith(bs2));

        //cs still in (4,4) -> (6,5) --- Collision on lower left
        bs2.setPos(3,3);
        Assertions.assertTrue(bs2.collidesWith(cs));

        //collision on left
        bs2.setPos(2,4);
        Assertions.assertTrue(bs2.collidesWith(cs));
    }

    @Test
    public void shotTest(){
        Point2D pCSOR  = new Point2D(6,5);
        Assertions.assertFalse(cs.didIGotHit(pCSOR));

        Point2D pCSUL  = new Point2D(3,3);
        Assertions.assertFalse(cs.didIGotHit(pCSUL));

    Point2D pCSOL  = new Point2D(5,3);
        Assertions.assertFalse(cs.didIGotHit(pCSOL));

    Point2D pCSUR  = new Point2D(3,5);
        Assertions.assertFalse(cs.didIGotHit(pCSUR));
    }
}
