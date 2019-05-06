package models.artificialIntelligence;

import models.*;
import models.card.Card;
import models.card.SpellCard;
import models.card.Unit;
import models.item.Item;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {
    private static final int NUMBER_OF_RANDOMS = 10;

    public AIPlayer(Deck deck, Account account) {
        super(deck, account);
    }

    public void doActsInAITurn(Game game) {
        putMyCards(game);
        moveMyUnits(game);
        attackToOpponentUnits(game);
        useSpecialPower(game);
        useCollectibles(game);
    }

    private void useCollectibles(Game game) {
        ArrayList<Item> collectiblesToUse = new ArrayList<>(getCollectibles());
        for (Item collectible : collectiblesToUse) {
            setSelectedCollectible(collectible);
            Cell cellToCastCollectible = getBestCellToCastCollectible(game);
            try {
                if (cellToCastCollectible == null) {
                    throw new NullPointerException();
                }
                castSelectedCollectible(cellToCastCollectible);
            } catch (Exception e) {
            }
        }
    }

    private void useSpecialPower(Game game) {
        Cell cellToCastSpecialPower = getBestCellToCastSpecialPower(game);
        try {
            if (cellToCastSpecialPower == null) {
                throw new NullPointerException();
            }
            castHeroSpell(cellToCastSpecialPower);
        } catch (Exception e) {
        }
    }

    private void attackToOpponentUnits(Game game) {
        ArrayList<Unit> originalMyUnits = getUnits();
        ArrayList<Unit> myUnits = new ArrayList<>(originalMyUnits);
        ArrayList<Unit> originalOppUnits = game.getOpponentPlayer().getUnits();
        ArrayList<Unit> opponentUnits = new ArrayList<>(originalOppUnits);
        for (Unit myUnit : myUnits) {
            if (originalMyUnits.contains(myUnit)) {
                setSelectedUnit(myUnit);
                for (Unit opponentUnit : opponentUnits) {
                    if (originalOppUnits.contains(opponentUnit)) {
                        try {
                            attack(opponentUnit);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    private void putMyCards(Game game) {
        ArrayList<Card> originalMyUnits = getHand().getCards();
        ArrayList<Card> myCards = new ArrayList<>(originalMyUnits);
        for (Card card : myCards) {
            putUnit(game, card);
            putSpellCard(game, card);
        }
    }

    private void putSpellCard(Game game, Card card) {
        if (card instanceof SpellCard) {
            Cell cellToCast = getBestCellToCastSpell(game);
            try {
                if (cellToCast == null) {
                    throw new NullPointerException();
                }
                castSpellCard((SpellCard) card, cellToCast);
            } catch (Exception e) {
            }
        }
    }

    private void putUnit(Game game, Card card) {
        if (card instanceof Unit) {
            Cell cellToPut = getBestCellToPutUnit(game);
            try {
                if (cellToPut == null) {
                    throw new NullPointerException();
                }
                putUnit(cellToPut, (Unit) card);
            } catch (Exception e) {
            }
        }
    }

    private void moveMyUnits(Game game) {
        ArrayList<Unit> myUnits = new ArrayList<>(getUnits());
        for (Unit unit : myUnits) {
            setSelectedUnit(unit);
            Cell cellToMove = getBestCellToMove(unit, game);
            try {
                if (cellToMove == null) {
                    throw new NullPointerException();
                }
                moveSelectedUnit(cellToMove);
            } catch (Exception e) {
            }
        }
    }

    private Cell getBestCellToMove(Unit unit, Game game) {
        Player opponent = game.getOpponentPlayer();
        Unit randomUnit = opponent.getRandomUnit();
        if (randomUnit == null) {
            return null;
        }
        int rowDiff = randomUnit.getCurrentCell().getRow() - unit.getCurrentCell().getRow();
        int columnDiff = randomUnit.getCurrentCell().getColumn() - unit.getCurrentCell().getColumn();

        int rowDirection = setMovementDirection(rowDiff);
        int columnDirection = setMovementDirection(columnDiff);

        int newRow = unit.getCurrentCell().getRow() + rowDirection;
        int newColumn = unit.getCurrentCell().getColumn() + columnDirection;

        return game.getTable().getCell(newRow, newColumn);
    }

    private Cell getBestCellToPutUnit(Game game) {
        ArrayList<Cell> cells = new ArrayList<>();
        Unit opponentUnit = game.getOpponentPlayer().getRandomUnit();
        if (opponentUnit == null) {
            return null;
        }
        for (int i = 0; i < NUMBER_OF_RANDOMS; i++) {
            int row = Math.abs(new Random().nextInt()) % 5;
            int column = Math.abs(new Random().nextInt()) % 9;
            Cell cell = game.getTable().getCell(row, column);
            if (!cell.hasUnit())
                cells.add(cell);
        }
        return getNearestCell(cells, opponentUnit.getCurrentCell());
    }

    private Cell getBestCellToCastSpell(Game game) {
        return getARandomCell(game);
    }

    private Cell getBestCellToCastSpecialPower(Game game) {
        return getARandomCell(game);
    }

    private Cell getBestCellToCastCollectible(Game game) {
        return getARandomCell(game);
    }

    private Cell getARandomCell(Game game) {
        int row = Math.abs(new Random().nextInt()) % 5;
        int column = Math.abs(new Random().nextInt()) % 9;
        return game.getTable().getCell(row, column);
    }

    public int setMovementDirection(int number) {
        if (number > 0) {
            return 1;
        } else if (number == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    private Cell getNearestCell(ArrayList<Cell> cells, Cell cell) {
        int distance = 123; //just for sure!!
        Cell resultCell = null;
        for (Cell cell1 : cells) {
            if (Table.getDistance(cell1, cell) < distance && !cell1.hasUnit()) {
                distance = Table.getDistance(cell1, cell);
                resultCell = cell1;
            }
        }
        return resultCell;
    }
}
