package newView.menu;

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

import java.util.ArrayList;

import static newView.SceneMakers.SceneMaker.SCALE;

public class LoginPage extends Page implements AccountContract.View {
    public LoginPage(Page parent, Stage stage) {
        super(parent, stage);
    }

    @Override
    public void start() {
        Group root = new Group();
        Scene scene = new MyScene(root);


        getStage().setScene(scene);
    }

    @Override
    public void showLeaderboard(ArrayList<Account> accounts) {

    }
}
