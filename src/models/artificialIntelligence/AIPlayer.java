package models.artificialIntelligence;

import models.*;
import models.card.Card;
import models.card.SpellCard;
import models.card.Unit;
import models.item.Item;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {
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
        ArrayList<Item> collectibles = getCollectibles();
        for (Item collectible : collectibles) {
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
        ArrayList<Unit> myUnits = getUnits();
        ArrayList<Unit> opponentUnits = game.getOpponentPlayer().getUnits();
        for (Unit myUnit : myUnits) {
            setSelectedUnit(myUnit);
            for (Unit opponentUnit : opponentUnits) {
                try {
                    attack(opponentUnit);
                } catch (Exception e) {
                }
            }
        }
    }

    private void putMyCards(Game game) {
        ArrayList<Card> myCards = getHand().getCards();
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
        ArrayList<Unit> myUnits = getUnits();
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
        // TODO: 5/6/19 handle null of random unit + in getRandom if size of units == 0 bood!!!
        int rowDiff = randomUnit.getCurrentCell().getRow() - unit.getCurrentCell().getRow();
        int columnDiff = randomUnit.getCurrentCell().getColumn() - unit.getCurrentCell().getColumn();

        int rowDirection = setMovementDirection(rowDiff);
        int columnDirection = setMovementDirection(columnDiff);

        int newRow = unit.getCurrentCell().getRow() + rowDirection;
        int newColumn = unit.getCurrentCell().getColumn() + columnDirection;

        return game.getTable().getCell(newRow, newColumn);
    }

    private Cell getBestCellToPutUnit(Game game) {
        // TODO: 5/6/19
        return null;
    }

    private Cell getBestCellToCastSpell(Game game) {
        // TODO: 5/6/19
        return null;
    }

    private Cell getBestCellToCastSpecialPower(Game game) {
        // TODO: 5/6/19
        return null;
    }

    private Cell getBestCellToCastCollectible(Game game) {
        // TODO: 5/6/19
        return null;
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
}
