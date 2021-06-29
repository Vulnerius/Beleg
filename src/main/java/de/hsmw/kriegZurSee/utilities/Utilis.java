package de.hsmw.kriegZurSee.utilities;

import de.hsmw.kriegZurSee.gameObjects.boats.Boat;
import javafx.geometry.Point2D;

public interface Utilis {
    static int randInt(int start, int end){
        return (int) (start + (Math.random() * (end-start)))*40;
    }

    static Point2D randPtF1(){
        return new Point2D(20+randInt(1,6), 30+randInt(1,6));
    }

    static Point2D randPtF2(){
        return new Point2D(20+randInt(1,6), 330+randInt(1,6));
    }

    static int pointToIndex(Boat bo, Point2D point){
        if(bo.checkIsVert()){
            return (int) ((bo.getPosition().getX() + bo.getPosition().getWidth() - point.getX()) /40);
        }
        else return (int) (((bo.getPosition().getY() + bo.getPosition().getHeight()) - point.getY()) / 40);
    }
}
