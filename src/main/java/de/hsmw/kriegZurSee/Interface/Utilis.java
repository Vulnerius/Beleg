package de.hsmw.kriegZurSee.Interface;

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
}
