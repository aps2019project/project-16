package newView.GraphicalElements.battle;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.battleView.GameGraphicData;
import newView.battleView.SelectType;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpecialPower extends Pane {
    private final Image image = new Image(new FileInputStream("src/newView/resources/battleInfo/temp.png"));
    private ImageView imageView = new ImageView(image);
    private boolean isSelected = false;

    private boolean isOnLeft;

    public static final double SPECIAL_POWER_LENGTH = 100;

    public SpecialPower(boolean isOnLeft) throws FileNotFoundException {
        this.isOnLeft = isOnLeft;
        ScaleTool.resizeImageView(imageView, SPECIAL_POWER_LENGTH, SPECIAL_POWER_LENGTH);
        this.getChildren().add(imageView);

        setMouseEventsFor(imageView);
    }

    public void setImageView(ImageView imageView) {
        this.getChildren().remove(this.imageView);

        this.imageView = imageView;
        ScaleTool.resizeImageView(imageView, SPECIAL_POWER_LENGTH, SPECIAL_POWER_LENGTH);
        this.getChildren().add(imageView);

        if (isOnLeft == GameGraphicData.isOnLeft()) {
            setMouseEventsFor(imageView);
        }
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (!isSelected && isOnLeft == GameGraphicData.isOnLeft()) {
                imageView.setScaleX(1.1);
                imageView.setScaleY(1.1);
            }
        });
        node.setOnMouseExited(event -> {
            if (!isSelected && isOnLeft == GameGraphicData.isOnLeft()) {
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            }
        });
        node.setOnMouseClicked(event -> {
            if (isOnLeft == GameGraphicData.isOnLeft()) {
                if (isSelected) {
                    GameGraphicData.unSelectAll();
                } else if (!GameGraphicData.isSomethingSelected()) {
                    isSelected = true;
                    GameGraphicData.setSelectType(SelectType.SPECIAL_POWER);
                    imageView.setScaleX(1.2);
                    imageView.setScaleY(1.2);
                }
            }
        });
    }

    public void unSelect() {
        isSelected = false;
        imageView.setScaleX(1);
        imageView.setScaleY(1);
    }
}