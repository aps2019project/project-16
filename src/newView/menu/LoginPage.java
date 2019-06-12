package newView.menu;

import javafx.scene.Group;
import javafx.scene.Scene;
import newView.GraphicalElements.MyScene;

public class LoginPage extends Page {

    @Override
    public void start() {
        Group root = new Group();
        Scene scene = new MyScene(root);



        stage.setScene(scene);
    }
}
