package newView.GraphicalElements.battle;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import newView.GraphicalElements.ScaleTool;

import java.io.FileNotFoundException;

import static newView.GraphicalElements.battle.HeroIcon.HERO_ICON_LENGTH;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class PlayerInfoPane extends Pane {
    private ManaPane manaPane = new ManaPane(2);
    private SpecialPower specialPower;
    private HeroIcon heroIcon = new HeroIcon();
    private Text playerName = new Text();

    {
        playerName.setFill(Color.WHITE);
    }

    public PlayerInfoPane(boolean isOnLeft) throws FileNotFoundException {
        specialPower = new SpecialPower(isOnLeft);
        if (!isOnLeft) {
            ScaleTool.relocate(this, WIDTH, 0);
            this.getTransforms().add(new Scale(-1, 1));
        }
        if (isOnLeft) {
            ScaleTool.relocate(playerName, HERO_ICON_LENGTH * 0.9, HERO_ICON_LENGTH * 0.8);
            playerName.getTransforms().add(new Scale(3, 3));
        } else {
            ScaleTool.relocate(playerName, HERO_ICON_LENGTH * 0.9 + 100, HERO_ICON_LENGTH * 0.8);
            playerName.getTransforms().add(new Scale(-3, 3));
        }
        ScaleTool.relocate(manaPane, HERO_ICON_LENGTH * 0.9, HERO_ICON_LENGTH * 0.45);
        ScaleTool.relocate(specialPower, HERO_ICON_LENGTH * 0.5, HERO_ICON_LENGTH);
        this.getChildren().addAll(playerName, manaPane, specialPower, heroIcon);
    }

    public ManaPane getManaPane() {
        return manaPane;
    }

    public SpecialPower getSpecialPower() {
        return specialPower;
    }

    public void setPlayerName(String string) {
        playerName.setText(string);
    }
}
