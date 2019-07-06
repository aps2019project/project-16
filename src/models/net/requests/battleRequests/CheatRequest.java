package models.net.requests.battleRequests;

import controllers.InGameController;

public class CheatRequest extends BattleRequest {
    private String cheatCode;

    public CheatRequest(String cheatCode) {
        this.cheatCode = cheatCode;
    }

    @Override
    public void run() {
        InGameController controller = new InGameController();
        try {
            controller.cheat(cheatCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
