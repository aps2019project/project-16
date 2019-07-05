package models.net.requests.battleRequests;

import models.net.RequestPacket;

public class CheatRequest extends RequestPacket {
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
