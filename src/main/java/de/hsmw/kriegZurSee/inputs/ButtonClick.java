package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonClick {


    public static EventHandler<ActionEvent> onRestore() {
        return event -> Game.restoreActivePlayerShots();
    }

    public static EventHandler<ActionEvent> onChange() {
        return event -> Game.ui.removeShot();
    }
}
