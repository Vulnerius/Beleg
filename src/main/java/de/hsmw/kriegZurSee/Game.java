package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.gameObjects.Field;
import de.hsmw.kriegZurSee.gameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.GameState;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.inputs.Handler;
import de.hsmw.kriegZurSee.userInterface.EndUI;
import de.hsmw.kriegZurSee.userInterface.UserInterFace;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Game {
    public final UserInterFace ui;
    public static Handler handler;
    private GameState gameState;
    private final Field field1;
    private final Field field2;
    private static Player player1;
    private static Player player2;

    public static final int BOARD_WIDTH_HEIGHT = 320;


    public Game(Stage stage) {
        gameState = GameState.ACTIVE;
        handler = new Handler(this);
        field1 = new Field(this, ID.Player1Field, 20, 30, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        field2 = new Field(this, ID.Player2Field, 20, 380, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        player1 = new Player(this, field1, ID.Player1);
        player2 = new Player(this, field2, ID.Player2);
        ui = new UserInterFace(this, stage, handler);
        player1.setHasTurn(true);
        field1.updateField();
        player2.setHasTurn(false);
        field1.setBoatColors(Color.PURPLE);
        field2.setBoatColors(Color.BLUE);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static void restoreActivePlayerShots() {
        Player he = handler.game.getActivePlayer();
        he.resetShotCount();
        handler.game.ui.update(he.getID() + " restored shots");
        handler.game.switchTurn();
    }

    public static void playerSearchingForEnemyBoatPoint() {
        Player playerToSearch = handler.game.getActivePlayer();
        boolean canSearch = false;
        for (Boat b : playerToSearch.getField().getBoats()) {
            if (b.getID().equals(ID.Corsair) && !b.isBoatDrowned() && !b.isHasCooldown()) {
                canSearch = true;
                //b.setHasCooldown();
            }
        }
        // active Player is searching the inactive Players Field
        if (canSearch) {
            handler.game.ui.drawSearchPT(handler.game.getInactivePlayer().getField().getBoatPos());
            handler.game.ui.update("Point found");
        } else
            handler.game.ui.update("No RepairBoat found");
    }

    public static void playerShoots5() {
        handler.game.getActivePlayer().setShoots5(true);
    }

    public void switchTurn() {
        if (getInactivePlayer().getField().allBoatsDead()) {
            new EndUI(this, handler.game.ui.getStage());
            return;
        }
        if (gameState.equals(GameState.NEW)) {
            new Game(new Stage());
        }
        if (player1.getHasTurn()) {
            player1.setHasTurn(false);
            player2.setHasTurn(true);
            player2.getField().resetCoolDowns();
        } else {
            player2.setHasTurn(false);
            player1.setHasTurn(true);
            player1.getField().resetCoolDowns();
        }
        getActivePlayer().getField().setBoatColors(Color.PURPLE);
        getInactivePlayer().getField().setBoatColors(Color.BLUE);
        getInactivePlayer().getField().updateField();
        getActivePlayer().getField().updateField();
        ui.shotCount.setText(getActivePlayer().getID() + "@" + getActivePlayer().getShotCount() + " shots");
        ui.removeShot();
    }

    public Player getActivePlayer() {
        if (player1.getHasTurn())
            return player1;
        else
            return player2;
    }

    public Player getInactivePlayer() {
        if (player2.getHasTurn())
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

}