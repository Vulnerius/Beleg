package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.inputs.Handler;
import de.hsmw.kriegZurSee.userInterface.UserInterFace;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;


public class Game extends Application {
    public static UserInterFace ui;
    public static Handler handler;
    private final Field field1;
    private final Field field2;
    private static Player player1;
    private static Player player2;

    public static int BOARD_WIDTH_HEIGHT = 240;


    public Game() {
        field1 = new Field(ID.Player1Field, 20, 30, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        field2 = new Field(ID.Player2Field, 20, 330, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        player1 = new Player(field1, ID.Player1);
        player2 = new Player(field2, ID.Player2);
        player1.setHasTurn();
    }


    public static void restoreActivePlayerShots() {
        Player he = handler.game.getActivePlayer();
        he.resetShotCount();
        handler.game.switchTurn();
    }

    public static void searchFor() {
        Player playerToSearch = handler.game.getActivePlayer();
        boolean canSearch = false;
        for (Boat b : playerToSearch.getField().getBoats()) {
            if (b.getID().equals(ID.Corsair) && !b.isBoatDrowned())
                canSearch = true;
        }
        // active Player is searching the inactive Players Field
        if (canSearch) {
            if (playerToSearch == player1)
                playerToSearch = player2;
            else
                playerToSearch = player1;
            ui.drawSearchPT(playerToSearch.getField().getBoatPos());
        }
    }

    public static void playerShoots5() {
        for (int temp = 0; temp < 5; temp++) {
            handler.game.getActivePlayer().setHasShot(false);
        }
    }

    public boolean searchField(ID id, Point2D mouseClick) {
        if (id.equals(ID.Player2Field)) {
            return field2.searchForMatching(mouseClick);
        } else if (id.equals(ID.Player1Field)) {
            return field1.searchForMatching(mouseClick);
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World");
        Game game = new Game();
        handler = new Handler(game);
        //set Storage File here
        ui = new UserInterFace(game, primaryStage, handler);
        primaryStage.show();
    }

    public void switchTurn() {
        player1.setHasTurn();
        if (player1.getHasTurn()) {
            player1.setHasShot(false);
            player1.getField().updateField();
        }
        player2.setHasTurn();
        if (player2.getHasTurn()) {
            player2.setHasShot(false);
            player2.getField().updateField();
        }
        ui.removeShot();
    }

    public ID getPlayerTurn() {
        if (player2.getHasTurn())
            return player2.getID();
        else
            return player1.getID();
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

    public static void main(String[] args) {
        launch(args);
    }

    public Player getActivePlayer() {
        ID playerID = getPlayerTurn();
        if (playerID == ID.Player1)
            return player1;
        else
            return player2;
    }

}