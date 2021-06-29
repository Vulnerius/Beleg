package de.hsmw.kriegZurSee.GameObjects;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.GameObjects.boats.HeliLandingBoat;
import de.hsmw.kriegZurSee.constants.GameState;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.fieldLogic.FieldLogic;
import de.hsmw.kriegZurSee.userInterface.EndUI;
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
        newRound();
        this.game = game;
    }

    public void updateField() {
        updateBS_HLB();
        if(allBoatsDead()){
            game.setGameState(GameState.END);
            game.switchTurn();
        }
        Game.ui.shotCount.setText("ShotCount of "  + game.getActivePlayer().getID() + " : " + game.getActivePlayer().getShotCount());
    }

    private boolean allBoatsDead() {
        int countOfDeadBoats = 0;
        for (Boat boat : boats) {
            if (boat.isBoatDrowned())
                countOfDeadBoats++;
        }
        return countOfDeadBoats == boats.length;
    }

    public void setBoatColors(Color col) {
        for (Boat boat : boats) {
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

    @Override
    public void tick() {

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
        if (Integer.parseInt(game.getActivePlayer().getShotCount()) <= 0) {
            game.switchTurn();
            Game.ui.tOUT.setText("you have to restore your Shots next Round!" + game.getActivePlayer());
        }
        if (game.getActivePlayer().isShoots5()) {
            if (searchingForMouseClickInField(mouseClick)) {
                Boat gotShot = searchForAliveBoat(mouseClick);
                gotShot.addHitPoint(mouseClick);
                Game.ui.drawHitCircle((int) mouseClick.getX(), (int) mouseClick.getY());
            } else if (!searchingForMouseClickInField(mouseClick)) {
                Game.ui.drawMissCircle((int) mouseClick.getX(), (int) mouseClick.getY());
            } else if (fiveShotCounter == 5) {
                game.switchTurn();
                fiveShotCounter = 0;
            }
            fiveShotCounter++;
        } else {
            if (searchingForMouseClickInField(mouseClick)) {
                Boat gotShot = searchForAliveBoat(mouseClick);
                gotShot.addHitPoint(mouseClick);
                Game.ui.drawHitCircle((int) mouseClick.getX(), (int) mouseClick.getY());
            } else {
                Game.ui.drawMissCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                game.switchTurn();
            }
        }
        game.getInactivePlayer().getField().updateField();
    }
}