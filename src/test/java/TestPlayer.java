import de.hsmw.kriegZurSee.GameComponents.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestPlayer {
    Player playerOne = new Player("Player 1");
    Player playerTwo = new Player("Player 2");

    @Test
    public void getName(){
        Assertions.assertEquals("Player 1", playerOne.name);
    }

    @Test
    public void setEnemyForPlayerTwo(){
        playerTwo.setEnemy(playerOne);
        Assertions.assertEquals(playerOne, playerTwo.enemy);
    }
    @Test
    public void singleShotTesting(){
        playerOne.setEnemy(playerTwo);
        Assertions.assertTrue(playerOne.shootOne(new Point(2,0)));
        Assertions.assertFalse(playerOne.shootOne(new Point(1,0)));
        Assertions.assertEquals(13,playerOne.getShotCount());
    }
}
