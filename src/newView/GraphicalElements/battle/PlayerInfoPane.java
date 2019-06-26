package newView.GraphicalElements.battle;

import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import newView.GraphicalElements.ScaleTool;

import java.io.FileNotFoundException;

import static newView.GraphicalElements.battle.HeroIcon.HERO_ICON_LENGTH;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class PlayerInfoPane extends Pane {
    private ManaPane manaPane = new ManaPane(2);
    private SpecialPower specialPower;
    private HeroIcon heroIcon = new HeroIcon();

    public PlayerInfoPane(boolean isOnLeft) throws FileNotFoundException {
        specialPower = new SpecialPower(isOnLeft);
        if (!isOnLeft) {
            ScaleTool.relocate(this, WIDTH, 0);
            this.getTransforms().add(new Scale(-1, 1));
        }
        ScaleTool.relocate(manaPane, HERO_ICON_LENGTH * 0.9, HERO_ICON_LENGTH * 0.45);
        ScaleTool.relocate(specialPower, HERO_ICON_LENGTH * 0.5, HERO_ICON_LENGTH);
        this.getChildren().addAll(manaPane, specialPower, heroIcon);
    }

    public ManaPane getManaPane() {
        return manaPane;
    }

    public SpecialPower getSpecialPower() {
        return specialPower;
    }
}
