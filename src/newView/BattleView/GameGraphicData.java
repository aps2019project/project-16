package newView.BattleView;

import controllers.InGameController;
import models.item.Item;
import newView.BattleView.gameActs.GameAct;
import newView.GraphicalElements.battle.*;
import view.views.InGameView;

import java.util.ArrayList;

public class GameGraphicData {
    private static boolean onLeft;
    private static HandHBox handBox;
    private static EndTurnButton turnButton;
    private static TilesPane tilesPane;
    private static PlayerInfoPane[] infoPanes;
    private static CollectiblesHBox collectiblesHBox;
    private static GraveyardPane graveyardPane;

    private static SelectType selectType = null;

    private static Tile selectedTile;
    private static HandElement selectedHandElement;
    private static CollectibleElement selectedCollectibleElement;

    private final static GameGraphicListener listener = new GameGraphicListener();
    private final static InGameController controller = new InGameController(new InGameView());

    static {
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

    public static void setDatas(HandHBox handHBox, EndTurnButton endTurnButton
            , TilesPane gameTiles, PlayerInfoPane[] playerInfoPanes
            , CollectiblesHBox collectiblesBox, GraveyardPane graveyard) {
        handBox = handHBox;
        turnButton = endTurnButton;
        tilesPane = gameTiles;
        infoPanes = playerInfoPanes;
        collectiblesHBox = collectiblesBox;
        graveyardPane = graveyard;
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
