package dev.hsmw.kriegZurSee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Hello World");
        primaryStage = root;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}