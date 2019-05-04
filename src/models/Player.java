package models;

import models.card.*;
import models.card.exception.*;

import java.util.ArrayList;

public class Player {
    private Deck deck;
    private Hand hand;
    private Graveyard graveYard = new Graveyard();
    private int mana;
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private Unit selectedUnit;
    private Collectible selectedCollectible;
    private int turnsFlagKeeped;
    private boolean hasFlag;
    private Account account;
    private Table table;

    Player(Deck deck, Account account) {
        this.deck = deck;
        setHand(this.deck);
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

    public Collectible getCollectible(int collectibleID) {
        // TODO: 5/4/19
        return null;
    }

    public void setSelectedCollectible(Collectible selectedCollectible) {
        this.selectedCollectible = selectedCollectible;
    }

    public int getMana() {
        return mana;
    }

    public Graveyard getGraveYard() {
        return graveYard;
    }

    public ArrayList<Collectible> getCollectibles() {
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

    public void setHand(Deck deck) {
        deck.shuffle();
        for (int i = 0; i < 3; i++) {
            this.hand.addCard(deck.pop());
        }
    }

    public void moveUnit(Cell cell) throws UnitMovedThisTurnException, UnitStunnedException, CellIsNotFreeException,
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
        this.hand.removeCard(unit);
        pickUpFlags(cell, selectedUnit);
        //todo if unit is on_spawn
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
    }

    public void castSpellCard(SpellCard spellCard, Cell cell) throws InvalidTargetException, NotEnoughManaException {
        if (!spellCard.canCast(this, cell))
            throw new InvalidTargetException();
        if (spellCard.getManaCost() > this.getMana())
            throw new NotEnoughManaException();
        this.mana -= spellCard.getManaCost();
        spellCard.cast(this, cell);
        //todo set uniqueGameID for spell + goToGraveYard
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
    }

    public void castHeroSpell(Cell cell) throws InvalidTargetException, NotEnoughManaException, SpellNotReadyException
            , NoHeroException {
        Hero hero = this.getHero();
        if (hero == null)
            throw new NoHeroException();
        if (!hero.getSpell().canCast(this, cell))
            throw new InvalidTargetException();
        if (hero.getSpellManaCost() > this.getMana())
            throw new NotEnoughManaException();
        if (!hero.isSpellReady())
            throw new SpellNotReadyException();
        this.mana -= hero.getSpellManaCost();
        hero.castSpell(cell);
        GameContents.getCurrentGame().checkIfAnyoneIsDead();
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
            }
            cell.removeFlag();
        }
    }

    //+useItem(item :Item):void        //todo check is dead for item
}
