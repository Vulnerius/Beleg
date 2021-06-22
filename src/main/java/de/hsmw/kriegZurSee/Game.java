package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.userInterface.UserInterFace;
import javafx.application.Application;
import javafx.stage.Stage;



public class Game extends Application {

    private static UserInterFace ui;
    //private final UserInterfaceListener uiListener;

    public Game() {
        //  uiListener = new UserInterFaceListener()

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World");

        //set Storage File here
        ui = new UserInterFace(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}