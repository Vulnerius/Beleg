import de.hsmw.kriegZurSee.Boats.AbstractBoat;
import de.hsmw.kriegZurSee.GameComponents.Field;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestField {

    Field fieldOne = new Field();
    Field fieldTwo = new Field();

    @Test
    public void searchForBoat(){
        Assertions.assertTrue(Range.between(1,6).contains((int) fieldOne.searchFct().getX()));
        Assertions.assertTrue(Range.between(1,6).contains((int) fieldOne.searchFct().getY()));

        fieldOne.bs.addHitPoint(fieldOne.bs.getPosition());
        fieldOne.cs.addHitPoint(fieldOne.cs.getPosition());
        fieldOne.rb2.addHitPoint(fieldOne.rb2.getPosition());
        fieldOne.rb1.addHitPoint(fieldOne.rb1.getPosition());
        fieldOne.hlb.addHitPoint(fieldOne.hlb.getPosition());

        Point gottenPoint = fieldOne.searchFct();
        for(AbstractBoat b : fieldOne.boats){
            Assertions.assertNotEquals(gottenPoint, b.getPosition());
        }

    }

    @Test
    public void allBoatsInField(){
        Assertions.assertTrue(fieldTwo.allBoatsInField());
    }
}
