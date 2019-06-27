package controllers;

import contracts.InGameContract;
import models.*;
import models.card.*;
import exception.*;
import models.item.Item;
import view.Notify;

import java.util.ArrayList;

public class InGameController implements InGameContract.Controller {
    private InGameContract.View view;

    public InGameController(InGameContract.View view) {
        this.view = view;
        view.setController(this);
    }

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
            player = GameContents.getCurrentGame().getOpponentPlayer();
        }
        view.showMinions(player.getAccount().getName(), player.getUnits());
    }

    @Override
    public void loadCardInfo(String playerName, String cardName, int gameCardID) {
        Game game = GameContents.getCurrentGame();
        Player player = game.getPlayer(playerName);
        if (player == null) {
            Notify.logError("This player isn't in the game.");
        } else {
            Unit unit = player.getUnit(cardName, gameCardID);
            if (unit == null) {
                Notify.logError("This unit isn't in the game.");
            } else {
                view.showCardInfo(unit);
            }
        }
    }

    @Override
    public void selectCard(String cardName, int gameID) {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Unit unit = currentPlayer.getUnit(cardName, gameID);
        if (unit == null) {
            Notify.logError("This card isn't in the game!");
        } else {
            currentPlayer.setSelectedUnit(unit);
            Notify.logMessage("Unit \"" + cardName + "\" with game ID \"" + gameID + "\" is selected.");
            int row = unit.getCurrentCell().getRow() + 1;
            int column = unit.getCurrentCell().getColumn() + 1;
            Notify.logMessage("It's place: row: " + row + " column: " + column);
        }
    }

    @Override
    public void moveToCell(int row, int column) {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Cell cell = game.getTable().getCell(row, column);
        Unit selectedUnit = currentPlayer.getSelectedUnit();
        try {
            if (currentPlayer.getSelectedUnit() == null) {
                throw new UnitIsNotSelectedException();
            }
            if (cell == null) {
                throw new CellIsNotInTableException();
            }
            currentPlayer.moveSelectedUnit(cell);
            Notify.logMessage("Unit \"" + selectedUnit.getName() + "\" moved to"
                    + "\n\trow: " + row
                    + "\n\tcolumn: " + column);
        } catch (UnitIsNotSelectedException E) {
            Notify.logError("Sorry! First select a unit then move it!");
        } catch (CellIsNotInTableException E) {
            Notify.logError("Cell is not in table!");
        } catch (CellIsNotFreeException E) {
            Notify.logError("Cell is not free!");
        } catch (UnitMovedThisTurnException E) {
            Notify.logError("Unit has moved in this turn!");
        } catch (UnitStunnedException E) {
            Notify.logError("Unit is stunned and can't move!");
        } catch (DistanceException E) {
            Notify.logError("Distance is to far!");
        } catch (PathIsBlockException E) {
            Notify.logError("The path is blocked!");
        }
    }

    @Override
    public void attack(String oppCardName, int gameID) {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        Unit opponentUnit = opponentPlayer.getUnit(oppCardName, gameID);
        try {
            if (currentPlayer.getSelectedUnit() == null) {
                throw new UnitIsNotSelectedException();
            }
            if (opponentUnit == null) {
                throw new InvalidOpponentException();
            }
            currentPlayer.attack(opponentUnit);
            Notify.logMessage("You attacked to \"" + opponentUnit.getName() + "\"");
        } catch (UnitIsNotSelectedException E) {
            Notify.logError("Sorry! First select a unit then attack!");
        } catch (InvalidOpponentException E) {
            Notify.logError("This opponent isn't in the game.");
        } catch (AttackException E) {
            Notify.logError(E.getMessage());
        }
    }

    @Override
    public void attackCombo(String oppCardID, ArrayList<String> myCardIDs) {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        Unit opponentUnit = opponentPlayer.getUnit(getCardName(oppCardID), getIDFromString(oppCardID));
        ArrayList<Unit> myUnits = initMyUnitsFromStrings(currentPlayer, myCardIDs);

        if (myUnits == null) {
            Notify.logError("A unit of yours found that isn't in the game!");
            return;
        }

        try {
            if (currentPlayer.getSelectedUnit() == null) {
                throw new UnitIsNotSelectedException();
            }
            if (opponentUnit == null) {
                throw new InvalidOpponentException();
            }
            currentPlayer.comboAttack(opponentUnit, myUnits);
            Notify.logMessage("You used combo attack on \"" + opponentUnit.getName() + "\"");
        } catch (UnitIsNotSelectedException E) {
            Notify.logError("Sorry! First select a unit then attack!");
        } catch (InvalidOpponentException E) {
            Notify.logError("This opponent isn't in the game.");
        } catch (UnitHasNotComboException E) {
            Notify.logError("Selected unit has not combo ability!");
        } catch (AttackException E) {
            Notify.logError(E.getMessage());
        }
    }

    private ArrayList<Unit> initMyUnitsFromStrings(Player currentPlayer, ArrayList<String> myCardIDs) {
        ArrayList<Unit> myUnits = new ArrayList<>();
        for (String myCardID : myCardIDs) {
            Unit myUnit = currentPlayer.getUnit(getCardName(myCardID), getIDFromString(myCardID));
            if (myUnit == null) {
                return null;
            } else {
                myUnits.add(myUnit);
            }
        }
        return myUnits;
    }

    private String getCardName(String cardID) {
        String[] strings = cardID.split(",");
        return strings[0];
    }

    private int getIDFromString(String cardID) {
        String[] strings = cardID.split(",");
        return Integer.parseInt(strings[1]);
    }

    @Override
    public void useSpecialPower(int row, int column) {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Cell cellToUseSP = game.getTable().getCell(row, column);
        try {
            if (cellToUseSP == null) {
                throw new CellIsNotInTableException();
            }
            currentPlayer.castHeroSpell(cellToUseSP);
            Notify.logMessage("You used special power of hero on row: " + row + " column: " + column);
        } catch (CellIsNotInTableException E) {
            Notify.logError("The cell is not in the table!");
        } catch (NoHeroException E) {
            Notify.logError("Your hero is killed!");
        } catch (NotEnoughManaException E) {
            Notify.logError("You don't have enough mana!");
        } catch (SpellNotReadyException E) {
            Notify.logError("Special power isn't ready!");
        } catch (InvalidTargetException E) {
            Notify.logError("Invalid target!");
        } catch (NoSpecialPowerException e) {
            Notify.logError("Hero doesn't have special power!");
        }
    }

    @Override
    public void loadHand() {
        Player player = GameContents.getCurrentGame().getCurrentPlayer();
        Hand hand = player.getHand();
        view.showHand(hand, player.getDeck().getTop());
    }

    @Override
    public void insertCard(String cardName, int row, int column) {
        try {
            Game game = GameContents.getCurrentGame();
            Player currentPlayer = game.getCurrentPlayer();
            Card cardToInsert = currentPlayer.getHand().getCard(cardName);
            Cell cell = game.getTable().getCell(row, column);
            if (cell == null) {
                throw new CellIsNotInTableException();
            }
            if (cardToInsert == null) {
                throw new CardNotInHandException();
            } else {
                if (cardToInsert.getClass() == Hero.class || cardToInsert.getClass() == Minion.class) {
                    currentPlayer.putUnit(cell, (Unit) cardToInsert);
                    Notify.logMessage("You inserted the unit \"" + cardToInsert.getName() + "\""
                            + " in row: " + row
                            + " in column: " + column);
                } else {
                    currentPlayer.castSpellCard((SpellCard) cardToInsert, cell);
                    Notify.logMessage("You cast the spell \"" + cardToInsert.getName() + "\""
                            + " in row: " + row
                            + " in column: " + column);
                }
            }
        } catch (CardNotInHandException E) {
            Notify.logError("This card doesn't exist in your hand.");
        } catch (CellIsNotInTableException E) {
            Notify.logError("The cell is not in the table!");
        } catch (CellIsNotFreeException E) {
            Notify.logError("Cell is not free!");
        } catch (NotEnoughManaException E) {
            Notify.logError("You don't have enough mana.");
        } catch (InvalidTargetException E) {
            Notify.logError("Invalid target for spell!");
        }
    }

    @Override
    public void endTurn() {
        try {
            GameContents.getCurrentGame().changeTurn();
        } catch (GameIsEndException E) {
            Notify.logMessage("Game is finished!!");
            Notify.logMessage("Winner is: \"" + GameContents.getCurrentGame().getWinner().getAccount().getName() + "\"");
//            view.goToPrevMenu();
        }
    }

    @Override
    public void loadCollectables() {
        ArrayList<Item> collectibles = GameContents.getCurrentGame().getCurrentPlayer().getCollectibles();
        view.showCollectables(collectibles);
    }

    @Override
    public void selectCollectable(int collectableID) {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Item collectible = currentPlayer.getCollectible(collectableID);
        if (collectible == null) {
            Notify.logError("You don't have this collectible!");
        } else {
            currentPlayer.setSelectedCollectible(collectible);
            Notify.logMessage("You selected the collectible \"" + collectableID + "\"");
        }
    }

    @Override
    public void loadSelectedCollectableInfo() {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Item selectedCollectible = currentPlayer.getSelectedCollectible();
        if (selectedCollectible == null) {
            Notify.logError("You didn't select any collectible!");
        } else {
            view.showCollectableInfo(selectedCollectible);
        }
    }

    @Override
    public void useSelectedCollectable(int row, int column) {
        Game game = GameContents.getCurrentGame();
        Cell cell = game.getTable().getCell(row, column);
        try {
            if (cell == null) {
                throw new CellIsNotInTableException();
            }
            game.getCurrentPlayer().castSelectedCollectible(cell);
            Notify.logMessage("You casted the selected collectible!");
        } catch (NoSelectedCollectibleException e) {
            Notify.logError("At first you must select a collectible!");
        } catch (CellIsNotInTableException e) {
            Notify.logError("Cell is not in the table!");
        }
    }

    @Override
    public void loadNextCard() {
        Player player = GameContents.getCurrentGame().getCurrentPlayer();
        view.showNextCard(player.getDeck().getTop());
    }

    @Override
    public void loadGameTable() {
        Player currentPlayer = GameContents.getCurrentGame().getCurrentPlayer();
        Table table = GameContents.getCurrentGame().getTable();
        view.showTable(currentPlayer, table);
    }

    @Override
    public void refuseGame() {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        game.setWinner(opponentPlayer);
        game.getRewardToWinner();
        GameContents.saveCurrentAndSecondAccounts();

        Notify.logMessage("Player \"" + currentPlayer.getAccount().getName() + "\" refused the game!!");
        Notify.logMessage("Winner is: \"" + GameContents.getCurrentGame().getWinner().getAccount().getName() + "\"");
        view.goToPrevMenu();
    }
}
