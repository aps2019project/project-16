package newView.GraphicalElements.battle;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.effects.FadeEffect;
import newView.battleView.GameGraphicData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class EndTurnButton extends ImageView {
    private Image normalImage = new Image(new FileInputStream("src/newView/resources/buttons/endTurn/normal.png"));
    private Image hoverImage = new Image(new FileInputStream("src/newView/resources/buttons/endTurn/hover.png"));
    private Image disabledImage = new Image(new FileInputStream("src/newView/resources/buttons/endTurn/disabled.png"));
    private boolean isEnable;
    private static final double BUTTON_WIDTH = 250;
    private static final double BUTTON_HEIGHT = 100;

    public EndTurnButton() throws FileNotFoundException {
        isEnable = GameGraphicData.isOnLeft();
        if (isEnable) {
            setImage(normalImage);
        } else {
            setImage(disabledImage);
        }

        ScaleTool.resizeImageView(this, BUTTON_WIDTH, BUTTON_HEIGHT);
        ScaleTool.relocate(this, WIDTH * 0.7, HEIGHT * 0.8);

        setMouseEventsFor(this);
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (isEnable) {
                setImage(hoverImage);
            }
        });
        node.setOnMouseExited(event -> {
            if (isEnable) {
                setImage(normalImage);
            }
        });
        node.setOnMouseClicked(event -> {
            if (isEnable) {
                GameGraphicData.sendChangeTurnRequest();
            }
        });
    }

    public void setEnable() {
        if (!isEnable) {
            isEnable = true;
            new FadeEffect(1000, this, normalImage);
        }
    }

    public void setDisable() {
        if (isEnable) {
            isEnable = false;
            new FadeEffect(1000, this, disabledImage);
        }
    }

    public void changeState() {
        if (isEnable) {
            setDisable();
        } else {
            setEnable();
        }
    }

    public boolean isEnable() {
        return isEnable;
    }
}
