package newView.GraphicalElements.battle;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.card.Unit;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.BattleView.SelectType;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;

public class Tile extends Pane {
    private static Image flagImage;

    static {
        try {
            flagImage = new Image(new FileInputStream("src/newView/resources/tiles/flag.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ImageView flagView = new ImageView(flagImage);
    private static final double FLAG_SIZE = 40;

    private ImageView itemView;

    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public double getX() {
        return getLayoutX();// TODO: 6/14/19 may change
    }

    public double getY() {
        return getLayoutY();
    }

    private boolean isSelected = false;
    private TilePolygon polygon = new TilePolygon();
    private ImageView imageView;
    private Unit unit;
    public static final double NORMAL_OPACITY = 0.2;
    public static final double HOVER_OPACITY = 0.4;
    public static final double SELECTED_OPACITY = 0.6;
    public static final double TILE_LENGTH = 80;

    public Tile(int row, int column) throws FileNotFoundException {
        this.row = row;
        this.column = column;
        ScaleTool.relocateTile(this);
        ScaleTool.makeTilePoints(polygon, row, column);
        this.getChildren().add(polygon);
        setMouseEventsFor(polygon);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setImageView(ImageView imageView, Unit unit) {
        this.unit = unit;
        if (this.imageView != null) {
            this.getChildren().remove(this.imageView);
        }
        this.imageView = imageView;
        if (imageView != null) {
            ScaleTool.resizeImageView(imageView, TILE_LENGTH, TILE_LENGTH);
            this.getChildren().add(imageView);
            setMouseEventsFor(imageView);
        }
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (!isSelected) {
                polygon.setOpacity(HOVER_OPACITY);
            }
        });
        node.setOnMouseExited(event -> {
            if (!isSelected) {
                polygon.setOpacity(NORMAL_OPACITY);
            }
        });
        node.setOnMouseClicked(event -> {
            if (isSelected) {
                GameGraphicData.unSelectAll();
                return;
            } else if (!GameGraphicData.isSomethingSelected()) {
                if (this.unit == null) {
                    return;
                }
                isSelected = true;
                GameGraphicData.setSelectedTile(SelectType.UNIT, this);
                polygon.setOpacity(SELECTED_OPACITY);
                return;
            }
            switch (GameGraphicData.getSelectType()) {
                case UNIT:
                    if (this.unit == null) {
                        GameGraphicData.sendMoveRequest(this);
                    } else {
                        GameGraphicData.sendAttackRequest(this);
                    }
                    break;
                case HAND:
                    //insert card
                    //then unSelect
                    break;
                case COLLECTIBLE:
                    //cast collectible
                    //then unSelect
                    break;
                case SPECIAL_POWER:
                    //cast special power
                    //then unSelect
                    break;
            }
        });
    }

    public void enableColorAnimation(Color color) {
        KeyValue colorValue = new KeyValue(polygon.fillProperty(), color);
        KeyValue strokeValue = new KeyValue(polygon.strokeWidthProperty(), polygon.getStrokeWidth() * 3);
        Timeline timeline = AnimationMaker.makeTimeline(
                Duration.millis(GAME_ACT_TIME * 0.4)
                , true, 2
                , colorValue, strokeValue);
        timeline.play();
    }

    public void showSpellCast(ImageView spellView) {
        final double SPELL_SIZE = 50;
        final double START_SIZE = TILE_LENGTH / 2 - SPELL_SIZE / 2;
        ScaleTool.resizeImageView(spellView, SPELL_SIZE, SPELL_SIZE);
        ScaleTool.relocate(spellView, START_SIZE, START_SIZE);
        enableColorAnimation(Color.BLUE);
        this.getChildren().add(spellView);

        KeyValue keyValue = new KeyValue(spellView.xProperty(), spellView.getX() + 3);
        KeyValue keyValue1 = new KeyValue(spellView.yProperty(), spellView.getY() + 3);
        KeyValue keyValue2 = new KeyValue(spellView.rotateProperty(), spellView.getRotate() + 5);
        KeyValue keyValue3 = new KeyValue(spellView.scaleXProperty(), spellView.getScaleX() * 1.1);
        KeyValue keyValue4 = new KeyValue(spellView.scaleYProperty(), spellView.getScaleY() * 1.1);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.06), true, 10
                , keyValue, keyValue1, keyValue2, keyValue3, keyValue4);
        timeline.play();
        timeline.setOnFinished(event -> this.getChildren().remove(spellView));
    }

    public void addFlag() {
        flagView.setOpacity(1);
        ScaleTool.relocate(flagView, 20, 20);
        ScaleTool.resizeImageView(flagView, FLAG_SIZE, FLAG_SIZE);
        if (!this.getChildren().contains(flagView)) {
            this.getChildren().add(flagView);
        }
    }

    public void removeFlag() {
        KeyValue keyValue = new KeyValue(flagView.yProperty(), flagView.getY() - 30);
        KeyValue keyValue1 = new KeyValue(flagView.opacityProperty(), 0.1);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.8)
                , false, 1
                , keyValue, keyValue1);

        timeline.play();

        timeline.setOnFinished(event -> this.getChildren().remove(flagView));
    }

    public void addCollectible(ImageView itemView) {
        if (this.itemView != null) {
            this.getChildren().remove(this.itemView);
        }
        ScaleTool.resizeImageView(itemView, 45, 45);
        ScaleTool.relocate(itemView, TILE_LENGTH - 55, TILE_LENGTH - 55);
        this.itemView = itemView;
        this.getChildren().add(itemView);
    }

    public void removeCollectible() {
        KeyValue keyValue = new KeyValue(itemView.yProperty(), flagView.getY() - 50);
        KeyValue keyValue1 = new KeyValue(itemView.opacityProperty(), 0.01);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.81)
                , false, 1
                , keyValue, keyValue1);

        timeline.play();

        timeline.setOnFinished(event -> this.getChildren().remove(itemView));
    }

    public void unSelect() {
        isSelected = false;
        polygon.setOpacity(NORMAL_OPACITY);
    }
}
