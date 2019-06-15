package newView;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimationMaker {
    private String name;
    private File filePlist;
    private Action action;
    private SpriteAnimation spriteAnimation;
    private ImageView imageView;
    private String type;

    public AnimationMaker(String name, String type, Action action) throws FileNotFoundException {
        this.type = type;
        this.name = name;
        this.action = action;
        setImageView();
        setFilePlist();
    }

    public AnimationMaker(String name, String filePath) throws FileNotFoundException {
        this.name = name;
        this.action = Action.NOTHING;
        this.filePlist = new File(filePath + name + ".plist");
        this.imageView = new ImageView(new Image(new FileInputStream(filePath + name + ".png")));
    }

    public void setFilePlist() {
        String fileName = "src/newView/resources/cards/" + type + "/" + name + ".plist";
        this.filePlist = new File(fileName);
    }

    public void setImageView() throws FileNotFoundException {
        String fileName = "src/newView/resources/cards/" + type + "/" + name + ".png";
        Image image = new Image(new FileInputStream(fileName));
        this.imageView = new ImageView(image);
    }

    public ImageView getAnimation(int cycle) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(filePlist);

        NSDictionary parameters = ((NSDictionary) rootDict.objectForKey("frames"));
        ArrayList<PictureCoordination> coordinates = new ArrayList<>();
        parameters.getHashMap().forEach((key, nsObject) -> {
            if (action == Action.NOTHING) {
                if (!key.contains("active")) {
                    getCoordinatesForSprite(coordinates, (NSDictionary) nsObject);
                }
            } else {
                if (key.contains(action.getName())) {
                    getCoordinatesForSprite(coordinates, (NSDictionary) nsObject);
                }
            }
        });

        SpriteAnimation sGpriteAnimation = new SpriteAnimation(imageView, Duration.millis(70 * coordinates.size()), coordinates);
        sGpriteAnimation.setCycleCount(cycle);
        sGpriteAnimation.play();
        return imageView;
    }

    private void getCoordinatesForSprite(ArrayList<PictureCoordination> coordinates, NSDictionary nsObject) {
        NSString nsString = (NSString) nsObject.objectForKey("frame");
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(nsString.toString());
        ArrayList<Integer> tmp = new ArrayList<>();

        while (matcher.find()) {
            tmp.add(Integer.parseInt(matcher.group(1)));
        }
        coordinates.add(new PictureCoordination(tmp.get(0), tmp.get(1), tmp.get(2), tmp.get(3)));
    }


    public static ImageView getAttackAnimation(String name, String type) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, type, Action.ATTACK).getAnimation(1);
    }

    public static ImageView getBreathingAnimation(String name, String type) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, type, Action.BREATHING).getAnimation(Animation.INDEFINITE);
    }

    public static ImageView getDeathAnimation(String name, String type) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, type, Action.DEATH).getAnimation(2);
    }

    public static ImageView getRunningAnimation(String name, String type) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, type, Action.RUN).getAnimation(4);
    }

    public static ImageView getHitAnimation(String name, String type) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, type, Action.HIT).getAnimation(2);
    }

    public static ImageView getNothingAnimation(String name, String type) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, type, Action.NOTHING).getAnimation(Animation.INDEFINITE);
    }

    public static ImageView getActiveAnimation(String name, String type) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, type, Action.ACTIVE).getAnimation(2);
    }

    public static ImageView getSimpleAnimation(String name, String filePath) throws IOException, PropertyListFormatException, ParserConfigurationException, SAXException, ParseException {
        return new AnimationMaker(name, filePath).getAnimation(Animation.INDEFINITE);
    }

    public static Timeline makeTimeline(Duration duration, boolean autoReverse, int cycleCount, KeyValue... keyValues) {
        KeyFrame keyFrame = new KeyFrame(duration, keyValues);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setAutoReverse(autoReverse);
        timeline.setCycleCount(cycleCount);
        return timeline;
    }
}
