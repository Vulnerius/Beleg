package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.awt.*;

/*
Fields at
f1: 20,30,240,240
f1: 20,330, 240, 570

Buttons at
restore:


 */

public class MouseInput implements EventHandler<MouseEvent> {

    Game game;

    public MouseInput(Game game){
        this.game = game;
    }

    @Override
    public void handle(MouseEvent event) {
        int x = (int)       event.getX();
        int y = (int)       event.getY();

        Point2D mouseClick = new Point2D(x,y);
        //game.searchForBoatsMatching(mouseClick);
    }


    private Point searchPosition(Point mouseClick){
return null;
    }


}
