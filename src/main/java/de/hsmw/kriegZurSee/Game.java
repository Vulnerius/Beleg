package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.constants.BoatPlacements;
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
    public static int BOARD_WIDTH_HEIGHT = 240;


    public Game() {
        field1 = new Field(ID.MyField, 20, 30, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        field2 = new Field(ID.EnemyField, 20, 330, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
    }

    public boolean searchField(ID id, Point2D mouseClick) {
        if (id.equals(ID.EnemyField))
            return field2.searchForMatching(mouseClick);
        else if (id.equals(ID.MyField))
            return field1.searchForMatching(mouseClick);

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

    public Field getField1() {
        return field1;
    }

    public Field getField2() {
        return field2;
    }

    public static void main(String[] args) {
        launch(args);
    }
}