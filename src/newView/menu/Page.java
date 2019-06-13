package newView.menu;

import com.dd.plist.PropertyListFormatException;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public abstract class Page {
    private Page parent;
    private Stage stage;

    public Page(Page parent, Stage stage) {
        this.parent = parent;
        this.stage = stage;
    }

    public abstract void start() throws Exception;

    protected void back() throws Exception {
        parent.start();
    }

    public Stage getStage() {
        return stage;
    }
}
