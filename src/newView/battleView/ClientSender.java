package newView.battleView;

import newView.battleView.gameActs.GameAct;

public class ClientSender {
    public static void sendToViewer(GameAct gameAct) {
        GameGraphicData.addGameAct(gameAct);
    }
}
