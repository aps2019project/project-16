package view.views;

import contracts.InGameContract;
import models.*;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.Unit;
import view.Notify;

import java.util.ArrayList;

public class InGameView implements InGameContract.View {
    private InGameContract.Controller controller;

    @Override
    public void setController(InGameContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showGameInfo(Game game) {
        Player[] players = game.getPlayers();

        //manas information
        Notify.logMessage("Number of mana:");
        for (Player player : players) {
            Notify.logMessage("manas of \"" + player.getAccount().getName() + "\" is: " + player.getMana());
        }

        //game condition
        switch (game.getGameMode()) {
            case KILLING_HERO:
                for (Player player : players) {
                    Notify.logMessage("Hero health of \"" + player.getAccount().getName() + "\" is: " + player.getHero().getHp());
                }
                break;
            case KEEP_FLAG:
            case COLLECT_FLAG:
                ArrayList<Flag> flags = game.getFlags();
                Notify.logMessage("Flags in the game:");
                int i = 0;
                for (Flag flag : flags) {
                    i++;
                    printFlagState(i, flag);
                }
                break;
        }
    }

    private void printFlagState(int i, Flag flag) {
        Cell flagCell = flag.getCurrentCell();
        Unit ownerUnit = flagCell.getUnit();
        if (ownerUnit == null) {
            Notify.logMessage(i + ": Flag is in"
                    + " row: " + flagCell.getRow()
                    + " column: " + flagCell.getColumn()
                    + " and don't have any owner.");
        } else {
            Notify.logMessage(i + ": Flag is in"
                    + " row: " + flagCell.getRow()
                    + " column: " + flagCell.getColumn()
                    + " owner player: " + ownerUnit.getPlayer().getAccount().getName()
                    + " owner unit: " + ownerUnit.getName() + "_" + ownerUnit.getGameCardID());
        }
    }

    @Override
    public void showMinions(String playerName, Hero hero, ArrayList<Minion> minions) {
        // TODO: 5/3/19
    }

    @Override
    public void showCardInfo(Card card) {
        // TODO: 5/3/19
    }

    @Override
    public void showHand(Hand hand, Card nextCard) {
        // TODO: 5/3/19
    }

    @Override
    public void showCollectables(ArrayList<Collectible> collectibles) {
        // TODO: 5/3/19
    }

    @Override
    public void showCollectableInfo(Collectible collectible) {
        // TODO: 5/3/19
    }

    @Override
    public void showNextCard(Card card) {
        // TODO: 5/3/19
    }
}
