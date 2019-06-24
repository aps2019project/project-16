package newView.BattleView.gameActs;

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
//  buff
//  cell effect
//  pick up item
//  +pick up flag
//  +counter attack
//  combo attack
//  change turn
//  +generate flags
//  generate collectibles
//  usable item