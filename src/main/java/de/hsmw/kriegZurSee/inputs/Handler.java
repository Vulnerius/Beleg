package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import javafx.event.Event;
import javafx.event.EventHandler;

public class Handler implements EventHandler {
    public final Game game;

//static class to access game components while events
    public Handler(Game game) {
        this.game = game;
    }

    @Override
    public void handle(Event event) {
        if (event.getEventType().getName().equals("MOUSE_CLICKED")) {
            new MouseInput(game,(javafx.scene.input.MouseEvent) event);
        }
    }
}
