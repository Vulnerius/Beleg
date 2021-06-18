package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.GameObjects.BattleShip;
import de.hsmw.kriegZurSee.GameObjects.Boat;
import de.hsmw.kriegZurSee.GameObjects.Corsair;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {

    private final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9  ;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Hello World");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        Group root = new Group();
        Corsair cs = new Corsair(ID.Corsair,150,150,100,50,Color.BLUE);
        Rectangle pt = new Rectangle(140,140,10,10);
        pt.setFill(Color.BLACK);
        root.getChildren().addAll(cs.getPosition(), pt);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}