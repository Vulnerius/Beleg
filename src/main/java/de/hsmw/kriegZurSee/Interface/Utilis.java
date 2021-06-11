package de.hsmw.kriegZurSee.Interface;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public interface Utilis {
    static int randInt(final int start, final int end){
        return (int) (start + (Math.random() * (end-start)));
    }

    static Point randPt(int start, int end){
        return new Point ((int) (start + (Math.random() * (end-start))), ((int) (start + (Math.random() * (end-start)))));
    }

    static Point mirrorOnMiddle(Point point){
        return point;
    }


    //copy Paste from stackoverflow
     static Pane makeGridToPane(Pane p, int n){
        double width = p.getWidth()/n;
        javafx.scene.shape.Rectangle[][] rectangles = new javafx.scene.shape.Rectangle[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
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
        return p;
    }
}
