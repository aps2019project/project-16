package newView.GraphicalElements.battle;

import controllers.InGameController;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.GraphicalElements.ScaleTool;
import view.views.InGameView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class CheatPane extends Pane {
    private Image buttonImage = new Image(new FileInputStream("src/newView/resources/buttons/cheatButton.png"));
    private ImageView buttonView = new ImageView(buttonImage);
    private TextField cheatField = new TextField("");

    private final static double BUTTON_LENGTH = 30;

    public CheatPane() throws FileNotFoundException {
        ScaleTool.relocate(this, WIDTH * 0.8, HEIGHT * 0.4);
        ScaleTool.resizeImageView(buttonView, BUTTON_LENGTH, BUTTON_LENGTH);
        ScaleTool.relocate(buttonView, BUTTON_LENGTH * 2, BUTTON_LENGTH);
        setMouseEventsFor(buttonView);

        cheatField.setPromptText("CHEAT KEY");
        cheatField.setStyle("-fx-prompt-text-fill: gray");

        this.getChildren().addAll(cheatField, buttonView);

        buttonView.setOnMouseClicked(event ->
                new InGameController(new InGameView()).cheat(cheatField.getText()));
    }

    private void setMouseEventsFor(Node node) {
        node.setOpacity(0.7);
        node.setOnMouseExited(event -> setOpacity(0.7));
        node.setOnMouseEntered(event -> setOpacity(1));
    }
}
