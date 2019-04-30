package models;

import models.card.*;

import java.util.ArrayList;

public class Player {
    private Deck deck;
    private Hand hand;
    private ArrayList<Card> graveYard = new ArrayList<>();
    private int mana;
    private ArrayList<Collectible> collectables = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private Unit selectedUnit;
    private Table table;
    private Hero hero;
    private int turnsFlagKeeped;
    private int numberOfColectedFlags ;

    Player(Deck deck, Hand hand, Table table) {
        this.deck = deck;
        this.hand = hand;
        this.table = table;
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

    public ArrayList<Collectible> getCollectables() {
        return collectables;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public Table getTable() {
        return table;
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
    public void setMana(int mana){
        this.mana = mana;
    }

    public  void addToGraveYard(Card card){
        this.graveYard.add(card);
    }
    ///+move(): void/
    //+putUnit(cell: Cell, card: Card) : void
    //+useItem(item :Item):void
    //+castSpellCard(cell: Cell,spellCard: SpellCard):void
    //+moveUnit(cell: Cell) : void
    //+attack(opponent: Unit):void
    //+comboAttack(opponent: Unit, allies: ArratList<Unit>): void
}
