package de.hsmw.kriegZurSee.Utilities;

import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import javafx.geometry.Point2D;

public interface Utilis {
    static int randInt(final int start, final int end){
        return (int) (start + (Math.random() * (end-start)));
    }

    static Point2D randPt(int start, int end){
        return new Point2D((int) (start + (Math.random() * (end-start))), ((int) (start + (Math.random() * (end-start)))));
    }

    static int pointToIndex(Boat bo, Point2D point){
        if(bo.checkIsVert()){
            return (int) ((bo.getPosition().getX() + bo.getPosition().getWidth() - point.getX()) /40 -1);
        }
        else return (int) (((bo.getPosition().getY() + bo.getPosition().getHeight()) - point.getY()) / 40 -1);
    }
}
