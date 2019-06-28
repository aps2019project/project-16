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

public class BackButton extends ImageView {
    private Image image = new Image(new FileInputStream("src/newView/resources/buttons/backButton.png"));

    private static final double BUTTON_WIDTH = 70;
    private static final double BUTTON_HEIGHT = 70;

    public BackButton() throws FileNotFoundException {
        setImage(image);
        setOpacity(0.7);

        ScaleTool.resizeImageView(this, BUTTON_WIDTH, BUTTON_HEIGHT);
        ScaleTool.relocate(this, WIDTH - BUTTON_WIDTH, HEIGHT - BUTTON_HEIGHT * 2.1);

        setMouseEventsFor(this);
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseExited(event -> setOpacity(0.7));
        node.setOnMouseEntered(event -> setOpacity(1));
    }
}
