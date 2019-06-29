package newView.GraphicalElements.battle;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import newView.BattleView.GameGraphicListener;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMakers.SceneMaker.SCALE;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class FastForwardPane extends HBox {
    private Image downImage = new Image(new FileInputStream("src/newView/resources/buttons/downButton.png"));
    private Image upImage = new Image(new FileInputStream("src/newView/resources/buttons/upButton.png"));
    private ImageView downView = new ImageView(downImage);
    private Text text = new Text("1.0 X");
    private ImageView upView = new ImageView(upImage);

    private final static double LENGTH = 45;

    public FastForwardPane() throws FileNotFoundException {
        ScaleTool.relocate(this, WIDTH / 2 - 100, 60);
        ScaleTool.resizeImageView(downView, LENGTH, LENGTH);
        ScaleTool.resizeImageView(upView, LENGTH, LENGTH);
        text.setFill(Color.WHITE);
        text.setScaleX(2);
        text.setScaleY(2);

        this.setSpacing(LENGTH / 2 * SCALE);

        this.getChildren().addAll(downView, text, upView);

        downView.setOnMouseClicked(event -> text.setText(GameGraphicListener.decreaseVelocity()));
        upView.setOnMouseClicked(event -> text.setText(GameGraphicListener.increaseVelocity()));
        setMouseEventsFor(downView);
        setMouseEventsFor(upView);
    }

    private void setMouseEventsFor(Node node) {
        node.setOpacity(0.7);
        node.setOnMouseEntered(event -> node.setOpacity(1));
        node.setOnMouseExited(event -> node.setOpacity(0.7));
    }
}
