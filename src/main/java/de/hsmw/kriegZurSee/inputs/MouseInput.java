package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;


/*
Fields at
UserInterface.anchorPane
 */

public class MouseInput {

    Game game;

    public MouseInput(Game game, MouseEvent event) {
        this.game = game;
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            handle(event);
        }
    }

    public void handle(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Point2D mouseClick = new Point2D(x, y);
        if (x >= 20 && x <= 260) {
            if (y >= 30 && y <= 270) {
                if (game.searchField(ID.MyField, mouseClick)) {
                    Game.ui.drawCircle(x, y);
                }
                else {
                    Game.ui.drawMissCircle(x,y);
                }
            } else if (y >= 330 && y <= 610) {
                if (game.searchField(ID.EnemyField, mouseClick)) {
                    Game.ui.drawCircle(x, y);
                }
                else {
                    Game.ui.drawMissCircle(x,y);
                }
            }
        }
    }


}
