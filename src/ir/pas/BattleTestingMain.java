package ir.pas;

import javafx.application.Application;
import javafx.stage.Stage;
import newView.SceneMakers.InGameSceneMaker;

public class BattleTestingMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new InGameSceneMaker(primaryStage).set();
        primaryStage.setTitle("DUELYST");
        primaryStage.show();
    }

    public static void main(String[] args) {
//        MenuHandler.startFirstMenu();
//        CommandHandler.scanAndRunCommands();
        launch(args);
    }
}