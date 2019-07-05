package newView.battleView;

import controllers.InGameController;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import models.item.Item;
import newView.battleView.gameActs.GameAct;
import newView.GraphicalElements.ZoomablePane;
import newView.GraphicalElements.battle.*;
import newView.SceneMakers.LoadingSceneMaker;
import view.views.InGameView;

import java.util.ArrayList;

public class GameGraphicData {
    private static boolean onLeft;
    private static ZoomablePane zoomablePane;
    private static HandHBox handBox;
    private static EndTurnButton turnButton;
    private static TilesPane tilesPane;
    private static PlayerInfoPane[] infoPanes;
    private static CollectiblesHBox collectiblesHBox;
    private static GraveyardPane graveyardPane;
    private static CardInfo cardInfo;

    private static SelectType selectType = null;

    private static Tile selectedTile;
    private static HandElement selectedHandElement;
    private static CollectibleElement selectedCollectibleElement;

    private static GameGraphicListener listener = new GameGraphicListener();
    private final static InGameController controller = new InGameController(new InGameView());

    static {
        listener.setDaemon(true);
        listener.start();
    }

    public static boolean isSomethingSelected() {
        return selectType != null;
    }

    public static SelectType getSelectType() {
        return selectType;
    }

    public static void setSelectType(SelectType selectType) {
        GameGraphicData.selectType = selectType;
    }

    public static void setSelectedTile(SelectType type, Tile tile) {
        selectedTile = tile;
        selectType = type;
    }

    public static void setSelectedHandElement(SelectType type, HandElement handElement) {
        selectedHandElement = handElement;
        selectType = type;
    }

    public static void setSelectedCollectible(SelectType type, CollectibleElement element) {
        selectedCollectibleElement = element;
        selectType = type;
    }

    public static void setDatas(ZoomablePane mainPane, HandHBox handHBox, EndTurnButton endTurnButton
            , TilesPane gameTiles, PlayerInfoPane[] playerInfoPanes
            , CollectiblesHBox collectiblesBox, GraveyardPane graveyard, CardInfo cardInfo1) {
        zoomablePane = mainPane;
        handBox = handHBox;
        turnButton = endTurnButton;
        tilesPane = gameTiles;
        infoPanes = playerInfoPanes;
        collectiblesHBox = collectiblesBox;
        graveyardPane = graveyard;
        cardInfo = cardInfo1;
    }

    public static void showActionsInFastRate(ArrayList<GameAct> initialGameActs) {
        Pane loadingPane = LoadingSceneMaker.getLoadingPane();
        Platform.runLater(() -> zoomablePane.getChildren().add(loadingPane));
        Thread thread = new Thread(() -> {
            try {
                listener.fastShow(initialGameActs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            GameGraphicListener.setMustShow(false);
            thread.start();
            thread.join();
            GameGraphicListener.setMustShow(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> zoomablePane.getChildren().remove(loadingPane));
    }

    public static GameGraphicListener getListener() {
        return listener;
    }

    public static void addGameAct(GameAct gameAct) {
        listener.addGameAct(gameAct);
    }

    public static TilesPane getTilesPane() {
        return tilesPane;
    }

    public static PlayerInfoPane[] getInfoPanes() {
        return infoPanes;
    }

    public static HandHBox getHandBox() {
        return handBox;
    }

    public static EndTurnButton getTurnButton() {
        return turnButton;
    }

    public static CollectiblesHBox getCollectiblesHBox() {
        return collectiblesHBox;
    }

    public static GraveyardPane getGraveyardPane() {
        return graveyardPane;
    }

    public static CardInfo getCardInfo() {
        return cardInfo;
    }

    public static void setOnLeft(boolean onLeft) {
        GameGraphicData.onLeft = onLeft;
    }

    public static boolean isOnLeft() {
        return onLeft;
    }

    public static void addCollectibles(ArrayList<Item> items) {
        for (Item item : items) {
            collectiblesHBox.addElement(new CollectibleElement(item));
        }
    }

    public static void sendMoveRequest(Tile destinationTile) {
        controller.selectCard(selectedTile.getUnit().getName(), selectedTile.getUnit().getGameCardID());
        controller.moveToCell(destinationTile.getRow(), destinationTile.getColumn());
        unSelectAll();
    }

    public static void sendAttackRequest(Tile opponentTile) {
        controller.selectCard(selectedTile.getUnit().getName(), selectedTile.getUnit().getGameCardID());
        controller.attack(opponentTile.getUnit().getName(), opponentTile.getUnit().getGameCardID());
        unSelectAll();
    }

    public static void sendInsertRequest(Tile insertTile) {
        controller.insertCard(selectedHandElement.getCardName(), insertTile.getRow(), insertTile.getColumn());
        unSelectAll();
    }

    public static void sendCastSpecialPowerRequest(Tile castTile) {
        controller.useSpecialPower(castTile.getRow(), castTile.getColumn());
        unSelectAll();
    }

    public static void sendCastCollectibleRequest(Tile castTile) {
        controller.selectCollectable(selectedCollectibleElement.getItem().getCollectibleID());
        controller.useSelectedCollectable(castTile.getRow(), castTile.getColumn());
        unSelectAll();
    }

    public static void sendChangeTurnRequest() {
        controller.endTurn();
    }

    public static void sendCheatRequest(String cheatCode) {
        controller.cheat(cheatCode);
    }

    public static void unSelectAll() {
        switch (selectType) {
            case UNIT:
                selectedTile.unSelect();
                selectedTile = null;
                break;
            case SPECIAL_POWER:
                for (PlayerInfoPane infoPane : infoPanes) {
                    infoPane.getSpecialPower().unSelect();
                }
                break;
            case COLLECTIBLE:
                selectedCollectibleElement.unSelect();
                selectedCollectibleElement = null;
                break;
            case HAND:
                selectedHandElement.unSelect();
                selectedHandElement = null;
                break;
        }
        selectType = null;
    }

    //hand select
    //unit select (attack + move)
    //collectible select
}
