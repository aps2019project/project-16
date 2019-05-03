package controllers;

import contracts.InGameContract;
import models.GameContents;
import models.Player;
import view.views.InGameView;

import java.util.ArrayList;

public class InGameController implements InGameContract.Controller {
    private InGameContract.View view;

    public InGameController() {
        view = new InGameView();
        view.setController(this);
    }

    // TODO: 4/21/19 implement all of functions :)))

    @Override
    public void loadGameInfo() {
        view.showGameInfo(GameContents.getCurrentGame());
    }

    @Override
    public void loadMinions(boolean myMinions) {
        Player player;
        if (myMinions) {
            player = GameContents.getCurrentGame().getCurrentPlayer();
        } else {
            player = GameContents.getCurrentGame().getOponentPlayer();
        }
        view.showMinions(player.getAccount().getName(), player.getUnits());
    }

    @Override
    public void loadCardInfo(String cardID) {

    }

    @Override
    public void selectCard(String cardID) {

    }

    @Override
    public void moveToCell(int x, int y) {

    }

    @Override
    public void attack(String oppCardID) {

    }

    @Override
    public void attackCombo(String oppCardID, ArrayList<String> myCardIDs) {

    }

    @Override
    public void useSpecialPower(int x, int y) {

    }

    @Override
    public void loadHand() {

    }

    @Override
    public void insertCard(String cardName, int x, int y) {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void loadCollectables() {

    }

    @Override
    public void selectCollectable(int collectableID) {

    }

    @Override
    public void loadSelectedCollectableInfo() {

    }

    @Override
    public void useSelectedCollectable(int x, int y) {

    }

    @Override
    public void loadNextCard() {

    }

    @Override
    public void loadGameHint() {

    }

    @Override
    public void finishTheGame() {

    }
}
