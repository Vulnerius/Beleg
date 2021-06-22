package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;


import java.awt.event.MouseEvent;

public class Handler implements EventHandler {
    Game game;

    public Handler(Game game) {
        this.game = game;
    }

    @Override
    public void handle(Event event) {
        if (event.getEventType().getName().equals("MOUSE_CLICKED")) {
            new MouseInput((javafx.scene.input.MouseEvent) event);
        }

Game.ui.addShot();
    }
}
