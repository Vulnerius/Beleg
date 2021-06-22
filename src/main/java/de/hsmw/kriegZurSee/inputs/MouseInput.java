package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.awt.*;

/*
Fields at
UserInterface.anchorPane
 */

public class MouseInput {

    Game game;

    public MouseInput(Game game, MouseEvent event) {
        this.game = game;
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
            handle(event);
        }
    }


    private Point searchPosition(Point mouseClick) {
        return null;
    }


    public void handle(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Point2D mouseClick = new Point2D(x, y);
        if (x <= 260 && x >= 20) {
            if (y <= 270) {
                if(game.searchField(ID.MyField,mouseClick))
                    Game.ui.drawCircle(x, y);

            }
            else if( y <= 610)
                System.out.println("Bottom");
                Game.ui.drawCircle(x, y);
        }
    }
}
