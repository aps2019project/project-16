package models.net.requests.gameRequests;

import models.net.RequestPacket;

public class LevelGameRequest extends RequestPacket {
    private int levelNumber;

    public LevelGameRequest(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    @Override
    public void run() {
        // TODO mostafa:
    }
}
