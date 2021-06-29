package de.hsmw.kriegZurSee.Utilities;

import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import javafx.geometry.Point2D;

public interface Utilis {
    static int randInt(int start, int end){
        return (int) (start + (Math.random() * (end-start-2)))*40;
    }

    static Point2D randPt(int startX, int endX, int startY, int endY){
        return new Point2D(startX + randInt(1,6), startY+randInt(1,6));
    }

    static int pointToIndex(Boat bo, Point2D point){
        if(bo.checkIsVert()){
            return (int) ((bo.getPosition().getX() + bo.getPosition().getWidth() - point.getX()) /40);
        }
        else return (int) (((bo.getPosition().getY() + bo.getPosition().getHeight()) - point.getY()) / 40);
    }
}
