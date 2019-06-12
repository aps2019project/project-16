package newView.GraphicalElements.battle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ManaElement extends Pane {
    private Image fullImage = new Image(new FileInputStream("src/newView/resources/battleInfo/full_mana.png"));
    private Image emptyImage = new Image(new FileInputStream("src/newView/resources/battleInfo/empty_mana.png"));
    private ImageView imageView = new ImageView(emptyImage);
    public static final double MANA_LENGTH = 35;

    public ManaElement() throws FileNotFoundException {
        ScaleTool.resizeImageView(imageView, MANA_LENGTH, MANA_LENGTH);
        this.getChildren().add(imageView);
    }

    public void setFull() {
        imageView.setImage(fullImage);
    }

    public void setEmpty() {
        imageView.setImage(emptyImage);
    }
}
