package newView.battleView.gameActs;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.net.Client;
import models.net.requests.battleRequests.EndTurnRequest;
import newView.AnimationMaker;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.battle.TilesPane;
import newView.SoundPlayer;
import newView.battleView.GameGraphicData;

import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_COLUMNS;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_ROWS;
import static newView.battleView.GameGraphicListener.GAME_ACT_TIME;

public class TurnChangeAct extends GameAct {
    @Override
    public void showAction() {
        makeAnimation();
    }

    private void makeAnimation() {
        Text text = new Text("NEXT TURN!!");
        ScaleTool.relocate(text, NUMBER_OF_COLUMNS * TILE_LENGTH / 2, NUMBER_OF_ROWS * TILE_LENGTH / 2);
        text.setScaleX(4);
        text.setScaleY(4);

        SoundPlayer.playByPath("src/newView/resources/sounds/next.wav");

        TilesPane tilesPane = GameGraphicData.getTilesPane();
        tilesPane.getChildren().add(text);

        KeyValue keyValue = new KeyValue(text.xProperty(), 8);
        KeyValue keyValue1 = new KeyValue(text.yProperty(), 8);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.9)
                , true, 2
                , keyValue, keyValue1);
        timeline.play();

        timeline.setOnFinished(event -> tilesPane.getChildren().remove(text));

        GameGraphicData.incrementTurnID();
        GameGraphicData.getTurnButton().changeState();
        GameGraphicData.setBar(null);

        setTurnTimeBar();
    }

    public static void setTurnTimeBar() {
        if (GameGraphicData.isMyTurn() && !GameGraphicData.isSpectator()) {
            int myTurnID = GameGraphicData.getTurnID();

            ProgressBar bar = new ProgressBar(0);
            bar.setPrefSize(TILE_LENGTH * NUMBER_OF_COLUMNS, 15);
            ScaleTool.relocate(bar, 0, TILE_LENGTH * NUMBER_OF_ROWS + 30);

            double timeToWait = 40;

            Timeline task = new Timeline(
                    new KeyFrame(
                            Duration.seconds(timeToWait * 3 / 4),
                            new KeyValue(bar.progressProperty(), 1)
                    )
            );

            Timeline timeLimiter = AnimationMaker.makeTimeline(Duration.seconds(timeToWait / 4)
                    , false, 1);
            timeLimiter.play();

            timeLimiter.setOnFinished(event -> {
                if (myTurnID == GameGraphicData.getTurnID()) {
                    GameGraphicData.setBar(bar);
                    task.play();

                    task.setOnFinished(event1 -> {
                        if (myTurnID == GameGraphicData.getTurnID()) {
                            Client.getInstance().sendPacket(new EndTurnRequest());
                        }
                    });
                }
            });
        }
    }
}
