package controllers;

import contracts.StoryContract;
import models.GameContents;
import view.views.StoryView;

public class StoryController implements StoryContract.Controller {
    private StoryContract.View view;

    public StoryController() {
        view = new StoryView();
        view.setController(this);
    }

    @Override
    public void loadLevel(int levelNumber) {
        // TODO: 4/30/19 first must make a game by level properties
        view.goToLevelInGame();
    }

    @Override
    public void loadAllLevels() {
        view.showAllLevels(GameContents.getGameLevels());
    }
}
