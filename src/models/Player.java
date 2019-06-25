package models;

import models.card.*;
import exception.*;
import models.item.Item;
import newView.BattleView.ClientSender;
import newView.BattleView.gameActs.*;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private Deck deck;
    private Hand hand = new Hand();
    private Graveyard graveYard = new Graveyard();
    private int mana = 2;
    private ArrayList<Item> collectibles = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private Unit selectedUnit;
    private Item selectedCollectible;
    private int turnsFlagKeeped;
    private Account account;
    private Table table;

    private boolean isOnLeft;

    public void setOnLeft(boolean onLeft) {
        isOnLeft = onLeft;
    }

    public Player(Deck deck, Account account) {
        this.account = account;
        this.deck = deck;
        for (Card card : deck.getCards()) {
            if (card instanceof Unit) {
                ((Unit) card).setPlayer(this);
            }
        }
        deck.getHero().setPlayer(this);
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Account getAccount() {
        return account;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public int getTurnsFlagKeeped() {
        return turnsFlagKeeped;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hero getHero() {
        for (Unit unit : units) {
            if (unit instanceof Hero)
                return (Hero) unit;
        }
        return null;
    }

    public String getName() {
        return account.getName();
    }

    public Hand getHand() {
        return hand;
    }

    public Unit getUnit(String cardName, int gameID) {
        for (Unit unit : units) {
            if (unit.getName().equalsIgnoreCase(cardName) && unit.getGameCardID() == gameID) {
                return unit;
            }
        }
        return null;
    }

    public Item getCollectible(int collectibleID) {
        for (Item collectible : collectibles) {
            if (collectible.getCollectibleID() == collectibleID) {
                return collectible;
            }
        }
        return null;
    }

    public Item getSelectedCollectible() {
        return selectedCollectible;
    }

    public void setSelectedCollectible(Item selectedCollectible) {
        this.selectedCollectible = selectedCollectible;
    }

    public int getMana() {
        return mana;
    }

    public Graveyard getGraveYard() {
        return graveYard;
    }

    public ArrayList<Item> getCollectibles() {
        return collectibles;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void attack(Unit opponent) throws AttackException {
        selectedUnit.attack(opponent);
        opponent.counterAttack(selectedUnit);
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
        selectedUnit = null;
    }

    public void comboAttack(Unit opponent, ArrayList<Unit> allies) throws UnitHasNotComboException, AttackException {
        if (!this.selectedUnit.hasCombo())
            throw new UnitHasNotComboException();
        this.selectedUnit.checkCanAttack(opponent);
        //RECOM : move 2 above method in Unit.ComboAttack (below)
        selectedUnit.comboAttack(opponent, allies);
        opponent.counterAttack(selectedUnit);
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
        selectedUnit = null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public void setMana(int mana) {
        this.mana = mana;
    }

    public ArrayList<Card> setHand() {
        ArrayList<Card> addedCards = new ArrayList<>();
        Deck deck = this.deck;
        deck.shuffle();
        for (int i = 0; i < 3; i++) {
            try {
                Card card = deck.pop();
                this.hand.addCard(card);

                addedCards.add(card);
            } catch (ArrayIsEmptyException e) {

            }
        }
        return addedCards;
    }

    public void moveSelectedUnit(Cell cell) throws UnitMovedThisTurnException, UnitStunnedException, CellIsNotFreeException,
            DistanceException, PathIsBlockException {
        if (Table.getDistance(this.selectedUnit.getCurrentCell(), cell) > 2)
            throw new DistanceException();
        if (this.table.checkPathIsBlocked(this.selectedUnit.getCurrentCell(), cell)) {
            throw new PathIsBlockException();
        }
        Cell tempCell = this.selectedUnit.getCurrentCell();
        this.selectedUnit.move(cell);
        cell.setUnit(this.selectedUnit);
        tempCell.setUnit(null);

        for (Flag flag : selectedUnit.getFlags())
            flag.setCurrentCell(cell);

        pickUpFlags(cell, selectedUnit);
        pickUpCollectibles(cell);
        selectedUnit = null;
    }

    public void putUnit(Cell cell, Unit unit) throws CellIsNotFreeException, NotEnoughManaException {
        if (unit.getManaCost() > this.getMana())
            throw new NotEnoughManaException();
        if (cell.hasUnit())
            throw new CellIsNotFreeException();
        this.mana -= unit.getManaCost();
        unit.setCurrentCell(cell);
        unit.setGameCardID(UniqueIDGenerator.getGameUniqueID(this.account.getName(), unit.getName()));
        cell.setUnit(unit);
        this.hand.removeCard(unit);
        this.units.add(unit);

        ClientSender.sendToViewer(new PutUnitAct(cell.getRow(), cell.getColumn(), isOnLeft, unit));

        pickUpFlags(cell, unit);
        pickUpCollectibles(cell);

        unit.castSpecialPower(SpecialPowerCastTime.ON_SPAWN, cell);
        unit.castSpecialPower(SpecialPowerCastTime.PASSIVE, cell);
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
    }

    public void castSpellCard(SpellCard spellCard, Cell cell) throws InvalidTargetException, NotEnoughManaException {
        if (!spellCard.canCast(this, cell))
            throw new InvalidTargetException();
        if (spellCard.getManaCost() > this.getMana())
            throw new NotEnoughManaException();
        this.mana -= spellCard.getManaCost();
        spellCard.cast(this, cell);
        spellCard.setGameCardID(UniqueIDGenerator.getGameUniqueID(this.getAccount().getName(), spellCard.getName()));
        graveYard.addCard(spellCard);

        ClientSender.sendToViewer(new SpellCastAct(cell.getRow(), cell.getColumn(), isOnLeft, spellCard));

        this.getHand().removeCard(spellCard);
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
    }

    public void castHeroSpell(Cell cell) throws InvalidTargetException, NotEnoughManaException, SpellNotReadyException
            , NoHeroException, NoSpecialPowerException {
        Hero hero = this.getHero();
        if (hero == null)
            throw new NoHeroException();
        if (hero.getSpell() == null)
            throw new NoSpecialPowerException();
        if (!hero.getSpell().canCast(this, cell))
            throw new InvalidTargetException();
        if (hero.getSpellManaCost() > this.getMana())
            throw new NotEnoughManaException();
        if (!hero.isSpellReady())
            throw new SpellNotReadyException();
        this.mana -= hero.getSpellManaCost();
        hero.castSpell(cell);

        ClientSender.sendToViewer(new SpecialPowerAct(cell.getRow(), cell.getColumn(), hero.getName()));

        GameContents.getCurrentGame().checkIfAnyoneIsDead();
    }

    public void castSelectedCollectible(Cell cell) throws NoSelectedCollectibleException {
        if (selectedCollectible == null) {
            throw new NoSelectedCollectibleException();
        }
        selectedCollectible.use(this, cell);

        ClientSender.sendToViewer(new UseCollectibleAct(cell.getRow(), cell.getColumn(), selectedCollectible));

        this.getCollectibles().removeIf(a -> a.equals(selectedCollectible));
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
        selectedCollectible = null;
    }

    public int getNumberOfCollectedFlags() {
        int sum = 0;
        for (Unit unit : units) {
            sum += unit.getFlags().size();
        }
        return sum;
    }

    public boolean hasFlag() {
        for (Unit unit : units) {
            if (unit.getFlags().size() > 0)
                return true;
        }
        return false;
    }

    public void incrementTurnsFlagKeeped() {
        this.turnsFlagKeeped++;
    }

    public void pickUpFlags(Cell cell, Unit unit) {
        if (cell.getFlags().size() > 0) {
            for (Flag flag : cell.getFlags()) {
                unit.addFlag(flag);
                flag.setOwnerUnit(unit);

                ClientSender.sendToViewer(new PickFlagAct(cell.getRow(), cell.getColumn()));
            }
            cell.removeFlag();
        }
    }

    public void pickUpCollectibles(Cell cell) {
        if (cell.getCollectibles().size() > 0) {
            this.collectibles.addAll(cell.getCollectibles());
            cell.removeCollectibles();

            ClientSender.sendToViewer(new PickUpCollectibleAct(cell.getRow(), cell.getColumn()));
        }
    }

    public Unit getRandomUnit() {
        if (this.getUnits().size() == 0) {
            return null;
        }
        int i = Math.abs(new Random().nextInt()) % this.getUnits().size();
        Unit unit = units.get(i);
        return unit;
    }

    // TODO: 5/6/19 ManaItems
}
