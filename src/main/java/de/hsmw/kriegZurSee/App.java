package de.hsmw.kriegZurSee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    FXMLLoader mainScene = new FXMLLoader(Objects.requireNonNull(getClass().getResource("MainScene.fxml")));;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage root = mainScene.load();
        primaryStage.setTitle("Hello World");
        primaryStage = root;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}