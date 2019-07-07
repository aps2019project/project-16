package newView.GraphicalElements.battle;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import models.card.Card;
import newView.AnimationMaker;
import newView.GraphicalElements.ScaleTool;
import newView.SoundPlayer;
import newView.Type;
import newView.battleView.GameGraphicData;
import newView.battleView.SelectType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.battleView.GameGraphicListener.GAME_ACT_TIME;

public class HandElement extends Pane {
    private Image normalImage = new Image(new FileInputStream("src/newView/resources/hand/normal.png"));
    private Image hoverImage = new Image(new FileInputStream("src/newView/resources/hand/hover.png"));
    private Image selectedImage = new Image(new FileInputStream("src/newView/resources/hand/selected.png"));
    private boolean isSelected = false;
    private ImageView bgView = new ImageView(normalImage);
    private ImageView imageView;
    private Card card;
    private String cardName;
    private Type type;
    public static final double HAND_LENGTH = 130;

    public HandElement() throws FileNotFoundException {
        ScaleTool.resizeImageView(bgView, HAND_LENGTH, HAND_LENGTH);
        this.getChildren().add(bgView);
        setMouseEventsFor(bgView);
    }

    public void setImageView(ImageView imageView, Card card, String cardName, Type type) {
        this.card = card;
        this.cardName = cardName;
        this.type = type;
        if (this.imageView != null) {
            animateAndDelete();
        }
        this.imageView = imageView;
        if (imageView != null) {
            this.getChildren().add(imageView);
            ScaleTool.resizeImageView(imageView, HAND_LENGTH, HAND_LENGTH);
            animateForAdding();
            setMouseEventsFor(imageView);
        }
    }

    public Card getCard() {
        return card;
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (imageView != null && card != null) {
                GameGraphicData.getCardInfo().setCardView(cardName, type, card);
            }
            if (!isSelected) {
                bgView.setImage(hoverImage);
            }
        });
        node.setOnMouseExited(event -> {
            if (imageView != null) {
                GameGraphicData.getCardInfo().setNull();
            }
            if (!isSelected) {
                bgView.setImage(normalImage);
            }
        });
        node.setOnMouseClicked(event -> {
            if (isSelected) {
                GameGraphicData.unSelectAll();
            } else if (!GameGraphicData.isSomethingSelected()) {
                if (this.card == null) {
                    return;
                }
                isSelected = true;
                if (type != Type.SPELL) {
                    SoundPlayer.playCardNameSound(this.card.getName(), type);
                }
                GameGraphicData.setSelectedHandElement(SelectType.HAND, this);
                bgView.setImage(selectedImage);
            }
        });
    }

    private void animateAndDelete() {
        KeyValue xValue = new KeyValue(imageView.scaleXProperty(), imageView.getScaleX() * 0.01);
        KeyValue yValue = new KeyValue(imageView.scaleYProperty(), imageView.getScaleY() * 0.01);
        KeyValue rotateValue = new KeyValue(imageView.rotateProperty(), imageView.getRotate() + 360);
        Timeline timeline = AnimationMaker.makeTimeline(
                Duration.millis(GAME_ACT_TIME * 0.8)
                , false, 1
                , xValue, yValue, rotateValue);
        timeline.play();
        HandElement handElement = this;
        timeline.setOnFinished(event -> handElement.getChildren().remove(imageView));
    }

    private void animateForAdding() {
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

    public String getCardName() {
        return cardName;
    }

    public Type getType() {
        return type;
    }

    public void unSelect() {
        isSelected = false;
        bgView.setImage(normalImage);
    }
}