package newView;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMaker.SCALE;

public class ForegroundMaker {
    public static ImageView getForeground(int number, double width, double height) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/newView/resources/fg" + number + ".png");

        Image image = new Image(input);

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width * SCALE);
        imageView.setFitHeight(height * SCALE);

        imageView.setPreserveRatio(false);

        return imageView;
    }
}
