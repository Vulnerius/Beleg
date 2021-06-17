package de.hsmw.kriegZurSee.Utilities;

import javafx.geometry.Point2D;

public interface Utilis {
    static int randInt(final int start, final int end){
        return (int) (start + (Math.random() * (end-start)));
    }

    static Point2D randPt(int start, int end){
        return new Point2D((int) (start + (Math.random() * (end-start))), ((int) (start + (Math.random() * (end-start)))));
    }

}
