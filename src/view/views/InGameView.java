package view.views;

import contracts.InGameContract;
import models.*;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.Unit;
import models.item.Collectible;
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
        int row = flagCell.getRow() + 1;
        int column = flagCell.getColumn() + 1;
        if (ownerUnit == null) {
            Notify.logMessage(i + ": Flag is in"
                    + " row: " + row
                    + " column: " + column
                    + " and don't have any owner.");
        } else {
            Notify.logMessage(i + ": Flag is in"
                    + " row: " + row
                    + " column: " + column
                    + " owner player: " + ownerUnit.getPlayer().getAccount().getName()
                    + " owner unit: " + ownerUnit.getName() + "_" + ownerUnit.getGameCardID());
        }
    }

    @Override
    public void showMinions(String playerName, ArrayList<Unit> units) {
        Notify.logMessage("** \"" + playerName + "\" units:");
        for (Unit unit : units) {
            printUnitInfo(unit);
        }
    }

    private void printUnitInfo(Unit unit) {
        int row = unit.getCurrentCell().getRow() + 1;
        int column = unit.getCurrentCell().getColumn() + 1;
        Notify.logMessage("Name: " + unit.getName());
        Notify.logMessage("Game ID: " + unit.getGameCardID());
        Notify.logMessage("HP: " + unit.getHp());
        Notify.logMessage("AP: " + unit.getAp());
        Notify.logMessage("row: " + row);
        Notify.logMessage("column: " + column);
    }

    @Override
    public void showCardInfo(Card card) {
        Class cardClass = card.getClass();

        Notify.logMessage(cardClass.getName());
        Notify.logMessage("Name: " + card.getName());
        Notify.logMessage("Cost: " + card.getBuyPrice());
        Notify.logMessage("Mana cost: " + card.getManaCost());
        Notify.logMessage("Desc: " + card.getDescription());

        if (cardClass == Hero.class || cardClass == Minion.class) {
            Unit unit = (Unit) card;
            Notify.logMessage("HP: " + unit.getHp());
            Notify.logMessage("AP: " + unit.getAp());
            Notify.logMessage("Range: " + unit.getAttackType().getClass().getName());
        }

        if (cardClass == Minion.class) {
            Minion minion = (Minion) card;
            Notify.logMessage("Combo-ability: " + minion.getComboDescription());
        }

        Notify.logMessage("");
    }

    @Override
    public void showHand(Hand hand, Card nextCard) {
        showNextCard(nextCard);

        Notify.logMessage("* Hand:");
        for (Card card : hand.getCards()) {
            showCardInfo(card);
        }
    }

    @Override
    public void showCollectables(ArrayList<Collectible> collectibles) {
        Notify.logMessage("List of collected collectibles:");
        for (Collectible collectible : collectibles) {
            Notify.logMessage(collectible.getName());
        }
    }

    @Override
    public void showCollectableInfo(Collectible collectible) {
        Notify.logMessage(collectible.getDescription());
    }

    @Override
    public void showNextCard(Card nextCard) {
        Notify.logMessage("* Next card:");
        if (nextCard == null) {
            Notify.logMessage("No more card!");
        } else {
            showCardInfo(nextCard);
        }
    }

    @Override
    public void showTable(Table table) {
        // TODO: 5/5/19
    }
}
