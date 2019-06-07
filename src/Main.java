import javafx.application.Application;
import javafx.stage.Stage;
import newView.SceneMakers.AccountSceneMaker;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new AccountSceneMaker().makeScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
//        MenuHandler.startFirstMenu();
//        CommandHandler.scanAndRunCommands();
        launch(args);
    }
}
