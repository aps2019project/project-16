package newView.menu;

import com.dd.plist.PropertyListFormatException;
import contracts.AccountContract;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Account;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.SceneMakers.LoginSceneMaker;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static newView.SceneMakers.SceneMaker.SCALE;

public class LoginPage extends Page implements AccountContract.View {
    public LoginPage(Page parent, Stage stage) {
        super(parent, stage);
    }

    @Override
    public void start() {
        Scene scene = null;
        try {
            scene = new LoginSceneMaker().makeScene();
        } catch (IOException | ParserConfigurationException | SAXException | PropertyListFormatException
                | ParseException e) {
            e.printStackTrace();
        }
        getStage().setScene(scene);
    }

    @Override
    public void showLeaderboard(ArrayList<Account> accounts) {

    }
}
