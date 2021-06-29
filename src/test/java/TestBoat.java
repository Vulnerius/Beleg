import de.hsmw.kriegZurSee.gameObjects.boats.BattleShip;
import de.hsmw.kriegZurSee.gameObjects.boats.Corsair;
import de.hsmw.kriegZurSee.gameObjects.boats.HeliLandingBoat;
import de.hsmw.kriegZurSee.gameObjects.boats.RepairBoat;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestBoat {
    Corsair cs = new Corsair(4,4,3*40, 40, Color.RED);
    BattleShip bs1 = new BattleShip(2,2, 4*40, 40,Color.RED);
    BattleShip bs2 = new BattleShip(3,2, 4*40, 40,Color.RED);
    HeliLandingBoat hlb = new HeliLandingBoat(3,2, 4*40, 40,Color.RED);
    RepairBoat rb = new RepairBoat(3,2, 4*40, 40,Color.RED);


    @Test
    public void testingCollision(){
        //Collision
        Assertions.assertTrue(bs1.collidesWith(bs2));

        // Collision
        bs1.setPos(3,3);
        bs2.setPos(1,1);
        Assertions.assertTrue(bs1.collidesWith(bs2));

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
        Point2D pCSOR  = new Point2D(6*40,5*40);
        Assertions.assertFalse(cs.didIGotHit(pCSOR));

        Point2D pCSUL  = new Point2D(3*40,3*40);
        Assertions.assertFalse(cs.didIGotHit(pCSUL));

    Point2D pCSOL  = new Point2D(5*40,3*40);
        Assertions.assertFalse(cs.didIGotHit(pCSOL));

    Point2D pCSUR  = new Point2D(3*40,5*40);
        Assertions.assertFalse(cs.didIGotHit(pCSUR));
    }
}
