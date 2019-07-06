package models.net.requests.gameRequests;

import controllers.CustomGameController;
import models.net.RequestPacket;

public class CustomGameRequest extends RequestPacket {
    private String oppDeckName;
    private int mode;
    private int flags;

    public CustomGameRequest(String oppDeckName, int mode, int flags) {
        this.oppDeckName = oppDeckName;
        this.mode = mode;
        this.flags = flags;
    }

    @Override
    public void run() {
        CustomGameController controller = new CustomGameController();
        try {
            controller.startGame(oppDeckName, mode, flags);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
