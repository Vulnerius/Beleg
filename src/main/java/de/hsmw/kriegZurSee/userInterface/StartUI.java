package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.Game;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URI;


public class StartUI {
    public StartUI(Stage stage){
        BorderPane bp = new BorderPane();
        VBox vBoxCenter = new VBox(25);
        vBoxCenter.setPadding(new Insets(50,10,10,50));
        Label heading = new Label("Press GO to play, Press HELP for ReadMe");
        Button go = new Button("GO");
        go.setOnAction(event -> new Game(stage));
        Button help = new Button("HELP");
        help.setOnAction(event -> {
            try {
                URI uri = new URI("https://github.com/Vulnerius/Beleg#readme");
                java.awt.Desktop.getDesktop().browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        vBoxCenter.getChildren().addAll(heading,go,help);
        bp.setCenter(vBoxCenter);
        Scene scene = new Scene(bp,400,400);
        stage.setScene(scene);
        stage.show();
    }
}
