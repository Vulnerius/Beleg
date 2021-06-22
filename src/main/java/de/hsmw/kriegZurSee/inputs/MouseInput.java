package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.awt.*;

/*
Fields at
UserInterface.anchorPane
 */

public class MouseInput {


    public MouseInput(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
            handle(event);
        }
    }


    private Point searchPosition(Point mouseClick) {
        return null;
    }


    public static void handle(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Point2D mouseClick = new Point2D(x, y);
        if (x <= 260 && x >= 20) {
            if (y <= 270) {
                System.out.println("Top");
                Game.ui.drawRect(x, y);
            }
            else if( y <= 610)
                System.out.println("Bottom");
                Game.ui.drawRect(x, y);
        }
    }
}
