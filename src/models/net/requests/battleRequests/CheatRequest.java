package models.net.requests.battleRequests;

public class CheatRequest extends BattleRequest {
    private String cheatCode;

    public CheatRequest(String cheatCode) {
        this.cheatCode = cheatCode;
    }

    @Override
    public void run() {
        // TODO mostafa:
        //  :
        //  controller.cheat(cheatCode);
    }
}
