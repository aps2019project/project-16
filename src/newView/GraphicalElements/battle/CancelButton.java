package newView.GraphicalElements.battle;

import controllers.InGameController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import newView.GraphicalElements.ScaleTool;
import view.views.InGameView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class CancelButton extends ImageView {
    private Image image = new Image(new FileInputStream("src/newView/resources/buttons/cancelButton.png"));

    private static final double BUTTON_WIDTH = 150;
    private static final double BUTTON_HEIGHT = 60;

    public CancelButton() throws FileNotFoundException {
        setImage(image);
        setOpacity(0.7);

        ScaleTool.resizeImageView(this, BUTTON_WIDTH, BUTTON_HEIGHT);
        ScaleTool.relocate(this, WIDTH - BUTTON_WIDTH, HEIGHT - BUTTON_HEIGHT);

        setMouseEventsFor(this);
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> setOpacity(1));
        node.setOnMouseExited(event -> setOpacity(0.7));
        node.setOnMouseClicked(event -> new InGameController(new InGameView()).refuseGame());
    }
}
