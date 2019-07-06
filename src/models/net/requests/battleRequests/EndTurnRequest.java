package models.net.requests.battleRequests;

import controllers.InGameController;

public class EndTurnRequest extends BattleRequest {
    @Override
    public void run() {
        InGameController controller = new InGameController();
        try {
            controller.endTurn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
