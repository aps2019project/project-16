package models.net.requests.battleRequests;

import controllers.InGameController;

public class AttackRequest extends BattleRequest {
    private String myUnitName;
    private int myGameID;
    private String oppUnitName;
    private int oppGameID;

    public AttackRequest(String myUnitName, int myGameID, String oppUnitName, int oppGameID) {
        this.myUnitName = myUnitName;
        this.myGameID = myGameID;
        this.oppUnitName = oppUnitName;
        this.oppGameID = oppGameID;
    }

    @Override
    public void run() {
        InGameController controller = new InGameController();
        try {
            controller.selectCard(myUnitName, myGameID);
            controller.attack(oppUnitName, oppGameID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
