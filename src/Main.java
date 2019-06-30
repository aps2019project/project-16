import com.dd.plist.PropertyListFormatException;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import newView.AnimationMaker;
import newView.SceneMakers.*;
import newView.Type;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setScene(new AccountSceneMaker().makeScene());
        //primaryStage.setScene(new LoginSceneMaker().makeScene());
//        new LoginPage(null, primaryStage).start();
//        new LoadingSceneMaker(primaryStage).set();
//        new ShopSceneMaker(primaryStage).set();
//        new CollectionSceneMaker(primaryStage).set();
        new LoginSceneMaker(primaryStage).set();
//        new BattleSceneMaker(primaryStage).set();
//        new GameModeSelectorSceneMaker(primaryStage).set();
//        new CustomCardSceneMaker(primaryStage).set();
        primaryStage.setTitle("DUELYST");
//        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image(new FileInputStream("C:\\Users\\sepehr.p\\Desktop\\project phase1\\src\\newView\\resources\\appIcon\\icon.png")));
//        File path = new File("src/newView/resources/sounds/02 - Tahamol Kon.mp3");
//        Media sound = new Media(path.toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
        primaryStage.show();
    }

    public static void main(String[] args) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
//        MenuHandler.startFirstMenu();
//        CommandHandler.scanAndRunCommands();
//        AnimationMaker.getAttackAnimation("ali" , "hero");
        launch(args);
    }
}
