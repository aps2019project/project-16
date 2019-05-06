package view.views;

import contracts.InGameContract;
import models.*;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.Unit;
import models.item.Item;
import view.MenuHandler;
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
    public void showCollectables(ArrayList<Item> collectibles) {
        Notify.logMessage("List of collected collectibles:");
        for (Item collectible : collectibles) {
            Notify.logMessage("* name: " + collectible.getName() + " collectible ID: " + collectible.getCollectibleID());
        }
    }

    @Override
    public void showCollectableInfo(Item collectible) {
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
    public void showTable(Player currentPlayer, Table table) {
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                System.out.print("     ");
            } else {
                System.out.printf("%d    ", i);
            }
        }
        System.out.print("\n");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d:   ", i + 1);
            for (int j = 0; j < 9; j++) {
                Cell cell = table.getCell(i, j);
                printCell(currentPlayer, cell);
            }
            System.out.print("\n");
        }
    }

    private void printCell(Player currentPlayer, Cell cell) {
        Unit unit = cell.getUnit();
        if (unit == null) {
            System.out.print("00");
        } else {
            if (unit instanceof Hero) {
                System.out.print("H");
            } else {
                System.out.print("M");
            }
            if (unit.getPlayer() == currentPlayer) {
                System.out.print("1");
            } else {
                System.out.print("2");
            }
        }
        if (cell.getFlags().size() != 0) {
            System.out.print("F");
        } else {
            System.out.print("0");
        }
        if (cell.getCollectibles().size() != 0) {
            System.out.print("C");
        } else {
            System.out.print("0");
        }
        System.out.print(" ");
    }

    @Override
    public void goToPrevMenu() {
        MenuHandler.goToParentMenu();
    }
}
