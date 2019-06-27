package newView.GraphicalElements.battle;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import models.item.Item;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.BattleView.SelectType;
import newView.GraphicalElements.ScaleTool;
import newView.Type;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;

public class CollectibleElement extends Pane {
    private boolean isSelected = false;
    private ImageView itemView;
    private Item item;

    public static final double COLLECTIBLE_LENGTH = 50;

    public CollectibleElement(Item item) {
        try {
            this.itemView = AnimationMaker.getNothingAnimation(item.getName(), Type.ITEM.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.item = item;

        ScaleTool.resizeImageView(itemView, COLLECTIBLE_LENGTH, COLLECTIBLE_LENGTH);
        this.getChildren().add(itemView);
        setMouseEventsFor(itemView);
    }

    public Item getItem() {
        return item;
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (!isSelected) {
                itemView.setScaleX(1.15);
                itemView.setScaleY(1.15);
            }
        });
        node.setOnMouseExited(event -> {
            if (!isSelected) {
                itemView.setScaleX(1);
                itemView.setScaleY(1);
            }
        });
        node.setOnMouseClicked(event -> {
            if (isSelected) {
                GameGraphicData.unSelectAll();
            } else if (!GameGraphicData.isSomethingSelected()) {
                isSelected = true;
                GameGraphicData.setSelectedCollectible(SelectType.COLLECTIBLE, this);
                itemView.setScaleX(1.3);
                itemView.setScaleY(1.3);
            }
        });
    }

    public void unSelect() {
        isSelected = false;
        itemView.setScaleX(1);
        itemView.setScaleY(1);
    }

    public void goUpAndDelete() {
        KeyValue keyValue = new KeyValue(itemView.yProperty(), itemView.getY() - 300);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.6)
                , false, 1
                , keyValue);
        timeline.play();

        timeline.setOnFinished(event -> this.getChildren().remove(itemView));
    }
}
