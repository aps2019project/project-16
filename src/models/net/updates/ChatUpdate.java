package models.net.updates;

import models.net.UpdatePacket;
import newView.SceneMakers.GlobalChatSceneMaker;

public class ChatUpdate extends UpdatePacket {
    private String playerName;
    private String message;

    public ChatUpdate(String playerName, String message) {
        this.playerName = playerName;
        this.message = message;
    }

    @Override
    public void update() {
        GlobalChatSceneMaker.getGlobalChatPane().addMassage(playerName ,message);
    }
}
