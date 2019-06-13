package newView.menu;

import javafx.stage.Stage;
import models.AccountScore;
import newView.SceneMakers.AccountSceneMaker;

public class MainMenuPage extends Page {
    public MainMenuPage(Page parent, Stage stage) {
        super(parent, stage);
    }

    @Override
    public void start() throws Exception {
        getStage().setScene(new AccountSceneMaker(this).makeScene());
    }
}
