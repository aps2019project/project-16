package newView.BattleView;

import controllers.InGameController;
import newView.BattleView.gameActs.GameAct;
import newView.GraphicalElements.battle.*;
import view.views.InGameView;

public class GameGraphicData {
    private static boolean onLeft;
    private static HandHBox handBox;
    private static EndTurnButton turnButton;
    private static TilesPane tilesPane;
    private static PlayerInfoPane[] infoPanes;

    private static SelectType selectType = null;

    private static Tile selectedTile;
    private static HandElement selectedHandElement;

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

    public static void setDatas(HandHBox handHBox, EndTurnButton endTurnButton,
                                TilesPane gameTiles, PlayerInfoPane[] playerInfoPanes, boolean isOnLeft) {
        handBox = handHBox;
        turnButton = endTurnButton;
        tilesPane = gameTiles;
        infoPanes = playerInfoPanes;
        onLeft = isOnLeft;
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

    public static boolean isOnLeft() {
        return onLeft;
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
                // TODO: 6/26/19
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
