import javafx.application.Application;
import javafx.stage.Stage;
import models.net.Client;
import models.net.requests.LoginRequest;
import newView.SceneMakers.LoginSceneMaker;

public class ClientApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Client.getInstance();
        new LoginSceneMaker(primaryStage).set();
        primaryStage.show();
    }
}
