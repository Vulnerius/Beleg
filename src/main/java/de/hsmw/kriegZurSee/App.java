package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.userInterface.UserInterFace;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static UserInterFace ui;



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World");
        ui = new UserInterFace(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}