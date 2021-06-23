package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.GameObjects.boats.Boat;
import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.inputs.ButtonClick;
import de.hsmw.kriegZurSee.inputs.Handler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static de.hsmw.kriegZurSee.Game.BOARD_WIDTH_HEIGHT;


public class UserInterFace {
    private final int WIDTH = 600, HEIGHT = 600;

    private final Handler handler;
    private final Game game;

    private final Stage stage;
    private final Group root;
    private final Scene scene;
    private final BorderPane sceneBP;
    private final AnchorPane fieldAnchorPane;
    private Label shotCount;

    private final Circle referenceCircle;


    public UserInterFace(Game game, Stage stage, Handler handler) {
        referenceCircle = new Circle(1, 1, 0);
        this.stage = stage;
        this.handler = handler;
        this.game = game;

        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);
        sceneBP = new BorderPane();

        fieldAnchorPane = new AnchorPane();
        fieldAnchorPane.getChildren().addAll(game.getField1().getPosition(), game.getField2().getPosition());
        fieldAnchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        for (Boat b : game.getField1().getBoats()) {
            fieldAnchorPane.getChildren().add(b.getPosition());
        }
        for (Boat b : game.getField2().getBoats()) {
            fieldAnchorPane.getChildren().add(b.getPosition());
        }

        VBox buttonvBox = new VBox(75);
        buttonvBox.setAlignment(Pos.BASELINE_RIGHT);

        Button restore = new Button("restore");
        restore.setOnAction(ButtonClick.onRestore());
        Button searchPt = new Button("searchPt");

        buttonvBox.getChildren().addAll(restore, searchPt);
        Button changeTurn = new Button("changeTurn");
        changeTurn.setOnAction(ButtonClick.onChange());
        buttonvBox.getChildren().add(changeTurn);
        
        shotCount = new Label();
        shotCount.setText("ShotCount "+ game.getActivePlayer().getID() + " : " + game.getActivePlayer().getShotCount());
        buttonvBox.getChildren().add(shotCount);
        buttonvBox.setPadding(new Insets(40, 15, 20, 140));


        sceneBP.setCenter(fieldAnchorPane);
        sceneBP.setRight(buttonvBox);

        initializeUI();
        stage.setScene(scene);
        stage.show();
    }

    private void initializeUI() {
        drawGrid(game.getField1(), fieldAnchorPane);
        drawGrid(game.getField2(), fieldAnchorPane);
        root.getChildren().add(sceneBP);
        scene.setFill(Color.valueOf("#eee"));
    }

    public void drawGrid(Field field, AnchorPane fieldVBox) {
        int x = (int) field.getPosition().getX();
        int y = (int) field.getPosition().getY();
        for (int index = 0; index < 7; index++) {
            /*x,y,width,height
           creating a Field of 6x6 divided lines are divided by (2px) thick lines
             */
            Rectangle verticalLine = getLine(x + 40 * index, y, BOARD_WIDTH_HEIGHT, 2);
            Rectangle horizontalLine = getLine(x, y + 40 * index, 2, BOARD_WIDTH_HEIGHT);
            /*
            adding children to VBox
             */
            fieldVBox.getChildren().addAll(verticalLine, horizontalLine);
        }
    }

    private Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle(x, y, width, height);
        line.setFill(Color.BLACK);
        return line;
    }

    public void drawCircle(int x, int y) {
        Circle shot = new Circle(x, y, 10);
        shot.setFill(Color.WHITESMOKE);
        fieldAnchorPane.getChildren().add(shot);
    }

    public void removeShot() {
        game.switchTurn();
        shotCount.setText("ShotCount "+ game.getActivePlayer().getID() + " : " + game.getActivePlayer().getShotCount());
        fieldAnchorPane.getChildren().removeIf(d -> d.getClass().equals(referenceCircle.getClass()));
    }

    public Scene getScene() {
        return scene;
    }

    public void drawMissCircle(int x, int y) {
        Circle miss = new Circle(x, y, 10);
        miss.setFill(Color.DARKRED);
        fieldAnchorPane.getChildren().add(miss);
    }
}
