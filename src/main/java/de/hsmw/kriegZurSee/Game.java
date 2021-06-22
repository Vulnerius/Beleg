package de.hsmw.kriegZurSee;

import de.hsmw.kriegZurSee.inputs.Handler;
import de.hsmw.kriegZurSee.userInterface.UserInterFace;
import javafx.application.Application;
import javafx.stage.Stage;



public class Game extends Application {

    public static UserInterFace ui;
    public static Handler handler;


    public Game() {


    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World");
        Game game = new Game();
        handler = new Handler(game);
        //set Storage File here
        ui = new UserInterFace(game,primaryStage, handler);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}