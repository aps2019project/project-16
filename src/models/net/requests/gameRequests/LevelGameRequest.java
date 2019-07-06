package models.net.requests.gameRequests;

import controllers.StoryController;
import models.net.RequestPacket;

public class LevelGameRequest extends RequestPacket {
    private int levelNumber;

    public LevelGameRequest(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    @Override
    public void run() {
        StoryController controller = new StoryController();
        try {
            controller.loadLevel(levelNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
