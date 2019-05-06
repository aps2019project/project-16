package models.artificialIntelligence;

import models.Account;
import models.Deck;
import models.Player;

public class AIAccount extends Account {
    private Deck AIDeck;

    public AIAccount(String name, String password, Deck deck) {
        //copy of deck is copied to this function
        super(name, password);
        AIDeck = deck;
    }

    @Override
    public Player getNewPlayerFromAccount() {
        return new AIPlayer(AIDeck, this);
    }
}
