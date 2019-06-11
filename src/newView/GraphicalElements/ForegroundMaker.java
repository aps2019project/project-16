package newView.GraphicalElements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMakers.SceneMaker.SCALE;

public class ForegroundMaker {
    public static ImageView getForeground(int number, double width, double height) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/newView/resources/fg" + number + ".png");

        Image image = new Image(input);

        ImageView imageView = new ImageView(image);

        ScaleTool.resizeImageView(imageView, width, height);

        return imageView;
    }
}
