package newView.BattleView;

import contracts.InGameContract;
import controllers.InGameController;
import models.Game;
import models.Hand;
import models.Player;
import models.Table;
import models.card.Card;
import models.card.Unit;
import models.item.Item;

import java.util.ArrayList;
// TODO: 6/13/19
public class GraphicalGameViewer implements InGameContract.View {
    private InGameContract.Controller controller;

    public GraphicalGameViewer() {
        new InGameController(this);
    }

    @Override
    public void setController(InGameContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showGameInfo(Game game) {

    }

    @Override
    public void showMinions(String playerName, ArrayList<Unit> units) {

    }

    @Override
    public void showCardInfo(Card card) {

    }

    @Override
    public void showHand(Hand hand, Card nextCard) {

    }

    @Override
    public void showCollectables(ArrayList<Item> collectibles) {

    }

    @Override
    public void showCollectableInfo(Item collectible) {

    }

    @Override
    public void showNextCard(Card card) {

    }

    @Override
    public void showTable(Player currentPlayer, Table table) {

    }

    @Override
    public void goToPrevMenu() {

    }
}
