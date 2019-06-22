import javafx.application.Application;
import javafx.stage.Stage;
import newView.SceneMakers.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setScene(new AccountSceneMaker().makeScene());
        //primaryStage.setScene(new LoginSceneMaker().makeScene());
//        new LoginPage(null, primaryStage).start();
//        new LoadingSceneMaker(primaryStage).set();
//        new ShopSceneMaker(primaryStage).set();
//        new CollectionSceneMaker(primaryStage).set();
//        new LoginSceneMaker(primaryStage).set();
        new BattleSceneMaker(primaryStage).set();
        primaryStage.setTitle("DUELYST");
//        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image(new FileInputStream("C:\\Users\\sepehr.p\\Desktop\\project phase1\\src\\newView\\resources\\appIcon\\icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        MenuHandler.startFirstMenu();
//        CommandHandler.scanAndRunCommands();
        launch(args);
    }
}
