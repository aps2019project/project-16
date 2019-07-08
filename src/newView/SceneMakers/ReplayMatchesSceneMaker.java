package newView.SceneMakers;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.MatchDetail;
import models.net.Client;
import models.net.requests.watchRequests.LiveWatchRequest;
import models.net.requests.watchRequests.ReplayWatchRequest;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ReplayMatchesSceneMaker extends SceneMaker {

    private static ArrayList<MatchDetail> finishedMatchDetails = new ArrayList<>();
    private static ArrayList<MatchDetail> liveMatchDetails = new ArrayList<>();

    private static int archiveCounter;
    private static int onlineCounter;

    private static ImageView liveButton;
    private static ImageView archiveButton;
    private static ImageView back;
    private static boolean showLive = true;
    private static VBox visiblePane;

    public ReplayMatchesSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }


    @Override
    public Scene makeScene() throws Exception {
        Pane root = new Pane();

        setBack();

        liveButton = new ImageView(new Image(new FileInputStream("src/newView/resources/replayMatches/live.png")));
        ScaleTool.resizeImageView(liveButton, 190, 65);
        ScaleTool.relocate(liveButton, 100, 83);
        liveButton.setOnMouseEntered(event -> liveButton.setEffect(new Glow(0.8)));
        liveButton.setOnMouseExited(event -> liveButton.setEffect(new Glow(0)));
        liveButton.setOnMouseClicked(event -> {
            showLive = true;
            showMatches();
        });


        archiveButton = new ImageView(new Image(new FileInputStream("src/newView/resources/replayMatches/archive.png")));
        ScaleTool.resizeImageView(archiveButton, 200, 80);
        ScaleTool.relocate(archiveButton, 100, 83 + 70);
        archiveButton.setOnMouseEntered(event -> archiveButton.setEffect(new Glow(0.8)));
        archiveButton.setOnMouseExited(event -> archiveButton.setEffect(new Glow(0)));
        archiveButton.setOnMouseClicked(event -> {
            showLive = false;
            showMatches();
        });

        BackgroundMaker.setBackgroundFor(root, 1, "replayMatches");
        visiblePane = new VBox();
        visiblePane.setSpacing(20);
        visiblePane.setPadding(new Insets(10, 10, 10, 10));
        visiblePane.setStyle("-fx-background-color: rgb(32 ,32, 32,0.8)");
        ScaleTool.resizeRegion(visiblePane, 420, 600);
//        visiblePane.getChildren().add(getMatchPane();

//        liveMatchDetails.add(new MatchDetail("klfdsng", "asghar", 43));
//        finishedMatchDetails.add(new MatchDetail("mmd kala", "asd", 43));

        ScaleTool.relocate(visiblePane, 500, 50);
        showMatches();

        root.getChildren().addAll(back, liveButton, archiveButton, visiblePane);
        return new MyScene(root);
    }

    private void setBack() throws FileNotFoundException {
        back = new ImageView(new Image(new FileInputStream("src/newView/resources/replayMatches/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());
    }

    private static void showMatches() {
        try {
            if (showLive)
                showLiveMatches();
            else
                showArchiveMatches();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showLiveMatches() throws FileNotFoundException {
        visiblePane.getChildren().removeIf(e -> true);
        for (int i = 0; i < 5; i++) {
            if (i + onlineCounter < liveMatchDetails.size()) {
                MatchDetail matchDetail = liveMatchDetails.get(i + onlineCounter);
                Pane matchPane = getMatchPane(matchDetail, true);
                visiblePane.getChildren().add(matchPane);
            }
        }
    }

    private static void showArchiveMatches() throws FileNotFoundException {
        visiblePane.getChildren().removeIf(e -> true);
        for (int i = 0; i < 5; i++) {
            if (i + archiveCounter < finishedMatchDetails.size()) {
                MatchDetail matchDetail = finishedMatchDetails.get(i + archiveCounter);
                Pane matchPane = getMatchPane(matchDetail, false);
                visiblePane.getChildren().add(matchPane);
            }
        }
    }

    private static Pane getMatchPane(MatchDetail matchDetail, boolean isLive) throws FileNotFoundException {
        Pane pane = new Pane();
        ScaleTool.resizeRegion(pane, 400, 100);
        pane.setStyle("-fx-background-color: rgb(100,100,100 ,0.5)");

        StackPane firstPlayer = new StackPane();
        ScaleTool.resizeRegion(firstPlayer, 100, 50);
        ScaleTool.relocate(firstPlayer, 20, 30);

        StackPane secondPLayer = new StackPane();
        ScaleTool.resizeRegion(secondPLayer, 100, 50);
        ScaleTool.relocate(secondPLayer, 270, 30);

        ImageView versus = new ImageView(new Image(new FileInputStream("src/newView/resources/replayMatches/versus.png")));
        ScaleTool.resizeImageView(versus, 200, 100);
        ScaleTool.relocate(versus, 100, 5);

        ImageView onlineSign = new ImageView(new Image(new FileInputStream("src/newView/resources/replayMatches/onlineSign.png")));
        ScaleTool.resizeImageView(onlineSign, 40, 40);
        ScaleTool.relocate(onlineSign, -5, -5);

        Text fistPlayerText = new Text(matchDetail.getFirstPlayerName());
        fistPlayerText.setFill(Color.WHITE);
        fistPlayerText.setStyle("-fx-font-size: 24");
        firstPlayer.getChildren().add(fistPlayerText);

        Text secondPlayerText = new Text(matchDetail.getSecondPlayerName());
        secondPlayerText.setFill(Color.WHITE);
        secondPlayerText.setStyle("-fx-font-size: 24");
        secondPLayer.getChildren().add(secondPlayerText);

        pane.getChildren().addAll(firstPlayer, secondPLayer, versus);
        if (isLive)
            pane.getChildren().add(onlineSign);
        pane.setOnMouseClicked(event -> {
            int matchId = matchDetail.getMatchID();
            if (isLive)
                Client.getInstance().sendPacket(new LiveWatchRequest(matchId));
            else
                Client.getInstance().sendPacket(new ReplayWatchRequest(matchId));
        });

        return pane;
    }

    public static void updateLiveMatchDetailes(ArrayList<MatchDetail> matchDetails) {
        liveMatchDetails = matchDetails;
        showMatches();
    }

    public static void updateArchivedMatchDetailes(ArrayList<MatchDetail> matchDetails) {
        finishedMatchDetails = matchDetails;
        showMatches();
    }




}
