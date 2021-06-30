package de.hsmw.kriegZurSee.gameObjects;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.gameObjects.boats.Boat;
import de.hsmw.kriegZurSee.gameObjects.boats.HeliLandingBoat;
import de.hsmw.kriegZurSee.constants.GameState;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.fieldLogic.FieldLogic;
import javafx.geometry.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Random;


public class Field extends GameObject {

    private Boat[] boats;
    private final Game game;
    private int fiveShotCounter = 0;

    public Field(Game game, de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height) {
        super(id, x, y, width, height, Color.BLUE);
        this.game = game;
        boats = FieldLogic.setBoats(id);
    }

    public void updateField() {
        updateBS_HLB();
        if (game.getActivePlayer().getField().getID() == getID()) {
            updateHitPoints();
        }
    }

    public void resetCoolDowns(){
        for (Boat b : boats) {
            if (b.isHasCooldown()) {
                b.setHasCooldown();
            }
        }
    }
    private void updateHitPoints() {
        game.ui.hitPoints.getChildren().clear();
        int posX = 25;
        int posY = 50;
        for (Boat b : boats) {
            Text boatId = new Text(b.getID().toString());
            boatId.setX(posX);
            boatId.setY(posY);
            game.ui.hitPoints.getChildren().add(boatId);
            posX += 150;
            posY -= 10;
            for (int i : b.getHitPointCounter()) {
                Rectangle hitPoint = new Rectangle(posX, posY, 20, 20);
                if (i == 0) {
                    hitPoint.setFill(Color.DARKBLUE);
                } else {
                    hitPoint.setFill(Color.DARKRED);
                }
                game.ui.hitPoints.getChildren().add(hitPoint);
                posX += 20;
            }
            posY += 40;
            posX = 25;
        }
        Text activePlayer = new Text(game.getActivePlayer().getID().toString());
        activePlayer.setX(12);
        activePlayer.setY(12);
        game.ui.hitPoints.getChildren().add(activePlayer);
    }

    public boolean allBoatsDead() {
        int countOfDeadBoats = 0;
        for (Boat boat : boats) {
            if (boat.isBoatDrowned())
                countOfDeadBoats++;
        }
        return countOfDeadBoats == boats.length;
    }

    public void setBoatColors(Color col) {
        for (Boat boat : boats) {
            if (!boat.isBoatDrowned())
                boat.setColor(col);
        }
    }

    private void updateBS_HLB() {
        boolean bsIsDead = false;
        for (Boat b : boats) {
            if (b.getID() == ID.BattleShip && b.isBoatDrowned())
                bsIsDead = true;
            if (b.getID() == ID.HeliLandingBoat) {
                if (bsIsDead) {
                    ((HeliLandingBoat) b).bsIsDestroyed();
                }
            }
        }
    }

    public void newRound() {
        boats = FieldLogic.setBoats(getID());
    }

    private boolean searchingForMouseClickInField(Point2D mouseclick) {
        for (Boat boat : boats) {
            if (boat.didIGotHit(mouseclick))
                return true;
        }
        return false;
    }

    public Boat searchForAliveBoat(Point2D mouseClick) {
        for (Boat boat : boats) {
            if (boat.didIGotHit(mouseClick) && !boat.isBoatDrowned()) {
                System.out.println(Arrays.toString(boat.getHitPointCounter()));
                return boat;
            }
        }
        return null;
    }


    public Boat[] getBoats() {
        return boats;
    }

    public Circle getBoatPos() {
        Random i = new Random();
        int found = i.nextInt(boats.length);
        Boat discovered = boats[found];
        while (discovered.isBoatDrowned() || discovered.getID() == ID.HeliLandingBoat) {
            found = i.nextInt(boats.length);
            discovered = boats[found];
        }
        return new Circle(discovered.getPosition().getX() + 15, discovered.getPosition().getY() + 15, 10);
    }

    //ID is activePlayersField --> if id==active-> Repair
    public void mouseInput(ID id, Point2D mouseClick) {
        if (id.equals(getID())) {
            if (searchingForMouseClickInField(mouseClick))
                if (game.getActivePlayer().canRepair()) {
                    game.getActivePlayer().repair(mouseClick);
                }
        } else
            game.getInactivePlayer().getField().setOnShot(mouseClick);
    }

    public void setOnShot(Point2D mouseClick) {
        game.getActivePlayer().playerDidShoot();
        if (allBoatsDead()) {
            game.setGameState(GameState.END);
        }
        if (Integer.parseInt(game.getActivePlayer().getShotCount()) <= 0) {
            game.getActivePlayer().resetShotCount();
            game.ui.update(game.getActivePlayer().getID() + "shotCount restored!");
            game.switchTurn();
        }
        boolean canShoot = false;
        for (Boat searchingForBattleShip : game.getActivePlayer().getField().getBoats()) {
            if (searchingForBattleShip.getID().equals(ID.BattleShip)) {
                canShoot = !searchingForBattleShip.isBoatDrowned();
            }
        }
        if (game.getActivePlayer().isShoots5() && canShoot) {
            game.ui.update(game.getActivePlayer().getID() + " shoots 5 shots");
            if (searchingForMouseClickInField(mouseClick)) {
                Boat gotShot = searchForAliveBoat(mouseClick);
                gotShot.addHitPoint(mouseClick);
                game.ui.drawHitCircle((int) mouseClick.getX(), (int) mouseClick.getY());
            } else if (!searchingForMouseClickInField(mouseClick)) {
                game.ui.drawMissCircle((int) mouseClick.getX(), (int) mouseClick.getY());
            }
            fiveShotCounter++;
            if (fiveShotCounter == 5) {
                fiveShotCounter = 0;
                game.ui.update("switched Turns");
                game.getInactivePlayer().getField().updateField();
                if (!game.getInactivePlayer().getField().allBoatsDead()) {
                    game.switchTurn();
                }
            }
        } else {
            if (searchingForMouseClickInField(mouseClick)) {
                Boat gotShot = searchForAliveBoat(mouseClick);
                gotShot.addHitPoint(mouseClick);
                game.ui.drawHitCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                game.ui.update(game.getActivePlayer().getID() + " hit");
            } else {
                game.ui.drawMissCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                game.ui.update(game.getActivePlayer().getID() + " missed");
                game.switchTurn();
            }
        }
    }
}