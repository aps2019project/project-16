package newView.SceneMakers;

import com.dd.plist.PropertyListFormatException;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public abstract class SceneMaker {
    public static final double SCALE = 1;
    public static final double HEIGHT = 700;
    public static final double WIDTH = 1300;
    private static FileInputStream mouseFIS;
    public static final ImageCursor GAME_CURSOR;

    static {
        try {
            mouseFIS = new FileInputStream( "src/newView/resources/mouse.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GAME_CURSOR = new ImageCursor(new Image(mouseFIS));
    }

    public abstract Scene makeScene() throws IOException, ParserConfigurationException, ParseException, SAXException, PropertyListFormatException;
}
