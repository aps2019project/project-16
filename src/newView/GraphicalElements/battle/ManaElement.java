package newView.GraphicalElements.battle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.effects.FadeEffect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.battleView.GameGraphicListener.GAME_ACT_TIME;

public class ManaElement extends Pane {
    private Image fullImage = new Image(new FileInputStream("src/newView/resources/battleInfo/full_mana.png"));
    private Image emptyImage = new Image(new FileInputStream("src/newView/resources/battleInfo/empty_mana.png"));
    private ImageView imageView = new ImageView(emptyImage);
    private static final double MANA_LENGTH = 35;
    private static final double FADE_TIME = GAME_ACT_TIME * 0.95;

    public ManaElement() throws FileNotFoundException {
        ScaleTool.resizeImageView(imageView, MANA_LENGTH, MANA_LENGTH);
        this.getChildren().add(imageView);
    }

    public void setFull() {
        new FadeEffect(FADE_TIME, imageView, fullImage);
    }

    public void setEmpty() {
        new FadeEffect(FADE_TIME, imageView, emptyImage);
    }
}
