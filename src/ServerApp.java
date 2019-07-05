import javafx.application.Application;
import javafx.stage.Stage;
import models.net.Server;
import newView.SceneMakers.ServerMenuSceneMaker;

public class ServerApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Server.getInstance();
        new ServerMenuSceneMaker(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
