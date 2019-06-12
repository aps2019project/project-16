package newView;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;
import javafx.animation.Animation;
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
    private File fileImage;
    private File filePlist;
    private Action action;
    private SpriteAnimation spriteAnimation;
    private ImageView imageView;

    public AnimationMaker(String name, Action action) throws FileNotFoundException {
        this.name = name;
        this.action = action;
        setImageView();
        setFileImage();
        setFilePlist();
    }

    public void setFileImage() {
        String fileName = name + ".png";
        this.fileImage = new File(fileName);
    }

    public void setFilePlist() {
        String fileName = name + ".plist";
        this.filePlist = new File(fileName);
    }

    public void setImageView() throws FileNotFoundException {
        String fileName = name + ".png";
        Image image = new Image(new FileInputStream(fileName));
        this.imageView = new ImageView(image);
    }

    public ImageView getAnimation() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(filePlist);

        NSDictionary parameters = ((NSDictionary) rootDict.objectForKey("frames"));
        ArrayList<PictureCoordination> coordinates = new ArrayList<>();
        parameters.getHashMap().forEach((key, nsObject) -> {
            if (key.contains(action.getName())) {

                NSString nsString = (NSString) ((NSDictionary) nsObject).objectForKey("frame");
                Pattern pattern = Pattern.compile("(\\d+)");
                Matcher matcher = pattern.matcher(nsString.toString());
                ArrayList<Integer> tmp = new ArrayList<>();

                while (matcher.find()) {
                    tmp.add(Integer.parseInt(matcher.group(1)));
                }
                coordinates.add(new PictureCoordination(tmp.get(0), tmp.get(1), tmp.get(2), tmp.get(3)));

            }
        });
        SpriteAnimation sGpriteAnimation = new SpriteAnimation(imageView, Duration.millis(70*coordinates.size()), coordinates);
        sGpriteAnimation.setCycleCount(Animation.INDEFINITE);
        sGpriteAnimation.play();
        return imageView;
    }

}
