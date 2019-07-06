package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import newView.battleView.GameGraphicData;
import newView.battleView.GraphicalGameViewer;
import newView.battleView.gameActs.*;
import newView.GraphicalElements.*;
import newView.GraphicalElements.battle.*;
import newView.GraphicalElements.effects.SnowPane;

import java.util.Random;

public class InGameSceneMaker extends SceneMaker {
    private static GraphicalGameViewer gameViewer = new GraphicalGameViewer();
    private static Random random = new Random();

    public InGameSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        ZoomablePane zoomablePane = new ZoomablePane();
        BackgroundMaker.setBackgroundFor(zoomablePane, 10, "battle");

        ImageView mapBGView = ForegroundMaker.getForeground(10, 1400, 900, "battle");
        ScaleTool.relocate(mapBGView, WIDTH / 2 - 1400 / 2, HEIGHT / 2 - 900 / 2);

        SnowPane snowPane = new SnowPane();

        HandHBox handHBox = new HandHBox();

        PlayerInfoPane[] infoPanes = new PlayerInfoPane[2];
        infoPanes[0] = new PlayerInfoPane(true);
        infoPanes[1] = new PlayerInfoPane(false);

        GraveyardPane graveyardPane = new GraveyardPane();

        CardInfo cardInfo = new CardInfo();
//        CancelButton cancelButton = new CancelButton();
        BackButton backButton = new BackButton();
        backButton.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());

        FastForwardPane fastForwardPane = new FastForwardPane();
        CheatPane cheatPane = new CheatPane();

        CollectiblesHBox collectiblesHBox = new CollectiblesHBox();

        TilesPane tilesPane = new TilesPane();

        EndTurnButton endTurnButton = new EndTurnButton(true);

        GameGraphicData.setDatas(zoomablePane, handHBox, endTurnButton, tilesPane, infoPanes
                , collectiblesHBox, graveyardPane, cardInfo);

        zoomablePane.getChildren().addAll(mapBGView, snowPane, infoPanes[0], infoPanes[1]
                , fastForwardPane, collectiblesHBox);
        zoomablePane.getChildren().addAll(graveyardPane, cheatPane, cardInfo, backButton);
        zoomablePane.getChildren().addAll(handHBox, tilesPane, endTurnButton);
        zoomablePane.setCursor(SceneMaker.GAME_CURSOR);
        return new MyScene(zoomablePane);
    }

    private void testForActions(EndTurnButton button) {
        button.setOnMouseClicked(event -> {
//            makeMoveAct();
//            makeAttackAct();
//            makeManaAct();
//            makeAddToHandAct();
//            makeAddToHandAct();
//            makePutUnit();
//            makeSpellAct();
//            makeSpecialPAct();
//            makeCollectibleAct();
//            makeFlagAct();
//            makeUsableAct();
            makeAddCollectibleAct();
        });
    }

    private void makeMoveAct() {
        int x1 = random.nextInt(5), y1 = random.nextInt(9);
        int x2 = random.nextInt(5), y2 = random.nextInt(9);
//        GameGraphicData.addGameAct(new MoveAct(x1, y1, x2, y2));
        System.out.println("move from " + x1 + "," + y1 + " to " + x2 + "," + y2);
    }


    private void makeAttackAct() {
        int x1 = 0, y1 = 1, x2 = 1, y2 = 2;
//        GameGraphicData.addGameAct(new AttackAct(x1, y1, x2, y2));
        System.out.println("attack from " + x1 + "," + y1 + " to " + x2 + "," + y2);
    }

    private void makeManaAct() {
        int playerNumber = random.nextInt(2);
        int number = random.nextInt(8);
//        GameGraphicData.addGameAct(new ManaAct(true, number));
        System.out.println("set mana to " + number + " for player " + playerNumber);
    }

    private void makeAddToHandAct() {
//        GameGraphicData.addGameAct(new AddToHandAct(true));
        System.out.println("add to hand");
    }

    private void makePutUnit() {
        int x = random.nextInt(5), y = random.nextInt(9);
//        GameGraphicData.addGameAct(new PutUnitAct(x, y, true));
        System.out.println("put on " + x + "," + y);
    }

    private void makeSpecialPAct() {
        int x1 = random.nextInt(5), y1 = random.nextInt(9);
//        GameGraphicData.addGameAct(new SpecialPowerAct(x1, y1));
        System.out.println("special power on " + x1 + "," + y1);
    }

    private void makeSpellAct() {
        int x1 = random.nextInt(5), y1 = random.nextInt(9);
//        GameGraphicData.addGameAct(new SpellCastAct(x1, y1, true));
        System.out.println("spell on " + x1 + "," + y1);
    }

    private void makeCollectibleAct() {
        int x1 = random.nextInt(5), y1 = random.nextInt(9);
//        GameGraphicData.addGameAct(new UseCollectibleAct(x1, y1));
        System.out.println("collectible on " + x1 + "," + y1);
    }

    private void makeFlagAct() {
        int x1 = random.nextInt(5), y1 = random.nextInt(9);
        GameGraphicData.addGameAct(new AddFlagAct(x1, y1));
        GameGraphicData.addGameAct(new PickFlagAct(x1, y1));
        System.out.println("flag on " + x1 + "," + y1);
    }

    private void makeUsableAct() {
        GameGraphicData.addGameAct(new UsableItemAct("first", "ghosle tamid"));
        System.out.println("usable item");
    }

    private void makeAddCollectibleAct() {
        int x1 = random.nextInt(5), y1 = random.nextInt(9);
        GameGraphicData.addGameAct(new AddCollectibleAct(x1, y1, "Blades of agility"));
//        GameGraphicData.addGameAct(new PickUpCollectibleAct(x1, y1));
        System.out.println("collectible item");
    }
}
