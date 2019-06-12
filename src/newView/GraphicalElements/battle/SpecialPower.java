package newView.GraphicalElements.battle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpecialPower extends Pane {
    private Image image = new Image(new FileInputStream("src/newView/resources/battleInfo/temp.png"));
    private ImageView imageView = new ImageView(image);
    public static final double SPECIAL_POWER_LENGTH = 100;

    public SpecialPower() throws FileNotFoundException {
        ScaleTool.resizeImageView(imageView, SPECIAL_POWER_LENGTH, SPECIAL_POWER_LENGTH);
        this.getChildren().add(imageView);
    }
}