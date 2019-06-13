package newView.menu;

import com.dd.plist.PropertyListFormatException;
import javafx.stage.Stage;
import newView.SceneMakers.LoadingSceneMaker;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public class LoadingPage extends Page {

    public LoadingPage(Page parent, Stage stage) {
        super(parent, stage);
    }

    @Override
    public void start() throws Exception {
        getStage().setScene(new LoadingSceneMaker(this).makeScene());
    }
}
