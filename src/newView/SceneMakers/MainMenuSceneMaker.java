package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.net.Client;
import models.net.requests.SaveRequest;
import models.net.requests.watchRequests.GetLiveListRequest;
import models.net.requests.watchRequests.GetReplayListRequest;
import models.net.requests.LogoutRequest;
import newView.GraphicalElements.*;
import newView.SoundPlayer;

import java.io.File;
import java.io.IOException;

public class MainMenuSceneMaker extends SceneMaker {
    private final static String SOUND_BG_PATH = "src/newView/resources/sounds/mainMenu/mainMenubg.mp3";
    private double x = 0, y = 0;
    private static AudioClip mainMenuBgSound = new AudioClip(new File(SOUND_BG_PATH).toURI().toString());


    public MainMenuSceneMaker(Stage primaryStage) {
        super(primaryStage);
        if (!mainMenuBgSound.isPlaying())
            mainMenuBgSound.play();
    }


    @Override
    public Scene makeScene() throws IOException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 1, "account");

        ImageView lionView = ForegroundMaker.getForeground(2, 600, 600, "account");
        ScaleTool.relocate(lionView, -100, 100);

        Fog fog = new Fog(800, 150);
        ScaleTool.relocate(fog.getView(), -100, 550);

        ImageView warriorManView = ForegroundMaker.getForeground(1, 800, 250, "account");
        ScaleTool.relocate(warriorManView, 600, 480);

        borderPane.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if (e.getX() < x) {
                warriorManView.setX(warriorManView.getX() + 0.1);
                lionView.setX(lionView.getX() + 0.1);
            } else if (e.getX() > x) {
                warriorManView.setX(warriorManView.getX() - 0.1);
                lionView.setX(lionView.getX() - 0.1);
            }
            if (e.getY() < y) {
                warriorManView.setY(warriorManView.getY() + 0.1);
                lionView.setY(lionView.getY() + 0.1);
            } else if (e.getY() > y) {
                warriorManView.setY(warriorManView.getY() - 0.1);
                lionView.setY(lionView.getY() - 0.1);
            }
            x = e.getX();
            y = e.getY();
        });

        SoundPlayer.playByPath("src/newView/resources/sounds/welcome.wav");


        VBox commandsBox = new VBox();
        Pane commandsPane = new Pane(commandsBox);

        Text battle = new Text("Battle");
        battle.setOnMouseClicked(event -> {
            new BattleSceneMaker(getPrimaryStage()).set();
            mainMenuBgSound.stop();
        });

        Text shop = new Text("Shop");
        shop.setOnMouseClicked(event -> new ShopSceneMaker(getPrimaryStage()).set());

        Text collection = new Text("Collection");
        collection.setOnMouseClicked(event -> new CollectionSceneMaker(getPrimaryStage()).set());

//        Text customCard = new Text("Custom Card");
//        customCard.setOnMouseClicked(event -> new CustomCardSceneMaker(getPrimaryStage()).set());

        Text globalChat = new Text("Global Chat");
        globalChat.setOnMouseClicked(event -> new GlobalChatSceneMaker(getPrimaryStage()).set());

        Text scoreBoard = new Text("Score Board");
        scoreBoard.setOnMouseClicked(event -> new ScoreBoardSceneMaker(getPrimaryStage()).set());

        Text replayMatches = new Text("Replay Matches");
        replayMatches.setOnMouseClicked(event -> {
            Client.getInstance().sendPacket(new GetLiveListRequest());
            Client.getInstance().sendPacket(new GetReplayListRequest());
            new ReplayMatchesSceneMaker(getPrimaryStage()).set();
        });

        Text save = new Text("Save");
        save.setOnMouseClicked(event -> Client.getInstance().sendPacket(new SaveRequest()));

        Text logout = new Text("Logout");
        logout.setOnMouseClicked(event -> {
            Client.getInstance().sendPacket(new LogoutRequest());
            new LoginSceneMaker(getPrimaryStage()).set();
        });

        Text exit = new Text("Exit");
        exit.setOnMouseClicked(event -> System.exit(0));

        commandsBox.getChildren().addAll(battle, shop, collection, globalChat, replayMatches
                , scoreBoard, save, logout, exit);
        commandsBox.getChildren().forEach(node -> {
            if (node instanceof Text) {
                setTextStyle((Text) node);
                setGlowOnMouseOver((Text) node);
            }
        });
        ScaleTool.relocate(commandsPane, 180, 160);

        borderPane.getChildren().addAll(lionView, fog.getView(), warriorManView);
        borderPane.getChildren().addAll(commandsPane);
        return new MyScene(borderPane);
    }

    private void setTextStyle(Text text) {
        text.setFont(new Font(ScaleTool.scale(35)));
        text.setFill(Color.WHITE);
    }

}
