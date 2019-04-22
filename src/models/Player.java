package models;

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
    Player(Deck deck , Hand hand , Table table){
        this.deck = deck;
        this.hand = hand;
        this.table = table;
    }
    ///Player/
    //--
    //-deck: Deck
    //-hand: Hand
    //-graveYard: ArrayList<Card>
    //-mana: int
    //-collectables: ArrayList<Collectible>
    //-units: Unit
    //-selectedUnit: Unit
    //-map: Map
    //-collectables: ArrayList<Collectible>
    //--
    //+setMana(mana: int): void
    ///+move(): void/
    //+putUnit(cell: Cell, card: Card) : void
    //+useItem(item :Item):void
    //+castSpellCard(cell: Cell,spellCard: SpellCard):void
    //+moveUnit(cell: Cell) : void
    //+attack(opponent: Unit):void
    //+comboAttack(opponent: Unit, allies: ArratList<Unit>): void
    //+addToGraveYard(card: Card): void
}
