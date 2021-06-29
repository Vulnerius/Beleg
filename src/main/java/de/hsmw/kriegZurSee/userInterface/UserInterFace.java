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
import javafx.scene.control.TextField;
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

    private final Game game;

    private final Stage stage;
    private final Group root;
    private final Scene scene;
    private final BorderPane sceneBP;
    private final AnchorPane fieldAnchorPane;
    private final VBox buttonBox;
    public final TextField tOUT;
    public final Label shotCount;

    private final Circle referenceCircle;


    public UserInterFace(Game game, Stage stage, Handler handler) {
        referenceCircle = new Circle(1, 1, 0);
        this.game = game;
        this.stage = stage;
        root = new Group();
        sceneBP = new BorderPane();

        fieldAnchorPane = new AnchorPane(root);
        fieldAnchorPane.getChildren().addAll(game.getField1().getPosition(), game.getField2().getPosition());
        fieldAnchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        VBox labels = new VBox(280);
        Label field1 = new Label("Player 1 Field");
        Label field2 = new Label("Player 2 Field");
        labels.getChildren().addAll(field1, field2);
        fieldAnchorPane.getChildren().add(labels);
        for (Boat b : game.getField1().getBoats()) {
            fieldAnchorPane.getChildren().add(b.getPosition());
        }

        for (Boat b : game.getField2().getBoats()) {
            fieldAnchorPane.getChildren().add(b.getPosition());
        }

        buttonBox = new VBox(25);
        buttonBox.setAlignment(Pos.CENTER);

        Button restore = new Button("restore");
        restore.setOnAction(ButtonClick.onRestore());
        Button searchPt = new Button("searchPt");
        searchPt.setOnAction(ButtonClick.onSearch());

        buttonBox.getChildren().addAll(restore, searchPt);
        Button shoot5 = new Button("shoot 5 shots");
        shoot5.setOnAction(ButtonClick.onShoot5());
        buttonBox.getChildren().add(shoot5);
        shotCount = new Label("ShotCount of " + game.getActivePlayer().getID() + " : " + game.getActivePlayer().getShotCount());
        buttonBox.getChildren().add(shotCount);

        tOUT = new TextField();
        tOUT.setAlignment(Pos.BASELINE_RIGHT);
        buttonBox.getChildren().add(tOUT);
        buttonBox.setPadding(new Insets(40, 15, 20, 140));

        sceneBP.setLeft(fieldAnchorPane);
        sceneBP.setRight(buttonBox);

        scene = new Scene(sceneBP, WIDTH, HEIGHT);
        initializeUI();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public void stop() {
        stage.close();
    }

    private void initializeUI() {
        drawGrid(game.getField1(), fieldAnchorPane);
        drawGrid(game.getField2(), fieldAnchorPane);
        scene.setFill(Color.valueOf("#eee"));
    }

    public void drawGrid(Field field, AnchorPane anchorPane) {
        int x = (int) field.getPosition().getX();
        int y = (int) field.getPosition().getY();
        for (int index = 0; index < 7; index++) {
            /*x,y,width,height
           creating a Field of 6x6 divided lines are divided by (2px) thick lines
             */
            Rectangle verticalLine = getLine(x + 40 * index, y, BOARD_WIDTH_HEIGHT, 2);
            Rectangle horizontalLine = getLine(x, y + 40 * index, 2, BOARD_WIDTH_HEIGHT);
            /*
            adding lines to AnchorPane
             */
            anchorPane.getChildren().addAll(verticalLine, horizontalLine);
        }
    }

    private Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle(x, y, width, height);
        line.setFill(Color.BLACK);
        return line;
    }

    public void drawHitCircle(int x, int y) {
        Circle shot = new Circle(x, y, 10);
        shot.setFill(Color.WHITESMOKE);
        tOUT.setText(game.getActivePlayer().getID() + " HIT");
        fieldAnchorPane.getChildren().add(shot);
    }

    public void removeShot() {
        fieldAnchorPane.getChildren().removeIf(d -> d.getClass().equals(referenceCircle.getClass()));
    }

    public Scene getScene() {
        return scene;
    }

    public void drawMissCircle(int x, int y) {
        Circle miss = new Circle(x, y, 10);
        miss.setFill(Color.DARKRED);
        fieldAnchorPane.getChildren().add(miss);
        tOUT.setText(game.getActivePlayer().getID() + " missed");
    }

    public void drawSearchPT(Circle boatPos) {
        boatPos.setFill(Color.DARKBLUE);
        fieldAnchorPane.getChildren().add(boatPos);
    }

    public Stage getStage() {
        return stage;
    }
}
