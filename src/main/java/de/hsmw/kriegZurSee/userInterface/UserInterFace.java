package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.GameObjects.Field;
import de.hsmw.kriegZurSee.constants.ID;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class UserInterFace implements IUserInterfaceContract, EventHandler<MouseEvent> {
    private IUserInterfaceContract.EventListener listener;
    private final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9 ;
    private final int BOARD_WIDTH_HEIGHT = 240;

    private final Stage stage;
    private final Group root;
    protected final Field field1;
    protected final Field field2;

    public UserInterFace(Stage stage){
        this.stage = stage;
        root = new Group();
        field1 = new Field(ID.MyField,20,30, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        field2 = new Field(ID.EnemyField,20,330, BOARD_WIDTH_HEIGHT, BOARD_WIDTH_HEIGHT);
        initializeUI();
        drawText();
    }

    private void drawText() {

    }

    private void initializeUI() {
        root.getChildren().addAll(field1.getPosition(), field2.getPosition());
        drawGrid(field1,root);
        drawGrid(field2,root);
        Scene scene = new Scene(root,WIDTH,HEIGHT);
        scene.setFill(Color.valueOf("#eee"));
        stage.setScene(scene);
    }

    private void drawGrid(Field field, Group root) {
        int x = (int)field.getPosition().getX();
        int y = (int)field.getPosition().getY();
        int thickness = 2;
        int index = 0;
        while (index < 7) {
            Rectangle verticalLine = getLine(x +40 * index, y, BOARD_WIDTH_HEIGHT, thickness);
            Rectangle horizontalLine = getLine(x, y +40 * index, thickness, BOARD_WIDTH_HEIGHT);
            root.getChildren().addAll(verticalLine, horizontalLine);
            index++;
        }
    }
    private Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle();
        line.setX(x);
        line.setY(y);
        line.setHeight(height);
        line.setWidth(width);
        line.setFill(Color.BLACK);
        return line;
    }

    public void updateField(ID id, int x, int y){
        switch (id){
            case MyField : {
                Rectangle r = new Rectangle();
                r.setX(x);
                r.setY(y);
                r.setWidth(40);
                r.setHeight(40);
                r.setFill(Color.RED);
                root.getChildren().add(r);
            }
            case EnemyField:  {
                Rectangle r = new Rectangle();
                r.setX(x);
                r.setY(y);
                r.setWidth(40);
                r.setHeight(40);
                r.setFill(Color.RED);
                root.getChildren().add(r);
            }
        }
    }

    @Override
    public void handle(MouseEvent mEvent) {
        if (mEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if (mEvent.getY() >= 330) {
                handleMouseInput(ID.EnemyField, mEvent.getX(), mEvent.getY());
            } else {
                handleMouseInput(ID.MyField, mEvent.getX(), mEvent.getY());
            }
        }
    }

    public void setListener(IUserInterfaceContract.EventListener list){
        listener = list;
    }
    private void handleMouseInput(ID id, double x, double y) {
        listener.onFieldInput(id,(int)x,(int)y);
    }
}
