package newView.menu;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import newView.SceneMakers.LoginSceneMaker;
import newView.SceneMakers.ShopSceneMaker;

public class ShopPage extends Page {

    public ShopPage(Page parent, Stage stage) {
        super(parent, stage);
    }

    @Override
    public void start() throws Exception {
        Scene scene;
        scene = new ShopSceneMaker(this).makeScene();
        getStage().setScene(scene);
    }
}
