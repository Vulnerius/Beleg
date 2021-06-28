package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonClick {


    public static EventHandler<ActionEvent> onRestore() {
        return event -> Game.restoreActivePlayerShots();
    }

    public static EventHandler<ActionEvent> onShoot5() {
        return event -> {
            try {
                Game.playerShoots5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
    public static EventHandler<ActionEvent> onSearch() {
        return event -> Game.playerSearchingForEnemyBoatPoint();
    }
}
