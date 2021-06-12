package de.hsmw.kriegZurSee.MainScene;

import de.hsmw.kriegZurSee.GameComponents.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;


public class Controller {
    public Pane myGrid;
    public GridPane enemyGrid;
    public Label shotCount;
    public Label searchPoint;
    public ImageView boat1Image;
    public Pane actionPane;
    public TextField shootX;
    public TextField shootY;
    public ChoiceBox actionChoice;
    public Button go;
    public TextArea consoleView;
    public Scene MainScene;
    public Stage mainStage;

    public void onShowing() {
        mainStage.setResizable(false);
        makeGridToPane(myGrid, 6);
    }


    public void accessGridPane() {

    }

    public static Pane makeGridToPane(Pane p, int n) {
        double width = p.getPrefHeight() / n;
        Rectangle[][] rectangles = new Rectangle[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rectangles[i][j] = new Rectangle();
                rectangles[i][j].setX(i * width);
                rectangles[i][j].setY(j * width);
                rectangles[i][j].setWidth(width);
                rectangles[i][j].setHeight(width);
                rectangles[i][j].setFill(javafx.scene.paint.Color.BLUE);
                rectangles[i][j].setStroke(Color.BLACK);
                p.getChildren().add(rectangles[i][j]);
            }
        }
        p.setOnMouseClicked(me -> {

                    double posX = me.getX();
                    double posY = me.getY();

                    int colX = (int) (posX / width);
                    int colY = (int) (posY / width);

                    rectangles[colX][colY].setFill(Color.RED);
                }

        );
        return p;
    }

}
