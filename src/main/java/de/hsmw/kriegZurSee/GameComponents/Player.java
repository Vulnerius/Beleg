package de.hsmw.kriegZurSee.GameComponents;

import java.awt.*;

public class Player {
    public final String name;
    public Player enemy;
    public final Field playerField = new Field();
    private int shotCount = 15;
    public boolean hasTurn = false;

    public Player(String name){
        this.name = name;
    }
    public void setEnemy(Player enemy){
        this.enemy = enemy;
    }
    public Player(String name, Player enemy) {
        this.name = name;
        this.enemy = enemy;
    }

    public int getShotCount() {
        return shotCount;
    }

    public void resetShotCount() {
        shotCount = 15;
    }

    public Point searchInEnemyField() {
        if (playerField.cs.canSearchForEnemy())
            return enemy.playerField.searchFct();
        return null;
    }

    public void switchTurn() {
        hasTurn = !hasTurn;
    }

    public boolean shootOne(Point p0) {
        shotCount--;
        return enemy.playerField.anyBoatGotHit(p0);

    }

    public boolean shootTriple(Point p1, Point p2, Point p3) {
        if (!playerField.cs.boatIsDisabled() && shotCount >= 3) {
            shootOne(p1);
            shootOne(p2);
            shootOne(p3);
            return true;
        }
        return false;
    }

    public void shootQuadruple(Point p1, Point p2, Point p3, Point p4, Point p5) {
        if (!playerField.bs.boatIsDisabled() && playerField.bs.canShoot5()) {
            shootOne(p1); shootOne(p2); shootOne(p3); shootOne(p4); shootOne(p5);
        }
    }

}
