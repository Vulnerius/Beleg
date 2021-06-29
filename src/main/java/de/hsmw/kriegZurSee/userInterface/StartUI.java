package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.Game;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class StartUI {
    public StartUI(Stage stage){
        BorderPane bp = new BorderPane();
        VBox vBoxCenter = new VBox(25);
        vBoxCenter.setPadding(new Insets(50,10,10,50));
        Label heading = new Label("Press Go to play");
        Button go = new Button("GO");
        go.setOnAction(event -> new Game(stage));
        vBoxCenter.getChildren().addAll(heading,go);
        bp.setCenter(vBoxCenter);
        Scene scene = new Scene(bp,200,200);
        stage.setScene(scene);
        stage.show();
    }
}
