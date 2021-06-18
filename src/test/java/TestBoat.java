import de.hsmw.kriegZurSee.GameObjects.BattleShip;
import de.hsmw.kriegZurSee.GameObjects.Corsair;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBoat {
    Corsair cs = new Corsair(ID.Corsair,4,4,3,1, Color.RED);
    BattleShip bs1 = new BattleShip(ID.BattleShip,2,2, 4, 1,Color.RED);
    BattleShip bs2 = new BattleShip(ID.BattleShip,3,2, 4, 1,Color.RED);


    @Test
    public void testingCollision(){
        //Collision
        Assertions.assertTrue(bs1.collidesWith(bs2));

        //no Collision bc outOufRange
        bs1.setPos(3,3,1,4);
        bs2.setPos(1,1,1,4);
        Assertions.assertFalse(bs1.collidesWith(bs2));

        //Collision on UpperRightCorner
        bs2.setPos(7,5,3,1);
        Assertions.assertTrue(cs.collidesWith(bs2));

        //cs still in (4,4) -> (6,5) --- Collision on lower left
        bs2.setPos(3,3,1,1);
        Assertions.assertTrue(bs2.collidesWith(cs));

        //collision on left
        bs2.setPos(2,4,1,1);
        Assertions.assertFalse(bs2.collidesWith(cs));
    }
    @Test
    public void shotTest(){
        Point2D pCS  = new Point2D(7,5);
        Assertions.assertFalse(cs.didIGotHit(pCS));
    }
}
