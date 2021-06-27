package de.hsmw.kriegZurSee.inputs;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
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
        ID id = game.getActivePlayer().getFieldID();

        if (x >= 20 && x <= 260) {
            //checking x -> BOARD
            if (y >= 30 && y <= 270) {
                //checking y -> Field1
                game.getField1().mouseInput(id,mouseClick);
            } else if (y >= 330 && y <= 610) {
                game.getField2().mouseInput(id,mouseClick);
            }
        }
        game.getField1().updateField();
        game.getField2().updateField();
    }
}
                /*if (game.getPlayerTurn() == ID.Player1) {
                        if (game.getPlayer1().canRepair()) {
                            game.getPlayer1().repair(mouseClick);
                        }
                    }
                    //active Player : player2 :: shoot
                    if (game.getPlayerTurn() == ID.Player2 && !game.getPlayer2().isHasShot()) {
                        game.getPlayer2().playerDidShoot();
                        game.getPlayer2().setHasShot(true);
                        if (game.searchField(ID.Player1Field, mouseClick)) {
                            Game.ui.drawHitCircle(x, y);
                            game.getPlayer2().setHasShot(false);
                            Game.ui.tOUT.setText("You Hit");
                        } else {

        Game.ui.drawMissCircle(x, y);
        Game.ui.tOUT.setText("You Missed");
        game.switchTurn();
    }


}*/
