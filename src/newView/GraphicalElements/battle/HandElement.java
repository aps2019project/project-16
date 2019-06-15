package newView.GraphicalElements.battle;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.GraphicalElements.ScaleTool;
import newView.Type;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;

public class HandElement extends Pane {
    private Image normalImage = new Image(new FileInputStream("src/newView/resources/hand/normal.png"));
    private Image hoverImage = new Image(new FileInputStream("src/newView/resources/hand/hover.png"));
    private Image selectedImage = new Image(new FileInputStream("src/newView/resources/hand/selected.png"));
    private boolean isSelected = false;
    private ImageView bgView = new ImageView(normalImage);
    private ImageView imageView;
    private String cardName;
    private Type type;
    public static final double HAND_LENGTH = 130;

    public HandElement() throws FileNotFoundException {
        ScaleTool.resizeImageView(bgView, HAND_LENGTH, HAND_LENGTH);
        this.getChildren().add(bgView);
        setMouseEventsFor(bgView);
    }

    public void setImageView(ImageView imageView, String cardName, Type type) {
        this.cardName = cardName;
        this.type = type;
        if (this.imageView != null) {
            this.getChildren().remove(this.imageView);
        }
        this.imageView = imageView;
        if (imageView != null) {
            this.getChildren().add(imageView);
            ScaleTool.resizeImageView(imageView, HAND_LENGTH, HAND_LENGTH);
            animatePut();
            setMouseEventsFor(imageView);
        }
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (!isSelected) {
                bgView.setImage(hoverImage);
            }
        });
        node.setOnMouseExited(event -> {
            if (!isSelected) {
                bgView.setImage(normalImage);
            }
        });
        node.setOnMouseClicked(event -> {
            isSelected = !isSelected;
            if (isSelected) {
                bgView.setImage(selectedImage);
            } else {
                bgView.setImage(hoverImage);
            }
        });
    }

    private void animatePut() {
        KeyValue xKeyValue = new KeyValue(imageView.scaleXProperty(), imageView.getScaleX() * 2);
        KeyValue yKeyValue = new KeyValue(imageView.scaleYProperty(), imageView.getScaleY() * 2);
        Timeline timeline = AnimationMaker.makeTimeline(
                Duration.millis(GAME_ACT_TIME * 0.4)
                , true, 2
                , xKeyValue, yKeyValue);
        timeline.play();
    }

    public ImageView getImageView() {
        return imageView;
    }
}