package models.net.requests.battleRequests;

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
        // TODO mostafa:
        //  :
        //  controller.selectCard(myUnitName, myGameID);
        //  controller.attack(oppUnitName, oppGameID);
    }
}