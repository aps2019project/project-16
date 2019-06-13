package newView.SceneMakers;

import com.dd.plist.PropertyListFormatException;
import javafx.scene.Scene;
import newView.menu.Page;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public class LoadingSceneMaker extends SceneMaker {

    public LoadingSceneMaker(Page page) {
        super(page);
    }

    @Override
    public Scene makeScene() throws Exception {
        return null;
    }
}
