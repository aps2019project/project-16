package models;

import models.card.*;

import java.util.ArrayList;

public class Player {
    private Deck deck;
    private Hand hand;
    private ArrayList<Card> graveYard = new ArrayList<>();
    private int mana;
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private Unit selectedUnit;
    private Hero hero;
    private int turnsFlagKeeped;
    private int numberOfColectedFlags;
    private Account account;

    Player(Deck deck , Account account) {
        this.deck = deck;
        setHand(deck);
    }

    public Account getAccount() {
        return account;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public int getNumberOfColectedFlags() {
        return numberOfColectedFlags;
    }

    public int getTurnsFlagKeeped() {
        return turnsFlagKeeped;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hero getHero() {
        return hero;
    }

    public Hand getHand() {
        return hand;
    }

    public ArrayList<Card> getGraveYard() {
        return graveYard;
    }

    public int getMana() {
        return mana;
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

    public void attack(Unit opponent) throws UnitAttackedThisTurnException, UnitStunnedException {
        selectedUnit.attack(opponent);
        opponent.counterAttack(selectedUnit);
    }

    public void comboAttack(Unit opponent, ArrayList<Unit> allies) {
        selectedUnit.comboAttack(opponent, allies);
        opponent.counterAttack(selectedUnit);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setHand(Deck deck) {
        deck.shuffle();
        for (int i = 0; i < 5; i++) {
            this.hand.addCard(deck.pop());
        }
    }

    public void addToGraveYard(Card card) {
        this.graveYard.add(card);
    }

    public void moveUnit(Cell cell) throws UnitMovedThisTurnException, UnitStunnedException {
        this.selectedUnit.move(cell);
    }

    public void putUnit(Cell cell, Unit unit) {
        for (Card card : this.hand.getCards()) {
            if (card.getName().equals(unit.getName())) {
                unit.setCurrentCell(cell);
                break;
            }
        }
    }

    //+useItem(item :Item):void
    //+castSpellCard(cell: Cell,spellCard: SpellCard):void
    //+moveUnit(cell: Cell) : void
    //+attack(opponent: Unit):void
    //+comboAttack(opponent: Unit, allies: ArratList<Unit>): void
}
