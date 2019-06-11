package newView.GraphicalElements;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static newView.SceneMakers.SceneMaker.*;

public class BackgroundMaker {

    public static void setBackgroundFor(Region region, int backgroundNumber) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("src/newView/resources/bg" + backgroundNumber + ".jpg");

        Image image = new Image(input);

        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(WIDTH * SCALE, HEIGHT * SCALE, false, false, false, false));

        Background background = new Background(backgroundimage);

        region.setBackground(background);
    }
}