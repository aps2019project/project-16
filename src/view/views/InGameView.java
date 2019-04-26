package view.views;

import contracts.InGameContract;
import models.*;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;

import java.util.ArrayList;

public class InGameView implements InGameContract.View {
    private InGameContract.Controller controller;

    @Override
    public void setController(InGameContract.Controller controller) {
        this.controller = controller;
    }

    // TODO: 4/21/19 implement all of functions :)))

    @Override
    public void showGameInfo(Game game) {

    }

    @Override
    public void showMinions(String playerName, Hero hero, ArrayList<Minion> minions) {

    }

    @Override
    public void showCardInfo(Card card) {

    }

    @Override
    public void showHand(Hand hand, Card nextCard) {

    }

    @Override
    public void showCollectables(ArrayList<Collectible> collectibles) {

    }

    @Override
    public void showCollectableInfo(Collectible collectible) {

    }

    @Override
    public void showNextCard(Card card) {

    }
}
