package view.views;

import contracts.CustomGameContract;
import view.MenuHandler;

import static view.menuItems.MenuConstants.IN_GAME_MENU;

public class CustomGameView implements CustomGameContract.View {
    private CustomGameContract.Controller controller;

    @Override
    public void setController(CustomGameContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void goToInGameMenu() {
        MenuHandler.goToSubMenu(IN_GAME_MENU);
    }
}
