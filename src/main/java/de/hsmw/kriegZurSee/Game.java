package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.GameState;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.inputs.Handler;
import de.hsmw.kriegZurSee.userInterface.EndUI;
import de.hsmw.kriegZurSee.userInterface.UserInterFace;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Game {
    public static UserInterFace ui;
    public static Handler handler;
    private Stage stage;
    private GameState gameState;
    private final Field field1;
    private final Field field2;
    private static Player player1;
    private static Player player2;

    public static int BOARD_WIDTH_HEIGHT = 240;


    public Game(Stage stage) {
        gameState = GameState.ACTIVE;
        this.stage = stage;
        handler = new Handler(this);
        field1 = new Field(this, ID.Player1Field, 20, 30, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        field2 = new Field(this, ID.Player2Field, 20, 330, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        player1 = new Player(field1, ID.Player1);
        player2 = new Player(field2, ID.Player2);
        player1.setHasTurn(true);
        player2.setHasTurn(false);
        ui = new UserInterFace(this, stage ,handler);
        /*field1.setBoatColors(Color.BLACK);
        field2.setBoatColors(Color.BLUE);*/
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static void restoreActivePlayerShots() {
        Player he = handler.game.getActivePlayer();
        he.resetShotCount();
        handler.game.switchTurn();
    }

    public static void playerSearchingForEnemyBoatPoint() {
        Player playerToSearch = handler.game.getActivePlayer();
        boolean canSearch = false;
        for (Boat b : playerToSearch.getField().getBoats()) {
            if (b.getID().equals(ID.Corsair) && !b.isBoatDrowned() && !b.isHasCooldown()) {
                canSearch = true;
                b.setHasCooldown();
            }
        }
        // active Player is searching the inactive Players Field
        if (canSearch) {
            ui.drawSearchPT(handler.game.getInactivePlayer().getField().getBoatPos());
        }
    }

    public static void playerShoots5() throws InterruptedException {
        handler.game.getActivePlayer().setShoots5(true);
    }

    public void switchTurn() {
        if (gameState == GameState.END) {
            new EndUI(this);
        }
        if (player1.getHasTurn()) {
            player1.setHasTurn(false);
            player2.setHasTurn(true);
        } else {
            player2.setHasTurn(false);
            player1.setHasTurn(true);
        }
        getActivePlayer().getField().setBoatColors(Color.BLACK);
        getInactivePlayer().getField().setBoatColors(Color.BLUE);
        getInactivePlayer().getField().updateField();
        ui.removeShot();
    }

    public ID getPlayerTurn() {
        if (player1.getHasTurn())
            return player1.getID();
        else
            return player2.getID();
    }

    public Player getActivePlayer() {
        if(player1.getHasTurn())
            return player1;
        else
            return player2;
    }

    public Player getInactivePlayer() {
        if(player2.getHasTurn())
            return player1;
        else
            return player2;
    }

    public Field getField1() {
        return field1;
    }

    public Field getField2() {
        return field2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}