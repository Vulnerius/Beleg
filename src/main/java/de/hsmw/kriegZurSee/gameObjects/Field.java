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
        for (Boat b : boats) {
            if (b.isHasCooldown())
                b.setHasCooldown();
        }
        if (allBoatsDead()) {
            game.switchTurn();
        }
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
        updateField();
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
                    game.ui.update("Repaired");
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
            game.ui.update("shooting 5 shots");
            if (searchingForMouseClickInField(mouseClick)) {
                Boat gotShot = searchForAliveBoat(mouseClick);
                gotShot.addHitPoint(mouseClick);
                game.ui.drawHitCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                game.ui.update(game.getActivePlayer().getID() + " hit");
            } else if (!searchingForMouseClickInField(mouseClick)) {
                game.ui.drawMissCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                game.ui.update(game.getActivePlayer().getID() + " missed");
            }
            fiveShotCounter++;
            if (fiveShotCounter == 5) {
                fiveShotCounter = 0;
                game.ui.update("switched Turns");
                game.switchTurn();
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
        game.getInactivePlayer().getField().updateField();
    }
}