package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.App;
import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.constants.GameState;
import de.hsmw.kriegZurSee.inputs.ButtonClick;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class EndUI {
    private final Stage stage;

    public EndUI(Game game) {
        stage = new Stage();
        VBox vBox = new VBox(25);
        Button playAgain = new Button("Play Again");
        playAgain.setOnAction(event ->{
            stage.close();
            game.setGameState(GameState.NEW);
            for(Boat b : game.getActivePlayer().getField().getBoats()){
                Arrays.fill(b.getHitPointCounter(),0);
            }for(Boat b : game.getInactivePlayer().getField().getBoats()){
                Arrays.fill(b.getHitPointCounter(),0);
            }
            game.getActivePlayer().resetShotCount();
            game.getInactivePlayer().resetShotCount();
            game.switchTurn();
        });
        Button exit = new Button("exit");
        exit.setOnAction(event -> {
            Game.ui.stop();
            stage.close();
        });
        Label whoWon = new Label(game.getActivePlayer().getID() + " won!");
        Label free = new Label();
        vBox.getChildren().addAll(free,whoWon,playAgain, exit);
        Scene scene = new Scene(vBox, 200, 200);
        stage.setScene(scene);
        stage.show();

    }
}
