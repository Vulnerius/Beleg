package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.Game;

public class EndUI {
    public EndUI(Game game){
        System.out.println(game.getActivePlayer().getID() + " WON");
    }
}
