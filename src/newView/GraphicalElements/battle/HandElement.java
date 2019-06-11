package newView.GraphicalElements.battle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HandElement extends Pane {
    private Image normalImage = new Image(new FileInputStream("src/newView/resources/hand/normal.png"));
    private Image hoverImage = new Image(new FileInputStream("src/newView/resources/hand/hover.png"));
    private Image selectedImage = new Image(new FileInputStream("src/newView/resources/hand/selected.png"));
    private boolean isSelected = false;
    private ImageView bgView = new ImageView(normalImage);
    private ImageView imageView;
    public static final double HAND_LENGTH = 130;

    public HandElement() throws FileNotFoundException {
        ScaleTool.resizeImageView(bgView, HAND_LENGTH, HAND_LENGTH);
        this.getChildren().add(bgView);
        this.setOnMouseEntered(event -> {
            if (!isSelected) {
                bgView.setImage(hoverImage);
            }
        });
        this.setOnMouseExited(event -> {
            if (!isSelected) {
                bgView.setImage(normalImage);
            }
        });
        this.setOnMouseClicked(event -> {
            isSelected = !isSelected;
            if (isSelected) {
                bgView.setImage(selectedImage);
            } else {
                bgView.setImage(hoverImage);
            }
        });
    }

    public void setImageView(ImageView imageView) {
        if (this.imageView == null) {
            this.imageView = imageView;
            ScaleTool.resizeImageView(imageView, HAND_LENGTH, HAND_LENGTH);
            this.getChildren().add(imageView);
        } else {
            this.imageView.setImage(imageView.getImage());
        }
    }
}