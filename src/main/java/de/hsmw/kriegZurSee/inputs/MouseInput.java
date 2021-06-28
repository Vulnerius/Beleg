package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

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
        ID id = game.getActivePlayer().getFieldID();

        if (x >= 20 && x <= 260) {
            //checking x -> BOARD
            if (y >= 30 && y <= 270) {
                //checking y -> Field1
                game.getField1().mouseInput(id,mouseClick);
            } else if (y >= 330 && y <= 610) {
                game.getField2().mouseInput(id,mouseClick);
            }
        }
        game.getField1().updateField();
        game.getField2().updateField();
    }
}
