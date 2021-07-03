package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.gameObjects.boats.Boat;
import de.hsmw.kriegZurSee.gameObjects.Field;
import de.hsmw.kriegZurSee.inputs.ButtonClick;
import de.hsmw.kriegZurSee.inputs.Handler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static de.hsmw.kriegZurSee.Game.BOARD_WIDTH_HEIGHT;


public class UserInterFace {

    private final Game game;

    private final Stage stage;
    private final Scene scene;
    private final AnchorPane fieldAnchorPane;
    public final Label shotCount;
    public final TextField tOUT;
    public final Pane hitPoints;
    private final Button restore;
    private final Button shoot5;
    private final Button searchPt;

    private final Circle referenceCircle;


    public UserInterFace(Game game, Stage stage, Handler handler) {
        referenceCircle = new Circle(1, 1, 0);
        this.game = game;
        this.stage = stage;
        //Group for vertical and horizontal Field lines
        Group root = new Group();
        //BorderPane for Scene
        BorderPane sceneBP = new BorderPane();

        // UI AnchorPane for Fields & Boats
        fieldAnchorPane = new AnchorPane(root);
        fieldAnchorPane.getChildren().addAll(game.getField1().getPosition(), game.getField2().getPosition());
        fieldAnchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        // UI Labels
        VBox labels = new VBox(340);
        Label field1 = new Label("Player 1 Field");
        Label field2 = new Label("Player 2 Field");
        labels.getChildren().addAll(field1, field2);
        fieldAnchorPane.getChildren().add(labels);
        //adding Boats to the AnchorPane
        for (Boat b : game.getField1().getBoats()) {
            fieldAnchorPane.getChildren().add(b.getPosition());
        }
        for (Boat b : game.getField2().getBoats()) {
            fieldAnchorPane.getChildren().add(b.getPosition());
        }
        //UI BP-Right VBox for Buttons and Output
        VBox buttonBox = new VBox(25);
        // initialising Buttons with dedicated Action
        restore = new Button("restore");
        restore.setOnAction(ButtonClick.onRestore());
        searchPt = new Button("search Point");
        searchPt.setOnAction(ButtonClick.onSearch());
        buttonBox.getChildren().addAll(restore, searchPt);
        shoot5 = new Button("shoot 5 shots");
        shoot5.setOnAction(ButtonClick.onShoot5());
        buttonBox.getChildren().add(shoot5);
        // Label for ShotCount of the active Player
        shotCount = new Label(game.getActivePlayer().getID() + " @ " + game.getActivePlayer().getShotCount() + " shots");
        buttonBox.getChildren().add(shotCount);
        //TextField for Output to the Players
        tOUT = new TextField();
        buttonBox.getChildren().add(tOUT);
        buttonBox.setPadding(new Insets(40, 15, 20, 140));
        //Pane _hitPoints_ for showing the Active Player which Boats have been shot
        hitPoints = new Pane();
        sceneBP.setLeft(hitPoints);
        sceneBP.setCenter(fieldAnchorPane);
        sceneBP.setRight(buttonBox);

        //setting width and height of the Scene
        int HEIGHT = 800;
        int WIDTH = 800;
        scene = new Scene(sceneBP, WIDTH, HEIGHT);
        // adding Grid to the Fields
        initializeUI();
        this.stage.setWidth(1000);
        this.stage.setHeight(800);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public void stop() {
        stage.close();
    }

    private void initializeUI() {
        drawGrid(game.getField1(), fieldAnchorPane);
        drawGrid(game.getField2(), fieldAnchorPane);
    }

    public void drawGrid(Field field, AnchorPane anchorPane) {
        int x = (int) field.getPosition().getX();
        int y = (int) field.getPosition().getY();
        for (int index = 0; index < 9; index++) {
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

    //method for coloring a white Circle on the Field
    public void drawHitCircle(int x, int y) {
        Circle shot = new Circle(x, y, 10);
        shot.setFill(Color.WHITESMOKE);
        tOUT.setText(game.getActivePlayer().getID() + " HIT");
        fieldAnchorPane.getChildren().add(shot);
    }

    //removing all shots from the Active Player on game.switchturn()
    public void removeShot() {
        fieldAnchorPane.getChildren().removeIf(d -> d.getClass().equals(referenceCircle.getClass()));
    }

    //drawing a dark red circle to show that the player missed
    public void drawMissCircle(int x, int y) {
        Circle miss = new Circle(x, y, 10);
        miss.setFill(Color.DARKRED);
        fieldAnchorPane.getChildren().add(miss);
        tOUT.setText(game.getActivePlayer().getID() + " missed");
    }

    //drawing a dark blue circle where a boat is located
    public void drawSearchPT(Circle boatPos) {
        boatPos.setFill(Color.DARKBLUE);
        fieldAnchorPane.getChildren().add(boatPos);
    }

    public Stage getStage() {
        return stage;
    }

    public void update(String text) {
        tOUT.setText(text);
        shotCount.setText(game.getActivePlayer().getID() + " : " + game.getActivePlayer().getShotCount());
    }

    public void disableShoots5Button(boolean isDisabled) {
        shoot5.setDisable(isDisabled);
    }

}
