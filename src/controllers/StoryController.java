package controllers;

import contracts.StoryContract;
import view.views.StoryView;

public class StoryController implements StoryContract.Controller {
    private StoryContract.View view;

    public StoryController() {
        view = new StoryView();
        view.setController(this);
    }

    // TODO: 4/30/19 implemennt funcs

    @Override
    public void loadLevel(int levelNumber) {

    }

    @Override
    public void loadAllLevels() {

    }
}
