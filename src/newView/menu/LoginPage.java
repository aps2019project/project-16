package newView.menu;

import com.dd.plist.PropertyListFormatException;
import contracts.AccountContract;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Account;
import newView.SceneMakers.LoginSceneMaker;

import java.util.ArrayList;


public class LoginPage extends Page implements AccountContract.View {
    public LoginPage(Page parent, Stage stage) {
        super(parent, stage);
    }

    @Override
    public void start() throws Exception {
        Scene scene;
        scene = new LoginSceneMaker(this).makeScene();
        getStage().setScene(scene);
    }

    @Override
    public void showLeaderboard(ArrayList<Account> accounts) {

    }
}
