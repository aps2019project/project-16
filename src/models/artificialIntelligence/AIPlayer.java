package models.artificialIntelligence;

import models.*;
import models.card.Card;
import models.card.SpellCard;
import models.card.Unit;

import java.util.ArrayList;

public class AIPlayer extends Player {
    public AIPlayer(Deck deck, Account account) {
        super(deck, account);
    }

    public void doActsInAITurn(Game game) {
        // TODO: 5/6/19 call this func
        // TODO: 5/5/19
        //  + attack
        //  + use special power
        //  + use collectible

        //put cards
        putMyCards(game);
        //move
        moveMyUnits(game);
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
                moveUnit(cellToMove);
            } catch (Exception e) {
            }
        }
    }

    private Cell getBestCellToMove(Unit unit, Game game) {
        // TODO: 5/6/19
        return null;
    }

    private Cell getBestCellToPutUnit(Game game) {
        // TODO: 5/6/19
        return null;
    }

    private Cell getBestCellToCastSpell(Game game) {
        // TODO: 5/6/19
        return null;
    }
}
