package newView.BattleView;

import newView.BattleView.gameActs.GameAct;

public class ClientSender {
    public static void sendToViewer(GameAct gameAct) {
        GameGraphicData.addGameAct(gameAct);
    }
}
