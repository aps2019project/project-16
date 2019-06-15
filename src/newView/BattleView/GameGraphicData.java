package newView.BattleView;

import newView.BattleView.gameActs.GameAct;
import newView.GraphicalElements.battle.EndTurnButton;
import newView.GraphicalElements.battle.HandHBox;
import newView.GraphicalElements.battle.PlayerInfoPane;
import newView.GraphicalElements.battle.TilesPane;

public class GameGraphicData {
    // TODO: 6/14/19
    private static HandHBox handBox;
    private static EndTurnButton turnButton;
    private static TilesPane tilesPane;
    private static PlayerInfoPane[] infoPanes;

    private final static GameGraphicListener listener = new GameGraphicListener();

    static {
        listener.start();
    }

    public static void setDatas(HandHBox handHBox, EndTurnButton endTurnButton,
                                TilesPane gameTiles, PlayerInfoPane[] playerInfoPanes) {
        handBox = handHBox;
        turnButton = endTurnButton;
        tilesPane = gameTiles;
        infoPanes = playerInfoPanes;
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
}
