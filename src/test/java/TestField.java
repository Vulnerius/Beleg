import de.hsmw.kriegZurSee.GameComponents.Field;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestField {

    Field fieldOne = new Field();
    Field fieldTwo = new Field();

    @Test
    public void searchForBoat(){
        Assertions.assertTrue(Range.between(1,6).contains((int) fieldOne.searchFct().getX()));
        Assertions.assertTrue(Range.between(1,6).contains((int) fieldOne.searchFct().getY()));

        fieldOne.rb2.addHitPoint(fieldOne.rb2.getPosition());
        
    }
    @Test
    public void allBoatsInField(){
        Assertions.assertTrue(fieldTwo.allBoatsInField());
    }
}
