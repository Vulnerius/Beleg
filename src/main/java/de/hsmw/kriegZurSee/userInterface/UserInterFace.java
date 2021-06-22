package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class UserInterFace {
    private final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
    private final int BOARD_WIDTH_HEIGHT = 240;

    private final Stage stage;
    private final Group root;
    private final BorderPane sceneBP;
    private final AnchorPane fieldVBox;

    private final Field field1;
    private final Field field2;


    public UserInterFace(Stage stage) {
        this.stage = stage;

        root = new Group();
        sceneBP = new BorderPane();

        fieldVBox = new AnchorPane();
        field1 = new Field(ID.MyField, 20, 30, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        field2 = new Field(ID.EnemyField, 20, 330, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        fieldVBox.getChildren().addAll(field1.getPosition(),field2.getPosition());

        VBox buttonvBox = new VBox(50);
        buttonvBox.setAlignment(Pos.BASELINE_RIGHT);

        Button restore = new Button("restore");
        Button searchPt = new Button("searchPt");
        Button changeTurn = new Button("changeTurn");
        buttonvBox.getChildren().addAll(restore, searchPt, changeTurn);
        buttonvBox.setPadding(new Insets(25,20,20,40));

        sceneBP.setCenter(fieldVBox);
        sceneBP.setRight(buttonvBox);

        initializeUI();
    }

    private void initializeUI() {
        drawGrid(field1, fieldVBox);
        drawGrid(field2, fieldVBox);

        root.getChildren().add(sceneBP);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.valueOf("#eee"));
        stage.setScene(scene);
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

}
