package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.gameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.GameState;
import de.hsmw.kriegZurSee.gameObjects.boats.HeliLandingBoat;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class EndUI {

    public EndUI(Game game, Stage stage) {
        VBox vBox = new VBox(25);
        Button playAgain = new Button("Play Again");
        //code for resetting everything for a new Game
        playAgain.setOnAction(event -> {
            stage.close();
            game.setGameState(GameState.NEW);
            for (Boat b : game.getActivePlayer().getField().getBoats()) {
                Arrays.fill(b.getHitPointCounter(), 0);
                if (b.getID().equals(ID.HeliLandingBoat))
                    ((HeliLandingBoat) b).setCanBeDamaged(false);
            }
            for (Boat b : game.getInactivePlayer().getField().getBoats()) {
                Arrays.fill(b.getHitPointCounter(), 0);
                if (b.getID().equals(ID.HeliLandingBoat))
                    ((HeliLandingBoat) b).setCanBeDamaged(false);
            }
            game.getActivePlayer().resetShotCount();
            game.getInactivePlayer().resetShotCount();
            game.switchTurn();
        });
        //closing the Application
        Button exit = new Button("exit");
        exit.setOnAction(event -> {
            game.ui.stop();
            stage.close();
        });
        //telling Players who won
        Label whoWon = new Label(game.getActivePlayer().getID() + " won!");
        Label free = new Label();
        vBox.getChildren().addAll(free, whoWon, playAgain, exit);
        vBox.setPadding(new Insets(50,50,50,50));
        Scene scene = new Scene(vBox, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
