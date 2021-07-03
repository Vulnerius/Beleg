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


public class Field extends GameObject implements FieldLogic{

    private Boat[] boats;
    private final Game game;
    private int fiveShotCounter = 1;

    public Field(Game game, de.hsmw.kriegZurSee.constants.ID id, int x, int y, int width, int height) {
        super(id, x, y, width, height, Color.BLUE);
        this.game = game;
        boats = FieldLogic.setBoats(id);
    }

    //updating the field after userInputs
    public void updateField() {
        updateBS_HLB();
        if (game.getActivePlayer().getField().getID() == getID()) {
            updateHitPoints();
        }
    }

    //resetting the CoolDowns when switching Turns
    public void resetCoolDowns() {
        for (Boat b : boats) {
            if (b.isHasCoolDown()) {
                b.setHasCooldown();
            }
        }
    }

    //method to show the Active Players hit Boats (on the left of the UI)
    //it is showing the inverted Hitpoints
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

    //checking whether or not all Boats are dead in the Field
    public boolean allBoatsDead() {
        int countOfDeadBoats = 0;
        for (Boat boat : boats) {
            if (boat.isBoatDrowned())
                countOfDeadBoats++;
        }
        return countOfDeadBoats == boats.length;
    }

    //setting the Color of all Boats - after switching Turns
    public void setBoatColors(Color col) {
        for (Boat boat : boats) {
            if (!boat.isBoatDrowned())
                boat.setColor(col);
        }
    }

    //checking if the Battleship is drowned and updating the HeliLandingBoat accordingly each updateField()
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

    //setting boats after switching Turns
    public void newRound() {
        boats = FieldLogic.setBoats(getID());
    }

    //checking for hits in UI
    private boolean searchingForMouseClickInField(Point2D mouseclick) {
        for (Boat boat : boats) {
            if (boat.didIGotHit(mouseclick))
                return true;
        }
        return false;
    }

    //returning which Boat got hit by a MouseClick
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

    //method to return a Position where a Boat is located on the Field, except the only left Boat is the HeliLandingBoat, because it can't be discovered
    public Circle getBoatPos() {
        Random i = new Random();
        int found = i.nextInt(boats.length);
        Boat discovered = boats[found];
        int saveCounter = 0;
        while (discovered.isBoatDrowned() || discovered.getID() == ID.HeliLandingBoat) {
            found = i.nextInt(boats.length);
            discovered = boats[found];
            if (saveCounter == 5) {
                game.ui.update("no alive Boat found, HeliLandingBoat can not be found");
                break;
            }
            saveCounter++;
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

    //method to access the Field, when a Player shoots at it
    private void setOnShot(Point2D mouseClick) {
        //calling that the active Player did shoot and lowering his shotCount
        game.getActivePlayer().playerDidShoot();
        //disabling the shoots5 Button, for a fair game
        game.ui.disableShoots5Button(true);
        //checking if the Field has living boats else setting GameState to End + switching Turns for EndUI
        if (allBoatsDead()) {
            game.setGameState(GameState.END);
            game.switchTurn();
        }
        //checking if the Player can shoot and otherwise resetting his shotcount and switching turns
        if (Integer.parseInt(game.getActivePlayer().getShotCount()) <= 0) {
            game.getActivePlayer().resetShotCount();
            game.ui.update(game.getActivePlayer().getID() + "shotCount restored!");
            game.switchTurn();
        }
        //initializing a boolean to check if a player can shoot 5 shots, if called
        boolean canShoot = false;
        for (Boat searchingForBattleShip : game.getActivePlayer().getField().getBoats()) {
            if (searchingForBattleShip.getID().equals(ID.BattleShip)) {
                canShoot = !searchingForBattleShip.isBoatDrowned();
            }
        }
        //if statement to check if a Player can shoot 5 shots
        if (game.getActivePlayer().isShoots5() && canShoot) {
            //if player did hit -> drawing HitPoint + adding HitPoint to hitpointsArray of the Boat
            if (searchingForMouseClickInField(mouseClick)) {
                Boat gotShot = searchForAliveBoat(mouseClick);
                if(gotShot != null) {
                    gotShot.addHitPoint(mouseClick);
                    game.ui.drawHitCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                }
            }
            //else drawing a missCircle
            else if (!searchingForMouseClickInField(mouseClick)) {
                game.ui.drawMissCircle((int) mouseClick.getX(), (int) mouseClick.getY());
            }
            //checking the fiveShotCounter
            if (fiveShotCounter == 5) {
                //after a player shot 5 times, reset counter to 0, update the TextField to tell the Players they switched turns
                fiveShotCounter = 0;
                game.ui.update("switched Turns");
                game.getInactivePlayer().getField().updateField();
                if (!game.getInactivePlayer().getField().allBoatsDead()) {
                    game.switchTurn();
                }
            }
            fiveShotCounter++;
        } else {
            //single shot
            if (searchingForMouseClickInField(mouseClick)) {
                //hit
                Boat gotShot = searchForAliveBoat(mouseClick);
                if (gotShot != null) {
                    gotShot.addHitPoint(mouseClick);
                    game.ui.drawHitCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                }
            } else {
                //or miss
                game.ui.drawMissCircle((int) mouseClick.getX(), (int) mouseClick.getY());
                game.switchTurn();
            }
        }
    }
}