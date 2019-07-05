package ir.pas;

import javafx.application.Application;
import javafx.stage.Stage;
import models.net.Client;
import models.net.requests.LoginRequest;
import newView.SceneMakers.LoginSceneMaker;

public class ClientApp extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientApp.primaryStage = primaryStage;
        Client.getInstance();
        new LoginSceneMaker(primaryStage).set();
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
