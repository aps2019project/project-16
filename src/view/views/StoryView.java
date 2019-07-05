package view.views;

import contracts.StoryContract;
import models.GameLevel;
import view.MenuHandler;

import java.util.ArrayList;

import static view.menuItems.MenuConstants.IN_GAME_MENU;

public class StoryView implements StoryContract.View {
    private StoryContract.Controller controller;

    @Override
    public void setController(StoryContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void goToLevelInGame() {
        MenuHandler.goToSubMenu(IN_GAME_MENU);
    }

    @Override
    public void showAllLevels(ArrayList<GameLevel> gameLevels) {
        for (GameLevel gameLevel : gameLevels) {
            printGameDetails(gameLevel);
        }
    }

    private void printGameDetails(GameLevel gameLevel) {
        System.out.printf("* level number: %d - Hero: %s - mode: %d - prize: %d\n"
                , gameLevel.getLevelNumber()
                , gameLevel.getDeck().getHero().getName()
                , gameLevel.getMode()
                , gameLevel.getPrize());
    }
}
