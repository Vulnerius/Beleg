package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.Game;
import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.inputs.ButtonClick;
import de.hsmw.kriegZurSee.inputs.Handler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class UserInterFace {
    private final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
    private final int BOARD_WIDTH_HEIGHT = 240;
    private final Handler handler;
    private final Game game;

    private final Stage stage;
    private final Group root;
    private final Scene scene;
    private final BorderPane sceneBP;
    private final AnchorPane fieldAnchorPane;

    private final Field field1;
    private final Field field2;


    public UserInterFace(Game game, Stage stage, Handler handler) {
        this.stage = stage;
        this.handler = handler;
        this.game = game;

        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);
        sceneBP = new BorderPane();

        fieldAnchorPane = new AnchorPane();
        field1 = new Field(ID.MyField, 20, 30, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        field2 = new Field(ID.EnemyField, 20, 330, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        fieldAnchorPane.getChildren().addAll(field1.getPosition(), field2.getPosition());
        fieldAnchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);

        VBox buttonvBox = new VBox(75);
        buttonvBox.setAlignment(Pos.BASELINE_RIGHT);

        Button restore = new Button("restore");
        restore.setOnAction(ButtonClick.onRestore());
        Button searchPt = new Button("searchPt");

        buttonvBox.getChildren().addAll(restore, searchPt);
        Button changeTurn = new Button("changeTurn");
        buttonvBox.getChildren().add(changeTurn);
        buttonvBox.setPadding(new Insets(40, 15, 20, 140));


        sceneBP.setCenter(fieldAnchorPane);
        sceneBP.setRight(buttonvBox);

        initializeUI();
        setScene(scene);
    }
    private void setScene(Scene scene){
        stage.setScene(scene);
    }
    private void show(){
        stage.show();
    }

    private void initializeUI() {
        drawGrid(field1, fieldAnchorPane);
        drawGrid(field2, fieldAnchorPane);
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

    public void drawRect(int x, int y) {
        Rectangle shot = new Rectangle(x, y, 40, 40);
        shot.setFill(Color.RED);
        fieldAnchorPane.getChildren().add(shot);
    }

    public void addShot() {

    }

    public Scene getScene() {
        return scene;
    }
}
