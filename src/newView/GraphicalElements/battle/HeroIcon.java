package newView.GraphicalElements.battle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HeroIcon extends Pane {
    private Image image = new Image(new FileInputStream("src/newView/resources/battleInfo/general1.png"));
    private ImageView imageView = new ImageView(image);
    public static final double HERO_ICON_LENGTH = 170;
    public static final double HERO_ICON_LENGTH2 = HERO_ICON_LENGTH * 1.1;
    public static final double DELTA_L = HERO_ICON_LENGTH2 - HERO_ICON_LENGTH;


    public HeroIcon() throws FileNotFoundException {
        ScaleTool.resizeImageView(imageView, HERO_ICON_LENGTH, HERO_ICON_LENGTH);
        this.setOnMouseEntered(event -> {
            ScaleTool.resizeImageView(imageView, HERO_ICON_LENGTH2, HERO_ICON_LENGTH2);
            ScaleTool.relocate(imageView, -DELTA_L / 2, -DELTA_L / 2);
        });
        this.setOnMouseExited(event -> {
            ScaleTool.resizeImageView(imageView, HERO_ICON_LENGTH, HERO_ICON_LENGTH);
            ScaleTool.relocate(imageView, 0, 0);
        });
        this.getChildren().add(imageView);
    }
}
