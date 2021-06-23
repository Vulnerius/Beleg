package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class MouseInput {

    Game game;

    public MouseInput(Game game, MouseEvent event) {
        this.game = game;
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            handle(event);
        }
    }

    public void handle(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Point2D mouseClick = new Point2D(x, y);
        if (x >= 20 && x <= 260) {
            if (y >= 30 && y <= 270) {
                //checking x -> BOARD
                //checking y -> Field1

                //getting active player -> repair on ownFieldInput
                if (game.getPlayerTurn() == ID.Player1) {
                    if (game.getPlayer1().canRepair()){
                        game.getPlayer1().repair(mouseClick);
                    System.out.println("anything");}
                }
                //active Player : player2 :: shoot
                else if (game.getPlayerTurn() == ID.Player2) {
                    game.getPlayer2().playerDidShoot();
                    if (game.searchField(ID.Player1Field, mouseClick)) {
                        Game.ui.drawCircle(x, y);
                    } else {
                        Game.ui.drawMissCircle(x, y);
                    }
                }
            }
            //field2
            else if (y >= 330 && y <= 610) {
                //player 2 repairs
                if (game.getPlayerTurn() == ID.Player2) {
                    if (game.getPlayer2().canRepair())
                        game.getPlayer2().repair(mouseClick);
                }
                // player 1 shoots
                else if (game.getPlayerTurn() == ID.Player1) {
                    game.getPlayer1().playerDidShoot();
                    if (game.searchField(ID.Player2Field, mouseClick)) {
                        Game.ui.drawCircle(x, y);
                    } else {
                        Game.ui.drawMissCircle(x, y);
                    }
                }
            }
        }
    }

}
