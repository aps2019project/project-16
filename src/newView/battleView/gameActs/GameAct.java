package newView.battleView.gameActs;

import javafx.application.Platform;

public abstract class GameAct {
    public void passToPlatform() {
        Platform.runLater(this::showAction);
    }

    public abstract void showAction();
}

// TODO: 6/14/19
//  +add card to hand
//  +put unit
//  +mana increase
//  +attack
//  +move
//  +cast spell
//  +special power
//  +use item
//  +pick up flag
//  +counter attack
//  +generate flags
//  +usable item
//  +generate collectibles
//  +pick up item
//  +dying
//  buff
//  cell effect
//  combo attack
//  change turn